package com.example.productinformation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter {

    private final List<Product> products;

    public ProductAdapter(List<Product> products){
        this.products = products;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product product = products.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.imageTextView.setText(product.getImage());
        viewHolder.nameTextView.setText(product.getName());
        viewHolder.descriptionTextView.setText(product.getDescription());
        viewHolder.priceTextView.setText("" + product.getPrice());
        viewHolder.sellerTextView.setText(product.getSeller());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView imageTextView;
        public TextView nameTextView;
        public TextView descriptionTextView;
        public TextView priceTextView;
        public TextView sellerTextView;

        public ViewHolder(View itemView){
            super(itemView);
            imageTextView = itemView.findViewById(R.id.product_image);
            nameTextView = itemView.findViewById(R.id.product_name);
            descriptionTextView = itemView.findViewById(R.id.product_description);
            priceTextView = itemView.findViewById(R.id.product_price);
            sellerTextView = itemView.findViewById(R.id.product_seller);
        }
    }
}
