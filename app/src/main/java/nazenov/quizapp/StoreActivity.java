package nazenov.quizapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity implements StoreAdapter.StoreAdapterListener {
    private RecyclerView recyclerView;
    private StoreAdapter adapter;
    private List<StoreItem> storeItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        recyclerView = findViewById(R.id.storeRecyclerView);

        // Set up the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create a list of store items
        storeItems = generateStoreItems();

        // Create an instance of the StoreAdapter
        adapter = new StoreAdapter(storeItems, this);

        // Set the adapter on the RecyclerView
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemPurchased(int position) {
        StoreItem storeItem = storeItems.get(position);
        storeItem.setPurchased(true);
        adapter.notifyItemChanged(position);

        // Display a toast message
        Toast.makeText(this, "Item purchased: " + storeItem.getName(), Toast.LENGTH_SHORT).show();
    }

    private List<StoreItem> generateStoreItems() {
        List<StoreItem> storeItems = new ArrayList<>();

        storeItems.add(new StoreItem("Item 1", 10, false));
        storeItems.add(new StoreItem("Item 2", 20, false));
        storeItems.add(new StoreItem("Item 3", 30, true));
        storeItems.add(new StoreItem("Item 4", 40, false));
        storeItems.add(new StoreItem("Item 5", 50, true));

        return storeItems;
    }
}
