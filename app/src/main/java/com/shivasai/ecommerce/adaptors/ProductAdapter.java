package com.shivasai.ecommerce.adaptors;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shivasai.ecommerce.R;
import com.shivasai.ecommerce.Service.ApiClient;
import com.shivasai.ecommerce.Service.productAPIService.product_ApiService;
import com.shivasai.ecommerce.models.productModel.ProductGetModel;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<ProductGetModel> productList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onUpdateClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public ProductAdapter(List<ProductGetModel> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductGetModel product = productList.get(position);
        holder.productName.setText(product.getProductName());
        holder.productPrice.setText(String.valueOf(product.getProductPrice()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice;
        Button updateButton, deleteButton;

        public ProductViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            updateButton = itemView.findViewById(R.id.updateButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);

            product_ApiService apiInterface = ApiClient.getClient().create(product_ApiService.class);




            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onUpdateClick(position);
                        }
                    }
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }
}

