package com.example.trolley;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.trolley.Fetch;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

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
    public void fetchItemTest() {
        Fetch fetch = new Fetch();
        fetch.fetchItem("apples");
    }

    @Test
    public void fetchWooliesItemByCode() {
        Fetch fetch = new Fetch();
        fetch.fetchWooliesItemByCode(205300);
    }
}