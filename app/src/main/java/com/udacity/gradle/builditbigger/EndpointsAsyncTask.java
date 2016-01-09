package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.csabacsete.jokedisplaylibrary.JokeDisplayActivity;
import com.csabacsete.joketellingbackend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by Csabi on 29-Dec-15.
 */
public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context mContext;

    @Override
    protected String doInBackground(Context... params) {
        if (myApiService == null) {  // Only do this once
//            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
//                    new AndroidJsonFactory(), null)
//                    // options for running against local devappserver
//                    // - 10.0.2.2 is localhost's IP address in Android emulator
//                    // - turn off compression when running against local devappserver
//                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://udacitybuilditbigger-1185.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        mContext = params[0];

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.w("onPostExecute", result);
        Intent i = new Intent(mContext, JokeDisplayActivity.class);
        i.putExtra(JokeDisplayActivity.INTENT_EXTRA_JOKE, result);
        mContext.startActivity(i);
    }
}