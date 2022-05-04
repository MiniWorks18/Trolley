package com.example.trolley;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Fetch extends MainActivity {
    Item[] wooliesItems;
    Item[] colesItems;
    Boolean wooliesProcessing = false;
    Boolean colesProcessing = false;

    // Create an interface to respond with the result after processing
    public interface OnProcessedListener {
        public void onProcessed();
    }

    // Thread delegates search tasks to other threads
    public void threadSearch(String term, Boolean shouldSearchWoolies, Boolean shouldSearchColes) {

        // Create some member variables for the ExecutorService
        // and for the Handler that will update the UI from the main thread
        ExecutorService mExecutor = Executors.newSingleThreadExecutor();
        Handler mHandler = new Handler(Looper.getMainLooper());

        final OnProcessedListener listener = new OnProcessedListener(){
            @Override
            public void onProcessed(){
                // Use the handler so we're not trying to update the UI from the search thread
                mHandler.post(new Runnable(){
                    @Override
                    public void run(){

                        // Update the UI
                        MainActivity.searchedItems = wooliesItems;
                        MainActivity.updateAdapter();

                        // Shutdown the thread
                        mExecutor.shutdown();
                    }
                });
            }
        };

        Runnable backgroundRunnable = new Runnable(){
            @Override
            public void run(){ // Perform background operation(s) and set the result(s)
                // In new threads search coles and woolies
                if (shouldSearchWoolies) {
                    threadSearchWoolies(term);
                    wooliesProcessing = true;
                }
                if (shouldSearchColes) {
                    threadSearchColes(term, false, 0);
                    colesProcessing = true;
                }

                // Wait for the threads to return
                while(colesProcessing || wooliesProcessing) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // In new threads, search coles for every item found at woolies
                if (shouldSearchColes) {
                    for (int i = 0; i < wooliesItems.length; i++) {
                        threadSearchColes(""+wooliesItems[i].getBarcode(), true, i);
                    }
                }

                /* TODO wait for the threads to return, then add items from colesItems to
                *   wooliesItems that are not already in there */

                // Use the interface to pass along the result
                listener.onProcessed();
            }
        };
        // Execute the thread
        mExecutor.execute(backgroundRunnable);
    }

    // Creates thread to search woolies for term
    private void threadSearchWoolies(String term) {
        // Create some member variables for the ExecutorService
        // and for the Handler that will update the UI from the main thread
        ExecutorService mExecutor = Executors.newSingleThreadExecutor();
        Runnable backgroundRunnable = new Runnable(){
            @Override
            public void run(){ // Perform background operation(s) and set the result(s)
                // Search woolies in a thread
                Utils utils = new Utils();
                wooliesItems = utils.searchWoolworths(term);
                wooliesProcessing = false;

                // Shutdown the thread
                mExecutor.shutdown();
            }
        };
        // Execute the thread
        mExecutor.execute(backgroundRunnable);
    }

    // Thread that searhces coles
    private void threadSearchColes(String term, boolean comparing, int index) {
        // Create some member variables for the ExecutorService
        // and for the Handler that will update the UI from the main thread
        ExecutorService mExecutor = Executors.newSingleThreadExecutor();
        Runnable backgroundRunnable = new Runnable(){
            @Override
            public void run(){ // Perform background operation(s) and set the result(s)
                Utils utils = new Utils();

                // Either update woolies items, or fetch a list of coles items
                if (comparing) {
                    Item item = utils.searchColes(term)[0];
                    if (item != null) {
                        // Update searched items (wooliesItems) with coles information
                        wooliesItems[index].setAtColes(true);
                        wooliesItems[index].setColesPrice(item.getColesPrice());
                    }
                } else {
                    colesItems = utils.searchColes(term);
                }

                colesProcessing = false;

                // Shutdown the thread
                mExecutor.shutdown();
            }
        };
        // Execute the thread
        mExecutor.execute(backgroundRunnable);
    }


}
