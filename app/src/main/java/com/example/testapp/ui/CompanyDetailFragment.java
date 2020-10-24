package com.example.testapp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testapp.R;
import com.example.testapp.model.PaymentModel;
import com.example.testapp.model.ProductModel;
import com.example.testapp.utils.Helper;
import com.jakewharton.rxbinding4.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;

public class CompanyDetailFragment extends Fragment {

    private CircleImageView image, fieldIcon, fieldMoneyIcon;
    private TextView name, description, fieldHead, restrictNumberTxt;
    private EditText field, moneyField;

    private int productId;
    private String productName, productImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailed, container, false);
        name = view.findViewById(R.id.detail_company_name);
        image = view.findViewById(R.id.detail_company_image);
        field = view.findViewById(R.id.detail_field);
        fieldHead = view.findViewById(R.id.detail_field_head);
        fieldIcon = view.findViewById(R.id.detail_field_icon);
        moneyField = view.findViewById(R.id.detail_field_money);
        description = view.findViewById(R.id.detail_company_description);
        fieldMoneyIcon = view.findViewById(R.id.detail_field_money_icon);
        restrictNumberTxt = view.findViewById(R.id.restrict_number);

        Button payBtn = view.findViewById(R.id.detail_pay_btn);
        payBtn.setOnClickListener(v -> startPayment());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {

            this.productId = getArguments().getInt("productId");
            checkProductId(productId);

            ProductModel.Companies company = (ProductModel.Companies) getArguments().getSerializable("company");
            if (company != null) {
                updateUI(company);
            }
        }

    }

    private void updateUI(ProductModel.Companies company) {

        name.setText(company.getName());
        productName = company.getName();

        description.setText(company.getDescription());

        fieldHead.setText(company.getField_head());

        productImage = company.getImage();
        Helper.loadImage(name.getContext(), company.getImage(), image);

        Helper.loadImage(name.getContext(), company.getField_icon(), fieldIcon);

        Helper.loadImage(name.getContext(), company.getField_money_icon(), fieldMoneyIcon);

    }

    private void startPayment() {
        if (!field.getText().toString().isEmpty() && !moneyField.getText().toString().isEmpty()) {

            PaymentModel paymentModel = new PaymentModel();
            paymentModel.setAmount(moneyField.getText().toString());
            paymentModel.setProductName(productName);
            paymentModel.setImage(productImage);

            if (productId == 0)
                paymentModel.setNumber("5" + field.getText().toString());
            else
                paymentModel.setNumber(field.getText().toString());

            ((HomeActivity) name.getContext()).onPaymentSuccess(paymentModel);

        } else {
            Toast.makeText(name.getContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkProductId(int productId) {

        if (productId == 0) {
            restrictNumberTxt.setVisibility(View.VISIBLE);
        } else {
            restrictNumberTxt.setVisibility(View.GONE);
        }

        //0 - mobile
        //restrict - 8

        //1 - utility
        //restrict - 6

        //2 - bank
        //restrict - 11

        switch (productId) {

            case 0:
                setFieldRestriction(8);
                break;

            case 1:
                setFieldRestriction(6);
                break;

            case 2:
                setFieldRestriction(11);
                break;

        }
    }

    private void setFieldRestriction(int maxLength) {

        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(maxLength);
        field.setFilters(FilterArray);

    }
}
