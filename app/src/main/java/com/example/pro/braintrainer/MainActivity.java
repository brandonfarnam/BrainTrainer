package com.example.pro.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView pointTextView;
    TextView timeTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView sumTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestion = 0;
    RelativeLayout gameRelativeLayout;
    public void playAgain(View view) {
        score = 0 ;
        numberOfQuestion = 0;
        timeTextView.setText("30s");
        pointTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        new CountDownTimer(3100, 1000){

            @Override
            public void onTick(long l) {
                timeTextView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                timeTextView.setText("0s");
                resultTextView.setText("You score: " + Integer.toString(score)+ "/" + Integer.toString(numberOfQuestion));
                playAgainButton.setVisibility(View.VISIBLE);

            }
        }.start();

    }
    public void generateQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        int incorrectAnswer;
        answers.clear();
        for (int i = 0 ; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            }
            else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == (a + b)) {
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            resultTextView.setText("Correct!");
        }else {
            resultTextView.setText("Wrong!");
        }
        numberOfQuestion++;
        pointTextView.setText(Integer.toString(score)+ "/" + Integer.toString(numberOfQuestion));
        generateQuestion();
    }


    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgain));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointTextView = (TextView)findViewById(R.id.pointsTextView);
        timeTextView = (TextView)findViewById(R.id.timeTextView);
        playAgainButton = (Button)findViewById(R.id.playAgain);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);



    }

}
