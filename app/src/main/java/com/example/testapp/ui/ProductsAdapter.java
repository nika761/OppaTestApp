package com.example.testapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.model.ProductModel;
import com.example.testapp.utils.Helper;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsHolder> {

    private final List<ProductModel> products;

    private final ProductsFragment.ProductsListener listener;

    public ProductsAdapter(List<ProductModel> products, Context context) {
        this.products = products;
        this.listener = (ProductsFragment.ProductsListener) context;
    }

    @NonNull
    @Override
    public ProductsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_companies, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductsHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView name;

        public ProductsHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_company_image);
            name = itemView.findViewById(R.id.item_company_name);

            itemView.setOnClickListener(v -> listener.onChooseProduct(products.get(getLayoutPosition()).getCompanies(),
                    products.get(getLayoutPosition()).getProduct_id()));

        }

        void bindView(int position) {

            name.setText(products.get(position).getProduct_name());

            Helper.loadImage(name.getContext(), products.get(position).getProduct_image(), image);
        }

    }
}
