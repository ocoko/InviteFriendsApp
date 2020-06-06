package com.example.invitefriends.data;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.invitefriends.MyListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.*;

public class EventManager {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    static int EVENTCOUNT = 0;

    private static List<Event> events;

    final MyListener myListener;

    public static void addWithId(Event e, String id) {
        e.setId(id);
        events.add(e);
    }

    public static void addItem(Event e) {
        events.add(e);
    }

    public static List<Event> getEventList() {
        return events;
    }

    public EventManager(final MyListener myListener) {

        this.myListener = myListener;

        events = new ArrayList<Event>();

/*
        db.collection("events")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots,
                                        @Nullable FirebaseFirestoreException e) {
                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                            switch (dc.getType()) {
                                case ADDED:

                                    Log.d("TAG", dc.getId() + " => " + dc.getData());
                                    EventManager.addWithId( dc.getDocument().toObject(Event.class), dc.getDocument().getId());

                                   // Message message = snapshots.toObject(Message.class);

                                    myListener.refreshList();
                                    break;
                            }
                        }
                    }
                });

 */

        db.collection("events")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());

                                Event newEvent = new Event();
                                newEvent.setId((String) document.getId());
                                newEvent.setAccepted((List<Long>) document.get("accepted"));
                                newEvent.setDate((Date) document.get("date"));
                                newEvent.setDeclined((List<Long>) document.get("declined"));
                                newEvent.setDescription((String) document.get("description"));
                                newEvent.setInvited((List<Long>) document.get("invited"));
                                newEvent.setLatitude((double) document.get("latitude"));
                                newEvent.setLongitude((double) document.get("longitude"));
                                newEvent.setOwner((String) document.get("owner"));
                                newEvent.setRequested((List<Long>) document.get("requested"));
                                newEvent.setTitle((String) document.get("title"));

                                addItem(newEvent);

                                //EventManager.addWithId(document.toObject(Event.class), document.getId());



                            }
                            myListener.refreshList();
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });


    }


    public void uploadEvent(final Event newEvent) {


        FirebaseFirestore.getInstance().collection("events").add(newEvent)
                .addOnSuccessListener(
                        new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                newEvent.setId(documentReference.getId());
                                Log.w("TAG", "Document successfully added ");
                            }
                        }
                )

                .addOnFailureListener(new OnFailureListener() {
                                          @Override
                                          public void onFailure(@NonNull Exception e) {
                                              Log.w("TAG", "Error adding document", e);
                                          }
                                      }
                );
    }


}
