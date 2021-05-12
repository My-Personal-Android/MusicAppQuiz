package com.musicquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String GAME_FINISHED = "game_finished";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView highScoreTextView = (TextView) findViewById(R.id.highscoreText);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quizIntent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(quizIntent);
            }
        });

        int highScore = QuizUtils.getHighScore(this);
        int maxScore = Sample.getAllSampleIDs(this).size() - 1; // this is because of ARRAY we minus 1

        String highScoreText = getString(R.string.high_score, highScore, maxScore);
        highScoreTextView.setText(highScoreText);

        // If the game is over, show the game finished UI.
        if(getIntent().hasExtra(GAME_FINISHED)){
            TextView gameFinishedTextView = (TextView) findViewById(R.id.gameResult);
            TextView yourScoreTextView = (TextView) findViewById(R.id.resultScore);

            Integer yourScore = QuizUtils.getCurrentScore(this);
            String yourScoreText = getString(R.string.score_result, yourScore, maxScore);
            yourScoreTextView.setText(yourScoreText);

            gameFinishedTextView.setVisibility(View.VISIBLE);
            yourScoreTextView.setVisibility(View.VISIBLE);
        }
    }

}