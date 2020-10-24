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

public class CompaniesFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_companies, container, false);

        recyclerView = view.findViewById(R.id.companies_recycler);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            List<ProductModel.Companies> companies = (List<ProductModel.Companies>) getArguments().getSerializable("companies");
            loadRecycler(companies);
        }

    }

    private void loadRecycler(List<ProductModel.Companies> companies) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new CompaniesAdapter(companies, recyclerView.getContext()));
    }

    public interface CompaniesListener {

        void onChooseCompany(ProductModel.Companies company);

    }

}
