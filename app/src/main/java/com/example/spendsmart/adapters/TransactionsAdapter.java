package com.example.spendsmart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spendsmart.R;
import com.example.spendsmart.databinding.RowMotBinding;
import com.example.spendsmart.databinding.RowTransactionBinding;
import com.example.spendsmart.models.Category;
import com.example.spendsmart.models.Transaction;
import com.example.spendsmart.utils.Constants;
import com.example.spendsmart.utils.Helper;

import java.util.ArrayList;

import io.realm.RealmResults;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder> {


    Context context;
    RealmResults<Transaction> transactions;

    public  TransactionsAdapter(Context context, RealmResults<Transaction> transactions) {
        this.context = context;
        this.transactions = transactions;
    }
    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TransactionViewHolder(LayoutInflater.from(context).inflate(R.layout.row_transaction, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);

        holder.binding.transactionAmount.setText(String.valueOf(transaction.getAmount()));
        holder.binding.motLbl.setText(transaction.getMot());

        holder.binding.tansactionDate.setText(Helper.formatDate(transaction.getDate()));
        holder.binding.transactionCatagory.setText(transaction.getCategory());

        Category transactionCategory = Constants.getCategoryDetails(transaction.getCategory());

        holder.binding.catagoryicon.setImageResource(transactionCategory.getCategoryImage());
        holder.binding.catagoryicon.setBackgroundTintList(context.getColorStateList(transactionCategory.getCategoryColor()));

        holder.binding.motLbl.setBackgroundTintList(context.getColorStateList(Constants.getMotsColor(transaction.getMot())));

        if(transaction.getType().equals(Constants.INCOME)) {
            holder.binding.transactionAmount.setTextColor(context.getColor(R.color.green));
        } else if(transaction.getType().equals(Constants.EXPENSE)){
            holder.binding.transactionAmount.setTextColor(context.getColor(R.color.red));
        }

    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {

       RowTransactionBinding binding;

       public TransactionViewHolder(@NonNull View itemView) {
           super(itemView);
           binding = RowTransactionBinding.bind(itemView);
       }
   }
}
