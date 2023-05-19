// StoreAdapter
package nazenov.quizapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {
    private List<StoreItem> storeItems;
    private StoreAdapterListener listener;

    public StoreAdapter(List<StoreItem> storeItems, StoreAdapterListener listener) {
        this.storeItems = storeItems;
        this.listener = listener;
    }

    public interface StoreAdapterListener {
        void onItemPurchased(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StoreItem storeItem = storeItems.get(position);
        holder.itemNameTextView.setText(storeItem.getName());
        holder.itemPriceTextView.setText(String.valueOf(storeItem.getPrice()));
        holder.buyButton.setVisibility(storeItem.isPurchased() ? View.GONE : View.VISIBLE);
        holder.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemPurchased(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return storeItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImageView;
        private TextView itemNameTextView;
        private TextView itemPriceTextView;
        private Button buyButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImageView = itemView.findViewById(R.id.itemImageView);
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            itemPriceTextView = itemView.findViewById(R.id.itemPriceTextView);
            buyButton = itemView.findViewById(R.id.buyButton);
        }
    }
}
