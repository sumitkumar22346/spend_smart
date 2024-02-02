package com.example.spendsmart.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;

import com.example.spendsmart.adapters.TransactionsAdapter;
import com.example.spendsmart.models.Transaction;
import com.example.spendsmart.utils.Constants;
import com.example.spendsmart.utils.Helper;
import com.example.spendsmart.viewmodels.MainViewModel;
import com.example.spendsmart.views.fragments.AddTransactionFragment;
import com.example.spendsmart.R;
import com.example.spendsmart.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Calendar calendar;


    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);


        setSupportActionBar(binding.toolBar);
        getSupportActionBar().setTitle("Transactions");


        Constants.setCategories();

        calendar = Calendar.getInstance();
        updateDate();

        binding.nextDateBtn.setOnClickListener(c-> {
            calendar.add(Calendar.DATE, 1);
            updateDate();
        });

        binding.previousDateBtn.setOnClickListener(c-> {
            calendar.add(Calendar.DATE, -1);
            updateDate();
        });

        binding.floatingActionButton.setOnClickListener(c->{
            new AddTransactionFragment().show(getSupportFragmentManager(), null);
        });


        binding.transactionsList.setLayoutManager(new LinearLayoutManager(this));

        viewModel.transaction.observe(this, new Observer<RealmResults<Transaction>>() {
            @Override
            public void onChanged(RealmResults<Transaction> transactions) {
                TransactionsAdapter transactionsAdapter = new TransactionsAdapter(MainActivity.this, transactions);

                binding.transactionsList.setAdapter(transactionsAdapter);

            }
        });
        viewModel.getTransaction(calendar);

    }


    void updateDate() {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM,yyyy");
        binding.currentDate.setText(Helper.formatDate(calendar.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}