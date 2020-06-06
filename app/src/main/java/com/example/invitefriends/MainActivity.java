package com.example.invitefriends;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.invitefriends.data.Event;
import com.example.invitefriends.data.EventManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MyListener{

    public static EventManager eventManager;
    Button btnLogout;
    Button btnCreate;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    View recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = findViewById(R.id.logout);
        btnCreate = findViewById(R.id.createEvent);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToLogin = new Intent(MainActivity.this, Login.class);
                startActivity(intToLogin);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intToCreateEvent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intToCreateEvent);
            }
        });


        eventManager = new EventManager(this);

        recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;


    }



    @Override
    public void refreshList() {
        ((RecyclerView) recyclerView).setLayoutManager(new LinearLayoutManager(this));
        ((RecyclerView) recyclerView).setAdapter(new SimpleItemRecyclerViewAdapter(this, eventManager.getEventList()));

    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final MainActivity mParentActivity;
        private final List<Event> mEventList;

        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event item = (Event) view.getTag();

                    Context context = view.getContext();
                    Intent intent = new Intent(context, EventDetailActivity.class);
                    intent.putExtra(EventDetailActivity.ARG_ITEM_ID, item.getId());

                    context.startActivity(intent);

            }
        };

        SimpleItemRecyclerViewAdapter(MainActivity parent,List<Event> eventManager) {
            mEventList = eventManager;
            mParentActivity = parent;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_list_content, parent, false);
            return new ViewHolder(view);
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }

        @Override
        public int getItemCount() {
            return mEventList.size();
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            // holder.mIdView.setText((mValues.get(position).id).toString());
           // holder.mContentView.setText(mValues.get(position).title);

            holder.itemView.setTag(mEventList.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);



        }
    }


}
