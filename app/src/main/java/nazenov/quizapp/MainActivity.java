// MainActivity
package nazenov.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView balanceTextView;
    private MaterialCardView easyCard;
    private MaterialCardView difficultCard;
    private MaterialCardView aboutCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        balanceTextView = findViewById(R.id.balanceTextView);
        easyCard = findViewById(R.id.easyCard);
        difficultCard = findViewById(R.id.difficultCard);
        aboutCard = findViewById(R.id.aboutCard);

        easyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quiz
                Intent intent = new Intent(MainActivity.this, BasicQuiz.class);
                startActivity(intent);
            }
        });

        difficultCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Store
                Intent intent = new Intent(MainActivity.this, Store.class);

                // Create test items
                List<StoreItem> storeItems = new ArrayList<>();
                storeItems.add(new StoreItem("Dark", 10, false));
                storeItems.add(new StoreItem("Purple", 15, false));
                storeItems.add(new StoreItem("Blue", 20, false));

                // Pass items to the Store activity
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("storeItems", new ArrayList<>(storeItems));
                intent.putExtras(bundle);
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
