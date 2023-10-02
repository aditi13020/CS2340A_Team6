package com.example.worldofscoobydoo;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LeaderBoard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);

        Button back = findViewById(R.id.backButton);
        back.setOnClickListener(v -> {
            Intent backButton = new Intent(getApplicationContext(), EndScreen.class);
            startActivity(backButton);
        });
    }
}
