package com.example.worldofscoobydoo;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class EndScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endscreen);

        Button leaderButton = findViewById(R.id.finalLeaderboard);
        leaderButton.setOnClickListener(v -> {
            Intent leaderboard = new Intent(getApplicationContext(), LeaderBoard.class);
            startActivity(leaderboard);
        });

        Button restartButton = findViewById(R.id.playAgain);
        restartButton.setOnClickListener(v -> {
            Intent restart = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(restart);
        });
    }
}

