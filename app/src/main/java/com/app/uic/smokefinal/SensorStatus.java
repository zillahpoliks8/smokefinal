package com.app.uic.smokefinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SensorStatus extends AppCompatActivity {

    ListView lv;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_status);

        lv = (ListView)findViewById(R.id.listView);
        Query query = FirebaseDatabase.getInstance().getReference().child("Data");

        FirebaseListOptions<Data> options = new FirebaseListOptions.Builder<Data>()
                .setLayout(R.layout.data)
                .setQuery(query,Data.class)
                .build();


        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView coreid = v.findViewById(R.id.coreid);
                TextView data = v.findViewById(R.id.data);
                TextView event = v.findViewById(R.id.event);
                TextView location = v.findViewById(R.id.location);
                TextView published_at = v.findViewById(R.id.published_at);

                Data dta = (Data)model;

                coreid.setText("Core ID: "+dta.getCoreid().toString());
                data.setText("\nData: "+dta.getData().toString());
                event.setText("\nEvent: "+dta.getEvent().toString());
                location.setText("\nLocation: "+dta.getLocation().toString());
                published_at.setText("\nPublished at: "+dta.getPublished_at().toString());
            }
        };
        lv.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
