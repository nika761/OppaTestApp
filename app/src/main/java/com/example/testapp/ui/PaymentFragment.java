package com.example.testapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testapp.R;
import com.example.testapp.model.PaymentModel;
import com.example.testapp.utils.Helper;

import java.text.MessageFormat;

import de.hdodenhof.circleimageview.CircleImageView;

public class PaymentFragment extends Fragment {
    private CircleImageView image;
    private TextView name, amount, number;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        image = view.findViewById(R.id.payment_image);
        name = view.findViewById(R.id.payment_name);
        amount = view.findViewById(R.id.payment_amount);
        number = view.findViewById(R.id.payment_number);

        Button homeBtn = view.findViewById(R.id.payment_btn);
        homeBtn.setOnClickListener(v -> startHomeActivity());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {

            PaymentModel paymentModel = (PaymentModel) getArguments().getSerializable("current_payment");

            if (paymentModel != null) {
                updateUI(paymentModel);
            }

        }
    }

    private void updateUI(PaymentModel paymentModel) {
        Helper.loadImage(name.getContext(), paymentModel.getImage(), image);
        name.setText(paymentModel.getProductName());
        amount.setText(MessageFormat.format("გადახდილი თანხა {0}", paymentModel.getAmount()));
        number.setText(MessageFormat.format("ნომერი {0}", paymentModel.getNumber()));
    }

    private void startHomeActivity() {
        Intent intent = new Intent(name.getContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        name.getContext().startActivity(intent);
    }

}
