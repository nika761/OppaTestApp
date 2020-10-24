package com.example.testapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.model.ProductModel;

import java.util.List;

public class ProductsFragment extends Fragment {

    private RecyclerView productsRecycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        productsRecycler = view.findViewById(R.id.products_recycler);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            List<ProductModel> products = (List<ProductModel>) getArguments().getSerializable("products");
            loadRecycler(products);
        }

    }

    private void loadRecycler(List<ProductModel> products) {
        productsRecycler.setLayoutManager(new LinearLayoutManager(productsRecycler.getContext()));
        productsRecycler.setAdapter(new ProductsAdapter(products, productsRecycler.getContext()));
    }

    public interface ProductsListener {

        void onChooseProduct(List<ProductModel.Companies> companies, int productId);

    }

}
