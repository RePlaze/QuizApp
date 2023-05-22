package nazenov.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {
    private TextView balanceTextView;
    private final int STORE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        balanceTextView = findViewById(R.id.balanceTextView);

        findViewById(R.id.easyCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quiz
                Intent intent = new Intent(MainActivity.this, BasicQuiz.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.storePage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Store
                Intent intent = new Intent(MainActivity.this, Store.class);
                startActivityForResult(intent, STORE_REQUEST_CODE);
            }
        });
        updateBalance();
    }

    private void updateBalance() {
        // Balance
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int balance = sharedPreferences.getInt("balance", 0);
        balanceTextView.setText("Balance: " + balance);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STORE_REQUEST_CODE && resultCode == RESULT_OK) {
            updateBalance();
        }
    }

    @Override
    public void onBackPressed() {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.app_name)
                .setMessage("Do you want to exit the app?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
