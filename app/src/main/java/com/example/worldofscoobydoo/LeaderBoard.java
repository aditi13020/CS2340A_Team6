package com.example.worldofscoobydoo;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LeaderBoard extends AppCompatActivity {
    Player player = Player.getPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);

        RecyclerView recyclerV = findViewById(R.id.mRecyclerView);
        setUpBoard();
        RecyclerAdapter adapter = new RecyclerAdapter(this, player.getHistoryOfNames(),
                player.getHistoryOfScores());
        recyclerV.setAdapter(adapter);
        recyclerV.setLayoutManager(new LinearLayoutManager(this));

        Button back = findViewById(R.id.backButton);
        back.setOnClickListener(v -> {
            Intent leaderboard1 = new Intent(getApplicationContext(), EndScreen.class);
            startActivity(leaderboard1);
        });
    }

    private void setUpBoard() {
        ArrayList<String> users = player.getHistoryOfNames();
        ArrayList<Integer> scoreHist = player.getHistoryOfScores();

        for (int i = 0; i < users.size(); i++) {

        }
    }
}
