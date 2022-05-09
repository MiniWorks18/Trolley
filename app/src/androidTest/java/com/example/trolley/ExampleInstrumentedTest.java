package com.example.trolley;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.trolley", appContext.getPackageName());
    }

    @Test
    public void fetchWooliesItemByCode() {
        Utils utils = new Utils();
        Item item = utils.fetchWooliesItemByCode(205300);
        assertEquals(item.getBarcode(), Long.parseLong("9316090023906"));
    }

    @Test
    public void fetchColesItemByBarcode() {
        Utils utils = new Utils();
        Item[] items = utils.searchColes("9310645092133");
        assertEquals(items[0].getName(), "Copy Paper A4");
    }

    @Test
    public void searchColes() {
        Utils utils = new Utils();
        Item[] items = utils.searchColes("fish");
        assertEquals(items[0].getName(), "Fresh Tasmanian Salmon Portions Skin On");
    }

    @Test
    public void searchWoolworths() {
        Utils utils = new Utils();
        Item[] items = utils.searchWoolworths("frozen");
        assertNotNull(items[0]);
        assertEquals(items[0].getBarcode(), Long.parseLong("9300633285562"));
    }

    @Test
    public void matchWoolworthsWithColes() {
        Utils utils = new Utils();
        Item[] wooliesItems = utils.searchWoolworths("dove soap");
        for (int i = 0; i < wooliesItems.length; i++) {
            Item newItem = utils.searchColes(""+wooliesItems[i].getBarcode())[0];
            if (newItem.getIsInStock()) {
                wooliesItems[i].setColesPrice(newItem.getColesPrice());
                wooliesItems[i].setAtColes(true);
            }
        }
    }

    // TODO Maybe see if this can actually work
//    @Test
//    public void searchThread() {
//        Fetch fetch = new Fetch();
//
//        try {
//            fetch.searchThread("frozen");
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException | RuntimeException e) {
//            e.printStackTrace();
//        }
//
//        assertNotEquals(MainActivity.searchedItems.length, 1);
//    }

}