package com.example.testapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductModel implements Serializable {

    @Expose
    @SerializedName("companies")
    private List<Companies> companies;

    @Expose
    @SerializedName("product_id")
    private int product_id;
    //0 - mobile
    //1 - utility
    //2 - bank

    @Expose
    @SerializedName("product_image")
    private String product_image;

    @Expose
    @SerializedName("product_name")
    private String product_name;

    public List<Companies> getCompanies() {
        return companies;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_image() {
        return product_image;
    }

    public String getProduct_name() {
        return product_name;
    }

    public static class Companies implements Serializable {
        @Expose
        @SerializedName("field_icon")
        private String field_icon;

        @Expose
        @SerializedName("field_money_icon")
        private String field_money_icon;

        @Expose
        @SerializedName("field_head")
        private String field_head;

        @Expose
        @SerializedName("description")
        private String description;

        @Expose
        @SerializedName("image")
        private String image;

        @Expose
        @SerializedName("name")
        private String name;

        public String getField_money_icon() {
            return field_money_icon;
        }

        public String getField_icon() {
            return field_icon;
        }

        public String getField_head() {
            return field_head;
        }

        public String getDescription() {
            return description;
        }

        public String getImage() {
            return image;
        }

        public String getName() {
            return name;
        }
    }
}
