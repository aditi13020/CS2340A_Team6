package com.example.worldofscoobydoo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class InitialConfiguration extends AppCompatActivity {
    private String name;
    private String sprite;
    private Player player = Player.getPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // removes top bar title
        getSupportActionBar().hide(); // removes top bar
        setContentView(R.layout.initial_configuration); // sets to layout

        Button startBtn = findViewById(R.id.button); // continue button
        EditText nameInput = findViewById(R.id.editTextText); // Name Input

        startBtn.setOnClickListener(v -> {
            boolean setDifficulty = false;
            boolean setCharacter = false;
            double difficulty = 1;

            RadioGroup spriteRadioGroup = findViewById(R.id.spriteRadio);
            RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
            switch (difficultyRadioGroup.getCheckedRadioButtonId()) {
            case R.id.radioEasy:
                difficulty = 1;
                setDifficulty = true;
                break;
            case R.id.radioMedium:
                difficulty = 0.75;
                setDifficulty = true;
                break;
            case R.id.radioHard:
                difficulty = 0.5;
                setDifficulty = true;
                break;
            default:
            }

            switch (spriteRadioGroup.getCheckedRadioButtonId()) {
            case R.id.ScoobyBtn:
                sprite = "scooby";
                setCharacter = true;
                break;
            case R.id.DaphneBtn:
                sprite = "daphne";
                setCharacter = true;
                break;
            case R.id.FredBtn:
                sprite = "fred";
                setCharacter = true;
                break;
            default:
            }

            EditText input = findViewById(R.id.editTextText);
            String inputName = input.getText().toString();
            name = inputName;

            if (inputName == null || inputName.isEmpty() || (inputName.trim().length() == 0)) {
                nameInput.setError("Please enter a name");
            } else if (!setDifficulty) {
                nameInput.setError("Choose a difficulty.");
            } else if (!setCharacter) {
                nameInput.setError("Choose a character.");
            } else {
                Intent game = new Intent(InitialConfiguration.this, GameActivity.class);
                game.putExtra("difficulty", difficulty);
                // game.putExtra("name", name);
                player.setName(name);
                game.putExtra("sprite", sprite);
                startActivity(game);
                finish();
            }
        });
    }
}
