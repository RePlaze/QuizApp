package nazenov.quizapp;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Store extends AppCompatActivity implements View.OnClickListener {
    private TextView balanceTextView;
    private SharedPreferences sharedPreferences;
    private boolean isDarkItemPurchased;
    private boolean isBlueItemPurchased;
    private boolean isPurpleItemPurchased;

    private Button darkItemBuyButton;
    private Button darkDisableButton;
    private Button darkUseButton;
    private Button blueItemBuyButton;
    private Button blueUseButton;
    private Button blueDisableButton;

    private Button purpleItemBuyButton;
    private Button purpleUseButton;
    private Button purpleDisableButton;
    private ImageView cardBackgroundImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        balanceTextView = findViewById(R.id.balanceTextView);

        // Get shared preferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Load item status from shared preferences
        isDarkItemPurchased = sharedPreferences.getBoolean("isDarkItemPurchased", false);
        isBlueItemPurchased = sharedPreferences.getBoolean("isBlueItemPurchased", false);
        isPurpleItemPurchased = sharedPreferences.getBoolean("isPurpleItemPurchased", false);

        // Initialize views
        darkItemBuyButton = findViewById(R.id.darkItemBuyButton);
        darkUseButton = findViewById(R.id.darkUseButton);
        darkDisableButton = findViewById(R.id.darkDisableButton);

        blueItemBuyButton = findViewById(R.id.blueItemBuyButton);
        blueUseButton = findViewById(R.id.blueUseButton);
        blueDisableButton = findViewById(R.id.blueDisableButton);

        purpleItemBuyButton = findViewById(R.id.purpleItemBuyButton);
        purpleUseButton = findViewById(R.id.purpleUseButton);
        purpleDisableButton = findViewById(R.id.purpleDisableButton);

        cardBackgroundImageView = findViewById(R.id.cardBackgroundImageView);

        // Set click listeners
        darkItemBuyButton.setOnClickListener(this);
        darkUseButton.setOnClickListener(this);
        darkDisableButton.setOnClickListener(this);

        blueItemBuyButton.setOnClickListener(this);
        blueUseButton.setOnClickListener(this);
        blueDisableButton.setOnClickListener(this);

        purpleItemBuyButton.setOnClickListener(this);
        purpleUseButton.setOnClickListener(this);
        purpleDisableButton.setOnClickListener(this);

        // Update UI based on item purchase status
        updateUI();
        updateBalance();
    }

    private void updateUI() {
        if (isDarkItemPurchased) {
            darkItemBuyButton.setVisibility(View.GONE);
            darkUseButton.setVisibility(View.VISIBLE);
            darkDisableButton.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cardBackgroundImageView.setBackgroundColor(getColor(R.color.purple_card_background));
            }
        }
        if (isBlueItemPurchased) {
            blueItemBuyButton.setVisibility(View.GONE);
            blueUseButton.setVisibility(View.VISIBLE);
            blueDisableButton.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cardBackgroundImageView.setBackgroundColor(getColor(R.color.purple_card_background));
            }

        }
        if (isPurpleItemPurchased) {
            purpleItemBuyButton.setVisibility(View.GONE);
            purpleUseButton.setVisibility(View.VISIBLE);
            purpleDisableButton.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cardBackgroundImageView.setBackgroundColor(getColor(R.color.purple_card_background));
            }
        }
    }

    private void performPurchase(final String item, final int itemPrice) {
        final int balance = sharedPreferences.getInt("balance", 0);

        if (balance < itemPrice) {
            showInsufficientBalanceDialog();
        } else {
            showConfirmationDialog(item, itemPrice);
        }
    }

    private void showInsufficientBalanceDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Insufficient Balance")
                .setMessage("You don't have enough balance to make this purchase.")
                .setPositiveButton("OK", null)
                .show();
    }

    private void showConfirmationDialog(final String item, final int itemPrice) {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Confirm Purchase")
                .setMessage("Are you sure you want to buy this item?")
                .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        makePurchase(item, itemPrice);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void makePurchase(String item, int itemPrice) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int balance = sharedPreferences.getInt("balance", 0);

        switch (item) {
            case "dark":
                isDarkItemPurchased = true;
                editor.putBoolean("isDarkItemPurchased", true);
                darkItemBuyButton.setVisibility(View.GONE);
                darkUseButton.setVisibility(View.VISIBLE);
                darkDisableButton.setVisibility(View.GONE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cardBackgroundImageView.setBackgroundColor(getColor(R.color.purple_card_background));
                }
                break;
            case "blue":
                isBlueItemPurchased = true;
                editor.putBoolean("isBlueItemPurchased", true);
                blueItemBuyButton.setVisibility(View.GONE);
                blueUseButton.setVisibility(View.VISIBLE);
                blueDisableButton.setVisibility(View.GONE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cardBackgroundImageView.setBackgroundColor(getColor(R.color.purple_card_background));
                }
                break;
            case "purple":
                isPurpleItemPurchased = true;
                editor.putBoolean("isPurpleItemPurchased", true);
                purpleItemBuyButton.setVisibility(View.GONE);
                purpleUseButton.setVisibility(View.VISIBLE);
                purpleDisableButton.setVisibility(View.GONE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cardBackgroundImageView.setBackgroundColor(getColor(R.color.purple_card_background));
                }
                break;
        }

        balance -= itemPrice;
        editor.putInt("balance", balance);
        editor.apply();
        updateBalance();
    }

    private void usePurpleItem() {
        // Handle "Use" functionality for purple item
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cardBackgroundImageView.setBackgroundColor(getColor(R.color.purple_card_background));
        }
        purpleUseButton.setVisibility(View.GONE);
        purpleDisableButton.setVisibility(View.VISIBLE);
    }

    private void disablePurpleItem() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cardBackgroundImageView.setBackgroundColor(getColor(R.color.card_background));
        }
        purpleUseButton.setVisibility(View.VISIBLE);
        purpleDisableButton.setVisibility(View.GONE);
    }

    private void useDarkItem() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cardBackgroundImageView.setBackgroundColor(getColor(R.color.dark_card_background));
        }
        darkUseButton.setVisibility(View.GONE);
        darkDisableButton.setVisibility(View.VISIBLE);
    }

    private void disableDarkItem() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            cardBackgroundImageView.setBackgroundColor(getColor(R.color.card_background));
        darkUseButton.setVisibility(View.VISIBLE);
        darkDisableButton.setVisibility(View.GONE);
    }

    private void useBlueItem() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cardBackgroundImageView.setBackgroundColor(getColor(R.color.blue_card_background));
        }
        blueUseButton.setVisibility(View.GONE);
        blueDisableButton.setVisibility(View.VISIBLE);
    }

    private void disableBlueItem() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cardBackgroundImageView.setBackgroundColor(getColor(R.color.card_background));
        }
        blueUseButton.setVisibility(View.VISIBLE);
        blueDisableButton.setVisibility(View.GONE);
    }

    private void updateBalance() {
        int balance = sharedPreferences.getInt("balance", 0);
        balanceTextView.setText("Balance: " + balance);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.darkItemBuyButton:
                performPurchase("dark", 10);
                break;
            case R.id.blueItemBuyButton:
                performPurchase("blue", 15);
                break;
            case R.id.purpleItemBuyButton:
                performPurchase("purple", 20);
                break;
            case R.id.darkUseButton:
                useDarkItem();
                break;
            case R.id.darkDisableButton:
                disableDarkItem();
                break;
            case R.id.blueUseButton:
                useBlueItem();
                break;
            case R.id.blueDisableButton:
                disableBlueItem();
                break;
            case R.id.purpleUseButton:
                usePurpleItem();
                break;
            case R.id.purpleDisableButton:
                disablePurpleItem();
                break;
        }
    }
}
