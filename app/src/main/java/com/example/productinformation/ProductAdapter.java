package com.example.productinformation;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * RecyclerView adapter for a product
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private final List<Product> products;
    private final boolean clickable;
    private final SelectedProducts selectedProducts;

    /**
     * If a selected products object is passed in the constructor, we want the displayed products
     * to be selectable(MainActivity)
     * @param products
     * @param selectedProducts
     */
    public ProductAdapter(List<Product> products, SelectedProducts selectedProducts){
        this.products = products;
        this.clickable = true;
        this.selectedProducts = selectedProducts;
    }

    /**
     * If only a list of products is passed to the constructor, we do not want any of the products
     * to be selectable(EmailerActivity)
     * @param products
     */
    public ProductAdapter(List<Product> products){
        this.products = products;
        this.clickable = false;
        this.selectedProducts = new SelectedProducts();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);

        holder.imageTextView.setText(product.getImage());
        holder.nameTextView.setText(product.getName());
        holder.descriptionTextView.setText(product.getDescription());
        holder.priceTextView.setText("" + product.getPrice());
        holder.sellerTextView.setText(product.getSeller());
        if(clickable) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.selected = !holder.selected;
                    if(holder.selected) {
                        view.setBackgroundColor(Color.parseColor("#4CE48B"));
                        selectedProducts.addProduct(product);
                    } else {
                        view.setBackgroundColor(Color.parseColor("#999999"));
                        selectedProducts.removeProduct(product);
                    }
                }
            });
        }
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
        public boolean selected;

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
