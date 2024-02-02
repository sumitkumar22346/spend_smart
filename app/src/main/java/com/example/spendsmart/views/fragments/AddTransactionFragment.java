package com.example.spendsmart.views.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spendsmart.R;
import com.example.spendsmart.adapters.CategoryAdapter;
import com.example.spendsmart.adapters.MotsAdapter;
import com.example.spendsmart.databinding.FragmentAddTransactionBinding;
import com.example.spendsmart.databinding.ListDialogBinding;
import com.example.spendsmart.models.Category;
import com.example.spendsmart.models.Mot;
import com.example.spendsmart.utils.Constants;
import com.example.spendsmart.utils.Helper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddTransactionFragment extends BottomSheetDialogFragment {


    public AddTransactionFragment(){
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentAddTransactionBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddTransactionBinding.inflate(inflater);

        binding.incomeBtn.setOnClickListener(view-> {
            binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.income_selector));
            binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
            binding.expenseBtn.setTextColor(getContext().getColor(R.color.black_2));
            binding.incomeBtn.setTextColor(getContext().getColor(R.color.green));
        });

        binding.expenseBtn.setOnClickListener(view-> {
            binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
            binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.expense_selector));
            binding.expenseBtn.setTextColor(getContext().getColor(R.color.red));
            binding.incomeBtn.setTextColor(getContext().getColor(R.color.black_2));
        });

        binding.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext());
                datePickerDialog.setOnDateSetListener((datePicker, i, i1, i2) -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
                    calendar.set(Calendar.MONTH, datePicker.getMonth());
                    calendar.set(Calendar.YEAR, datePicker.getYear());

                    //SimpleDateFormat dateFormat  = new SimpleDateFormat("dd MMMM,yyyy");
                    String dateToShow = Helper.formatDate(calendar.getTime());

                    binding.date.setText(dateToShow);

                });
                datePickerDialog.show();
            }
        });

        binding.category.setOnClickListener(c-> {
            ListDialogBinding dialogBinding = ListDialogBinding.inflate(inflater);
            AlertDialog categoryDialog = new AlertDialog.Builder(getContext()).create();
            categoryDialog.setView(dialogBinding.getRoot());



            CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), Constants.categories, new CategoryAdapter.CategoryCLickListener() {
                @Override
                public void onCategoryClicked(Category category) {
                    binding.category.setText(category.getCategoryName());
                    categoryDialog.dismiss();
                }
            });
            dialogBinding.recycleView.setLayoutManager(new GridLayoutManager(getContext(),3));
            dialogBinding.recycleView.setAdapter(categoryAdapter);

            categoryDialog.show();
        });

        binding.mot.setOnClickListener(c-> {
            ListDialogBinding dialogBinding = ListDialogBinding.inflate(inflater);
            AlertDialog motsDialog = new AlertDialog.Builder(getContext()).create();
            motsDialog.setView(dialogBinding.getRoot());

            ArrayList<Mot> mots = new ArrayList<>();
            mots.add(new Mot(0, "Pocket_money"));
            mots.add(new Mot(0, "Online"));
            mots.add(new Mot(0, "Cash"));
            mots.add(new Mot(0, "Other"));

            MotsAdapter adapter = new MotsAdapter(getContext(), mots, new MotsAdapter.MotsClickListener() {
                @Override
                public void onMotSelected(Mot mot) {
                    binding.mot.setText(mot.getMotName());
                    motsDialog.dismiss();
                }
            });
            dialogBinding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
            dialogBinding.recycleView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
            dialogBinding.recycleView.setAdapter(adapter);


            motsDialog.show();

        });
        return binding.getRoot();
    }
}