package com.example.invitefriends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.invitefriends.data.Event;
import com.example.invitefriends.data.EventManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.model.value.DoubleValue;

import java.util.Calendar;
import java.util.Date;

public class CreateActivity extends AppCompatActivity {

    Button btnSaveEvent;
    EventManager eventManager = MainActivity.eventManager;
    EditText etTitle;
    EditText etX;
    EditText etY;
    EditText etDescription;
    DatePicker dpDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        etTitle = findViewById(R.id.etTitle);
        etX = findViewById(R.id.etLocX);
        etY = findViewById(R.id.etlocY);
        etDescription = findViewById(R.id.etDescription);
        dpDate = findViewById(R.id.dpDate);

        btnSaveEvent = findViewById(R.id.btnSave);
        btnSaveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int year = dpDate.getYear();
                int month = dpDate.getMonth();
                int day = dpDate.getDayOfMonth();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);

                Date eventDate = calendar.getTime();

                final Event newEvent = new Event(
                        FirebaseAuth.getInstance().getCurrentUser().getUid(),
                        etTitle.getText().toString(),
                        eventDate,
                        Double.valueOf(etX.getText().toString()),
                        Double.valueOf(etY.getText().toString()),
                        etDescription.getText().toString(),
                        null,
                        null,
                        null,
                        null

                );
                eventManager.uploadEvent(newEvent);


            }
        });
    }
}