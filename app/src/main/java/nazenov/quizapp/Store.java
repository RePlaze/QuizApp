// Store
package nazenov.quizapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Store extends AppCompatActivity implements StoreAdapter.StoreAdapterListener {
    private RecyclerView recyclerView;
    private StoreAdapter adapter;
    private List<StoreItem> storeItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        recyclerView = findViewById(R.id.storeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        storeItems = generateStoreItems();

        adapter = new StoreAdapter(storeItems, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemPurchased(int position) {
        StoreItem storeItem = storeItems.get(position);

        // Check if the item is already purchased
        if (!storeItem.isPurchased()) {
            // Update the purchase status
            storeItem.setPurchased(true);
            adapter.notifyItemChanged(position);

            // Display a toast message
            Toast.makeText(this, "Item purchased: " + storeItem.getName(), Toast.LENGTH_SHORT).show();
        } else {
            // Display a toast message indicating that the item is already purchased
            Toast.makeText(this, "Item already purchased: " + storeItem.getName(), Toast.LENGTH_SHORT).show();
        }
    }

    private List<StoreItem> generateStoreItems() {
        List<StoreItem> storeItems = new ArrayList<>();

        storeItems.add(new StoreItem("Dark", 10, false));
        storeItems.add(new StoreItem("Purple", 15, false));
        storeItems.add(new StoreItem("Blue", 20, false));

        return storeItems;
    }
}
