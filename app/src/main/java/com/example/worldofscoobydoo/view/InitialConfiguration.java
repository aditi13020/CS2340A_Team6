package com.example.worldofscoobydoo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.worldofscoobydoo.R;

public class InitialConfiguration extends AppCompatActivity {
    private String name;
    private String sprite;
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
                break;
            case R.id.DaphneBtn:
                sprite = "daphne";
                break;
            case R.id.FredBtn:
                sprite = "fred";
                break;
            default:
            }

            EditText input = findViewById(R.id.editTextText);
            String inputName = input.getText().toString();
            name = inputName;

            if (!nameIsValid(inputName)) {
                nameInput.setError("Please enter a name");
            } else if (!setDifficulty) {
                nameInput.setError("Choose a difficulty.");
            } else if (!characterIsValid(sprite)) {
                nameInput.setError("Choose a character.");
            } else {
                Intent game = new Intent(InitialConfiguration.this, GameActivity.class);
                game.putExtra("difficulty", difficulty);
                game.putExtra("name", name);
                game.putExtra("sprite", sprite);
                startActivity(game);
                finish();
            }
        });
    }
    public static boolean nameIsValid(String inputName) {
        if (inputName == null || inputName.isEmpty() || (inputName.trim().length() == 0)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean difficultyIsValid(double difficulty) {
        if (!(difficulty == 1 || difficulty == 0.75 || difficulty == 0.5)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean characterIsValid(String sprite) {
        if (!(sprite == "scooby" || sprite == "daphne" || sprite == "fred")) {
            return false;
        } else {
            return true;
        }
    }
}