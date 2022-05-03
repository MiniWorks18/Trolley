package com.example.trolley;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Fetch extends MainActivity {


    // Create some member variables for the ExecutorService
    // and for the Handler that will update the UI from the main thread
    ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    Handler mHandler = new Handler(Looper.getMainLooper());

    // Create an interface to respond with the result after processing
    public interface OnProcessedListener {
        public void onProcessed(Item[] result);
    }

    // Method creates thread for async HTTP processing
    public void searchThread(String term){

        final OnProcessedListener listener = new OnProcessedListener(){
            @Override
            public void onProcessed(Item[] result){
                // Use the handler so we're not trying to update the UI from the search thread
                mHandler.post(new Runnable(){
                    @Override
                    public void run(){
                        // Call for the UI to be updated
                        updateUi(result);

                        // Shutdown the thread
                        mExecutor.shutdown();
                    }
                });
            }
        };

        Runnable backgroundRunnable = new Runnable(){
            @Override
            public void run(){
                // Perform background operation(s) and set the result(s)
                Utils utils = new Utils();
                Item[] items = utils.searchWoolworths(term);

                // Use the interface to pass along the result
                listener.onProcessed(items);
            }
        };
        // Execute the thread
        mExecutor.execute(backgroundRunnable);
    }

    // Update the UI with the new data
    private void updateUi(Item[] result) {
        // Update the data
        MainActivity.searchedItems = result;
        // Call for the UI to refresh
        MainActivity.updateAdapter();
    }


    // TODO Create a second method for threads to fetch coles API and contribute to the item information

}
