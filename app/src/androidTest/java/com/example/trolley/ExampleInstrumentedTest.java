package com.example.trolley;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

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

//    @Test
//    public void testFetch() {
//        Fetch fetch = new Fetch();
//        Item apples = new Item("apples", 116969, 20.00);
//        ArrayList<Item> list = new ArrayList<>();
//        list.add(apples);
//        Log.println(1, "Item", apples.getName());
//        assertEquals(fetch.searchTerm("apples").get(0).getName(), apples.getName());
//    }

    @Test
    public void gitUserIdTest() {
        Fetch fetch = new Fetch();
        GitUser user = fetch.gitUser();
        assertEquals(user.getId(), 1022859);
    }

    @Test
    public void fetchWooliesItemByCode() {
        Fetch fetch = new Fetch();
        Item item = fetch.fetchWooliesItemByCode(205300);
        assertEquals(item.getBarcode(), Long.parseLong("9316090023906"));
    }

    @Test
    public void fetchColesItemByBarcode() {
        Fetch fetch = new Fetch();
        Item[] items = fetch.searchColes("9310645092133");
        assertEquals(items[0].getName(), "A4 Copy Paper");
    }

    @Test
    public void searchColes() {
        Fetch fetch = new Fetch();
        Item[] items = fetch.searchColes("fish");
        assertEquals(items[0].getName(), "Fresh Tasmanian Salmon Portions Skin On");
    }

    @Test
    public void searchWoolworths() {
        Fetch fetch = new Fetch();
        Item[] items = fetch.searchWoolworths("frozen");
        assertEquals(items[0].getBarcode(), Long.parseLong("9300633285562"));
    }

}