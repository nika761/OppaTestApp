package com.example.testapp.ui;

import androidx.annotation.NonNull;

import com.example.testapp.model.ProductModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter {

    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("products");

    private final HomeListener homeListener;

    public HomePresenter(HomeListener homeListener) {
        this.homeListener = homeListener;
    }

    void getProducts() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<ProductModel> products = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ProductModel productModel = dataSnapshot.getValue(ProductModel.class);
                    products.add(productModel);
                }

                if (products.size() != 0)
                    homeListener.onGetProducts(products);
                else
                    homeListener.onGetError("Size is 0");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                homeListener.onGetError(error.getMessage());
            }
        });
    }


}
