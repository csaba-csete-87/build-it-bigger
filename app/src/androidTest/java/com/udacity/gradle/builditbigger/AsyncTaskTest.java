package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.TimeUnit;

/**
 * Created by Csabi on 29-Dec-15.
 */
public class AsyncTaskTest extends AndroidTestCase {

    /**
     * Compliments to @JeremyAtUdacity for the code in this test
     *
     * https://discussions.udacity.com/t/writing-tests-for-async-task/25482/9
     */
    public final void testSuccessJokeReturn() {
        String joke = null;
        try {
            EndpointsAsyncTask jokeTask = new EndpointsAsyncTask();
            jokeTask.execute(mContext);
            joke = jokeTask.get(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            fail("Timed out");
        }

        assertNotNull("No joke was supplied by AsyncTask", joke);
    }

}
