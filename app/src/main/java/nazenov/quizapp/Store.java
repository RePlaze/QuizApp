package nazenov.quizapp;

import android.os.Bundle;

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

        storeItems = new ArrayList<>();

        adapter = new StoreAdapter(storeItems, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemPurchased(int position) {
        // Handle item purchased event
    }
}
