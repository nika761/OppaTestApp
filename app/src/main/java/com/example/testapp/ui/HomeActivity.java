package com.example.testapp.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testapp.R;
import com.example.testapp.model.PaymentModel;
import com.example.testapp.model.ProductModel;
import com.example.testapp.utils.Helper;

import java.io.Serializable;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements HomeListener,
        ProductsFragment.ProductsListener, CompaniesFragment.CompaniesListener {

    private int productId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initUI();

        getProducts();

    }

    private void getProducts() {

        HomePresenter presenter = new HomePresenter(this);
        presenter.getProducts();

    }

    private void initUI() {

        CircleImageView imageView = findViewById(R.id.products_toolbar_icon);

        Helper.loadImage(this, "https://play-lh.googleusercontent.com/5GnTY1nDbJZjqa6n9sSJqXeY4zNbBWAFFU0KPL4YXVFc_s9ZKUtpEcWtB6IHKWCosrc=s180-rw", imageView);

    }

    @Override
    public void onGetProducts(List<ProductModel> products) {

        Bundle productData = new Bundle();
        productData.putSerializable("products", (Serializable) products);

        Helper.loadFragment(new ProductsFragment(), productData, R.id.fragment_container, false, this);

    }

    @Override
    public void onGetError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChooseProduct(List<ProductModel.Companies> companies, int productId) {

        this.productId = productId;

        Bundle companiesData = new Bundle();
        companiesData.putSerializable("companies", (Serializable) companies);

        Helper.loadFragment(new CompaniesFragment(), companiesData, R.id.fragment_container, true, this);

    }

    @Override
    public void onChooseCompany(ProductModel.Companies company) {

        Bundle currentCompanyData = new Bundle();

        currentCompanyData.putSerializable("company", company);
        currentCompanyData.putInt("productId", productId);

        Helper.loadFragment(new CompanyDetailFragment(), currentCompanyData, R.id.fragment_container, true, this);

    }

    public void onPaymentSuccess(PaymentModel currentPayment) {

        Bundle paymentFragmentData = new Bundle();
        paymentFragmentData.putSerializable("current_payment", currentPayment);

        Helper.loadFragment(new PaymentFragment(), paymentFragmentData, R.id.fragment_container, true, this);
    }
}

