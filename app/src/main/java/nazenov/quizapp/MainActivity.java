// MainActivity.java
package nazenov.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {
    private TextView balanceTextView;
    private CardView easyCard;
    private CardView storePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        balanceTextView = findViewById(R.id.balanceTextView);
        easyCard = findViewById(R.id.easyCard);
        storePage = findViewById(R.id.storePage);

        easyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quiz
                Intent intent = new Intent(MainActivity.this, BasicQuiz.class);
                startActivity(intent);
            }
        });

        storePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Store
                Intent intent = new Intent(MainActivity.this, Store.class);
                startActivity(intent);
            }
        });

        // Update the balance
        updateBalance();
    }

    // Back pop-up
    @Override
    public void onBackPressed() {
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
        materialAlertDialogBuilder.setTitle(R.string.app_name);
        materialAlertDialogBuilder.setMessage("Do you want to exit the app?");
        materialAlertDialogBuilder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        materialAlertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        materialAlertDialogBuilder.show();
    }

    private void updateBalance() {
        // Balance
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int balance = sharedPreferences.getInt("balance", 0);
        balanceTextView.setText("Balance: " + balance);
    }
}