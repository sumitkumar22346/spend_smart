package com.example.spendsmart.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.spendsmart.models.Transaction;
import com.example.spendsmart.utils.Constants;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainViewModel extends AndroidViewModel {

    public MutableLiveData<RealmResults<Transaction>> transaction = new MutableLiveData<>();

    Realm realm;

    public MainViewModel(@NonNull Application application) {
        super(application);
        Realm.init(application);
        setupDatabase();
    }

    public  void getTransaction(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        RealmResults<Transaction>newTransactions = realm.where(Transaction.class)
                .greaterThanOrEqualTo("date", calendar.getTime())
                .lessThan("date", new Date(calendar.getTime().getTime() + (24*60*60*1000))
                .findAll();
        transaction.setValue(newTransactions);
    }

    public void addTransactions() {
       realm.beginTransaction();
       realm.copyToRealmOrUpdate(new Transaction(Constants.EXPENSE, "Pocket_money", "Online", "mote", new Date(), 500, new Date().getTime()));
       realm.copyToRealmOrUpdate(new Transaction(Constants.INCOME, "Food", "Online", "mote", new Date(), 500, new Date().getTime()));
        //some code here
        realm.commitTransaction();
    }

    void setupDatabase() {
        realm = Realm.getDefaultInstance();
    }


}
