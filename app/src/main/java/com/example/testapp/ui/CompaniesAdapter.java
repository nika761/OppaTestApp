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

public class CompaniesAdapter extends RecyclerView.Adapter<CompaniesAdapter.CompaniesHolder> {

    private final List<ProductModel.Companies> companies;

    private final CompaniesFragment.CompaniesListener listener;

    public CompaniesAdapter(List<ProductModel.Companies> companies, Context context) {
        this.companies = companies;
        this.listener = (CompaniesFragment.CompaniesListener) context;
    }

    @NonNull
    @Override
    public CompaniesAdapter.CompaniesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CompaniesHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_companies, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CompaniesAdapter.CompaniesHolder holder, int position) {

        holder.bindView(position);

    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    public class CompaniesHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView name;

        public CompaniesHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.item_company_image);
            name = itemView.findViewById(R.id.item_company_name);

            itemView.setOnClickListener(v -> listener.onChooseCompany(companies.get(getLayoutPosition())));

        }

        void bindView(int position) {

            Helper.loadImage(name.getContext(), companies.get(position).getImage(), image);

            name.setText(companies.get(position).getName());

        }
    }
}
