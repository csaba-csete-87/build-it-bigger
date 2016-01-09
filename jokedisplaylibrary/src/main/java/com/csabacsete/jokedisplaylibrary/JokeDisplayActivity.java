package com.csabacsete.jokedisplaylibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    public static final String INTENT_EXTRA_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView t = new TextView(this);
        t.setGravity(Gravity.CENTER);

        String joke = getIntent().getStringExtra(INTENT_EXTRA_JOKE);
        if (joke != null && !TextUtils.isEmpty(joke)) {
            t.setText(joke);
        } else {
            t.setText(R.string.no_joke);
        }
        setContentView(t);
    }
}
