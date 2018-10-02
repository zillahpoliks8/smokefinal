package com.app.uic.smokefinal;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Confirmation extends AppCompatActivity {

    private Button conf;
    private DatabaseReference mDatabase;
    int count = 0;


    TextView tv;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);


        conf = (Button) findViewById(R.id.conf_conf);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("Confirm_Count").setValue(count++);
            }
        });


        Query query = FirebaseDatabase.getInstance().getReference().child("Confirm_Count");

        FirebaseListOptions<Confirm> options = new FirebaseListOptions.Builder<Confirm>()
                .setLayout(R.layout.confirm)
                .setQuery(query, Confirm.class)
                .build();

        tv = (TextView) findViewById(R.id.tv_conf);

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView dta = v.findViewById(R.id.tv_conf);
                Confirm ddta = (Confirm) model;

                dta.setText("Number of Confirmations: " + ddta.getConfirm_Count().toString());
            }
        };
        tv.setAdapter(adapter);
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

//
//
//        conf = (Button) findViewById(R.id.conf_conf);
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//
//        conf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mDatabase.child("Confirm_Count").setValue(count++);
//            }
//        });
//    }

