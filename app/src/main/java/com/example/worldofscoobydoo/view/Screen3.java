package com.example.worldofscoobydoo.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.worldofscoobydoo.R;
import com.example.worldofscoobydoo.model.Player;
import com.example.worldofscoobydoo.viewModel.MovementFast;
import com.example.worldofscoobydoo.viewModel.MovementMedium;
import com.example.worldofscoobydoo.viewModel.MovementObservable;
import com.example.worldofscoobydoo.viewModel.MovementSlow;
import com.example.worldofscoobydoo.viewModel.MovementStrategy;
import com.example.worldofscoobydoo.viewModel.Renderer;

import java.util.ArrayList;

public class Screen3 extends AppCompatActivity {

    private String name;
    private CountDownTimer scoreCountdownTimer;
    private double difficulty;
    private String sprite;
    private int score;
    private TextView scoreTextView;
    private Handler handler = new Handler();
    private int screenWidth;
    private int screenHeight;
    private MovementStrategy movementStrategy;
    private Player player;
    private Renderer renderer;
    private MovementObservable movementObservable;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.screen3);
        player = Player.getPlayer();
        movementObservable = new MovementObservable();

        name = player.getName();
        difficulty = player.getDifficulty();
        sprite = player.getSprite();
        score = player.getScore();

        if (difficulty == .5) {
            movementStrategy = new MovementSlow(movementObservable);
        } else if (difficulty == .75) {
            movementStrategy = new MovementMedium(movementObservable);
        } else {
            movementStrategy = new MovementFast(movementObservable);
        }

        TextView nameReceiver = findViewById(R.id.textView_3);
        nameReceiver.setText(name);

        TextView difficultyReceiver = findViewById(R.id.health_status_3);
        String diff = String.valueOf(difficulty * 100.0);
        difficultyReceiver.setText(diff);

        ImageView spriteImg = findViewById(R.id.imageView_3);
        if ("scooby".equals(sprite)) {
            spriteImg.setImageResource(R.drawable.scooby_png);
        } else if ("daphne".equals(sprite)) {
            spriteImg.setImageResource(R.drawable.daphne_png);
        } else if ("fred".equals(sprite)) {
            spriteImg.setImageResource(R.drawable.fred_png);
        } else if ("velma".equals(sprite)) {
            spriteImg.setImageResource(R.drawable.velma_png);
        } else if ("shaggy".equals(sprite)) {
            spriteImg.setImageResource(R.drawable.shaggy_png);
        }

        renderer = new Renderer(spriteImg);
        movementObservable.addObserver(renderer);

        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;

        View user = findViewById(android.R.id.content);
        user.setFocusable(true);
        user.setFocusableInTouchMode(true);
        user.requestFocus();
        user.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int key, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    float futureX;
                    float futureY;
                    switch (key) {
                        case KeyEvent.KEYCODE_DPAD_UP:
                            futureX = spriteImg.getX();
                            futureY = spriteImg.getY() - 80;
                            if (!checkCollision(futureX, futureY)) {
                                movementStrategy.moveUp(spriteImg);
                            }
                            break;
                        case KeyEvent.KEYCODE_DPAD_DOWN:
                            futureX = spriteImg.getX();
                            futureY = spriteImg.getY() + 80;
                            if (!checkCollision(futureX, futureY)) {
                                movementStrategy.moveDown(spriteImg, screenHeight);
                            }
                            break;
                        case KeyEvent.KEYCODE_DPAD_LEFT:
                            futureX = spriteImg.getX() - 80;
                            futureY = spriteImg.getY();
                            if (!checkCollision(futureX, futureY)) {
                                movementStrategy.moveLeft(spriteImg);
                            }
                            break;
                        case KeyEvent.KEYCODE_DPAD_RIGHT:
                            futureX = spriteImg.getX() + 80;
                            futureY = spriteImg.getY();
                            if (!checkCollision(futureX, futureY)) {
                                movementStrategy.moveRight(spriteImg, screenWidth);
                            }
                            break;
                        default:
                    }
                    if (checkExit(spriteImg.getX(), spriteImg.getY())) {
                        // Cancel the countdown timer
                        if (scoreCountdownTimer != null) {
                            scoreCountdownTimer.cancel();
                        }

                        Intent intent = new Intent(Screen3.this, EndScreen.class);
                        player.setScore(score);
                        SharedPreferences pref = getSharedPreferences("PREFS", 0);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putInt("lastScore", player.getScore());
                        editor.putString("player", player.getName());
                        editor.apply();
                        startActivity(intent);
                    }
                    return true;
                }
                return false;
            }
        });

        // Initialize the score TextView
        scoreTextView = findViewById(R.id.scoreText);
        updateScore(score);

        // Define the score countdown timer
        scoreCountdownTimer = new CountDownTimer(score * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                score -= 1;
                updateScore(score);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(Screen3.this, EndScreen.class);
                player.setScore(0);
                startActivity(intent);
            }
        };

        // Start the score countdown timer
        scoreCountdownTimer.start();
    }

    private void updateScore(int sc) {
        scoreTextView.setText(String.valueOf(sc));
    }

    public boolean checkCollision(float x, float y) {
        ImageView spriteImg = findViewById(R.id.imageView_3);
        float playerX =  x;
        float playerY =  y;
        float playerWidth = spriteImg.getWidth();
        float playerHeight = spriteImg.getHeight();
        ArrayList<ImageView> collisionsList = new ArrayList<ImageView>();
        ImageView cb = findViewById(R.id.Border1);
        ImageView cb2 = findViewById(R.id.Border2);
        ImageView cb3 = findViewById(R.id.Border3);
        ImageView cb4 = findViewById(R.id.Border4);
        collisionsList.add(cb);
        collisionsList.add(cb2);
        collisionsList.add(cb3);
        collisionsList.add(cb4);
        for (ImageView collisionBox : collisionsList) {
            float objX = collisionBox.getX();
            float objY = collisionBox.getY();
            int objWidth = collisionBox.getWidth();
            int objHeight = collisionBox.getHeight();
            if ((playerX + playerWidth >= objX) && (playerX <= objX + objWidth)
                    && (playerY + playerHeight >= objY) && (playerY <= objY + objHeight)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkExit(float x, float y) {
        ImageView spriteImg = findViewById(R.id.imageView_3);
        float playerX =  x;
        float playerY =  y;
        float playerWidth = spriteImg.getWidth();
        float playerHeight = spriteImg.getHeight();
        ImageView exitScreen1 = findViewById(R.id.exit_screen3);
        float objX = exitScreen1.getX();
        float objY = exitScreen1.getY();
        int objWidth = exitScreen1.getWidth();
        int objHeight = exitScreen1.getHeight();
        if ((playerX + playerWidth >= objX) && (playerX <= objX + objWidth) && (playerY
                + playerHeight >= objY) && (playerY <= objY + objHeight)) {
            return true;
        }
        return false;
    }
}