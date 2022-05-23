package com.example.trolley;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Fetch extends MainActivity {
    ArrayList<Item> wooliesItems;
    Item[] colesItems;
    Boolean wooliesProcessing = false;
    Boolean colesProcessing = false;
    int returnCount = 0;
    double waitPercentage = 0.3;
    String exclusiveColesBrands = "Coles";

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
                        MainActivity.searchedItems = wooliesItems.toArray(new Item[0]);
                        HomeFragment.updateAdapter();

                        try {
                            TimeUnit.MILLISECONDS.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        HomeFragment.updateAdapter();

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
                Utils utils = new Utils();
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
                    for (int i = 0; i < wooliesItems.size(); i++) {
                        threadSearchColes(""+wooliesItems.get(i).getBarcode(), true, i);
                    }
                }

                // Collect Woolworths item images
                for (Item wooliesItem : wooliesItems) {
                    new ImageLoadTask(wooliesItem.getWoolworthsImageURL(), wooliesItem, false).execute();
                }

                // Wait for a portion of threads to return, then continue atfer 50 milliseconds
                while (returnCount < wooliesItems.size()*waitPercentage) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Add any items from coles that are not at woolworths
                if (shouldSearchColes) {
                    for (Item colesItem : colesItems) {
                        // Only allow specific brands to be included
                        if (exclusiveColesBrands.contains(colesItem.getBrand())) {
                            boolean found = false;
                            for (Item wooliesItem : wooliesItems) {
                                if (wooliesItem.getColesCode().equals(colesItem.getColesCode())) {
                                    found = true;
                                    break;
                                }
                            }
                            if (!found) {
                                // Add to items
                                wooliesItems.add(utils.getRandomNumber(0, wooliesItems.size()), colesItem);
                            }
                        }
                    }
                }

                // Collect Coles item images
                for (Item wooliesItem : wooliesItems) {
                    if (wooliesItem.getIsAtColes()) {
                        new ImageLoadTask(wooliesItem.getColesImageURL(), wooliesItem, true).execute();
                    }
                }

                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


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
                wooliesItems = new ArrayList<>(Arrays.asList(utils.searchWoolworths(term)));
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
                    if (item.getIsAtColes()) {
                        // Update searched items (wooliesItems) with coles information
                        wooliesItems.get(index).setAtColes(true);
                        wooliesItems.get(index).setColesPrice(item.getColesPrice());
                        wooliesItems.get(index).setColesCode(item.getColesCode());
                        wooliesItems.get(index).setColesWasPrice(item.getColesWasPrice());
                    }
                } else {
                    colesItems = utils.searchColes(term);
                }

                colesProcessing = false;
                returnCount++;

                // Shutdown the thread
                mExecutor.shutdown();
            }
        };
        // Execute the thread
        mExecutor.execute(backgroundRunnable);
    }


}
