package nazenov.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.card.MaterialCardView;

public class ResultActivity extends AppCompatActivity {

    MaterialCardView home;
    TextView correctt, wrongt, resultinfo, resultscore;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        home = findViewById(R.id.returnHome);
        correctt = findViewById(R.id.correctScore);
        wrongt = findViewById(R.id.wrongScore);
        resultinfo = findViewById(R.id.resultInfo);
        resultscore = findViewById(R.id.resultScore);

        int correct = getIntent().getIntExtra("correct", 0);
        int wrong = getIntent().getIntExtra("wrong", 0);
        double score = (correct * 100.0) / (correct + wrong);
        int balance = sharedPreferences.getInt("balance", 0);
        balance += correct;

        // Store the updated balance
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("balance", balance);
        editor.apply();

        correctt.setText(""+correct);
        wrongt.setText(""+wrong);
        resultscore.setText(""+score + " %");

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ResultActivity.this, MainActivity.class));
        finish();
    }
}
