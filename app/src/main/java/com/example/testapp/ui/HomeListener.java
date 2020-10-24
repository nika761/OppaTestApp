package com.example.testapp.ui;

import com.example.testapp.model.ProductModel;

import java.util.List;

public interface HomeListener {

    void onGetProducts(List<ProductModel> products);

    void onGetError(String message);
}
