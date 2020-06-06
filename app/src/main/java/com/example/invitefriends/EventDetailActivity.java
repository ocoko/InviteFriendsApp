package com.example.invitefriends;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EventDetailActivity extends AppCompatActivity {


    public static final String ARG_ITEM_ID = "item_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
    }
}