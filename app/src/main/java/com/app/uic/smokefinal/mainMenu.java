package com.app.uic.smokefinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class mainMenu extends AppCompatActivity {

    private static final String TAG = "mainMenu";

    private Button Btn_confirmation,Btn_status;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Btn_confirmation = (Button) findViewById(R.id.Btn_confirmation);
        Btn_status = (Button) findViewById(R.id.Btn_status);

        Btn_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainMenu.this, SensorStatus.class);
                startActivity(intent);
            }
        });


    }
}
