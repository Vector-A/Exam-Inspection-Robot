package com.example.examinspection;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        SharedPreferences sp = getSharedPreferences("ip", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();

        final EditText cam1 = findViewById(R.id.editTextText6);
        final EditText cam2 = findViewById(R.id.editTextText1);
        final EditText robot = findViewById(R.id.editTextText7);
        final Button save = findViewById(R.id.button);

        cam1.setText(sp.getString("ip1", ""));
        cam2.setText(sp.getString("ip2", ""));
        robot.setText(sp.getString("RD", ""));


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.putString("ip1",  cam1.getText().toString());
                edit.putString("ip2",  cam2.getText().toString());
                edit.putString("RD",  robot.getText().toString());
                edit.apply();

                Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intent);
                Toast.makeText(MainActivity3.this, "IP successfully saved", Toast.LENGTH_SHORT).show();

            }
        });

    }
}