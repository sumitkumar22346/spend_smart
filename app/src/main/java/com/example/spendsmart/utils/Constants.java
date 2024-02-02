package com.example.spendsmart.utils;

import com.example.spendsmart.R;
import com.example.spendsmart.models.Category;

import java.util.ArrayList;

public class Constants {
    public static String INCOME = "INCOME";
    public static String EXPENSE = "EXPENSE";

    public static ArrayList<Category> categories;

    public static void setCategories() {
        categories = new ArrayList<>();
        categories.add(new Category("Pocket_money", R.drawable.ic_pocketmoney, R.color.green_7));
        categories.add(new Category("Clothes", R.drawable.ic_clothes, R.color.blue_3));
        categories.add(new Category("Food", R.drawable.ic_food, R.color.yellow_2));
        categories.add(new Category("Fruits", R.drawable.ic_fruit, R.color.yellow));
        categories.add(new Category("Gadgets", R.drawable.ic_gadgets, R.color.purple_500));
        categories.add(new Category("Grocery", R.drawable.ic_grocery, R.color.orange));
        categories.add(new Category("Healthcare", R.drawable.ic_healthcare, R.color.red_3));
        categories.add(new Category("Loan", R.drawable.ic_loan1, R.color.red_2));
        categories.add(new Category("Movie", R.drawable.ic_movie, R.color.blue_1));
        categories.add(new Category("Online_shopping", R.drawable.ic_onlineshope, R.color.blue_5));
        categories.add(new Category("Travel", R.drawable.ic_travel, R.color.green_6));
        categories.add(new Category("Trip", R.drawable.ic_trip, R.color.green_5));
        categories.add(new Category("Stationary", R.drawable.ic_stationary, R.color.green_2));
        categories.add(new Category("Rent", R.drawable.ic_rent, R.color.red_7));
        categories.add(new Category("Personal_Spend", R.drawable.ic_personalspend, R.color.pink));
        categories.add(new Category("other", R.drawable.invoice, R.color.red_2));
        categories.add(new Category("miscellaneous", R.drawable.ic_miscellaneous, R.color.pink));
    }

    public static Category getCategoryDetails(String categoryName) {
        for (Category cat :
                categories) {
            if (cat.getCategoryName().equals(categoryName)) {
                return cat;
            }
        }
        return null;
    }

    public static int getMotsColor(String motName) {
        switch (motName) {
            case "Pocket_money":
                return R.color.blue_6;
            case "Cash":
                return R.color.pink_2;
            case "Online":
                return R.color.purple_3;
            case "Other":
                return R.color.green_3;
            default:
                return R.color.black_2;
        }

    }
}
