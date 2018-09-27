package com.app.uic.smokefinal;

import android.hardware.Sensor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SensorStatus extends AppCompatActivity {

    private static final String TAG = "SensorStatus";


    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private String dataID;

    private ListView mListview;
    private TextView txtview_name, txtview_data, txtview_pub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_status);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        mListview = findViewById(R.id.listview);


        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser data = mAuth.getCurrentUser();
        dataID = data.getUid();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            SensorInformation uInfo = new SensorInformation();

            //    uInfo.setCoreid(ds.child(userID).getValue(SensorInformation.class).setCoreid()); //set the coreid
            uInfo.setData(ds.child(dataID).getValue(SensorInformation.class).getData()); //set the data
            uInfo.setEvent(ds.child(dataID).getValue(SensorInformation.class).getEvent()); //set the event
            uInfo.setPublished_at(ds.child(dataID).getValue(SensorInformation.class).getPublished_at());//set the publish date
            //display all the information
            //   Log.d(TAG, "showData: Coreid: " + uInfo.getCoreid());
            Log.d(TAG, "showData: Data: " + uInfo.getData());
            Log.d(TAG, "showData: Event: " + uInfo.getEvent());
            Log.d(TAG, "showData: Date Published: " + uInfo.getPublished_at());


            ArrayList<String> array = new ArrayList<>();
            //     array.add(uInfo.getCoreid());
            array.add(uInfo.getData());
            array.add(uInfo.getEvent());
            array.add(uInfo.getPublished_at());
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
            mListview.setAdapter(adapter);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
