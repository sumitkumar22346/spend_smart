package com.example.spendsmart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spendsmart.R;
import com.example.spendsmart.databinding.RowMotBinding;
import com.example.spendsmart.models.Mot;

import java.util.ArrayList;

public class MotsAdapter extends RecyclerView.Adapter<MotsAdapter.MotsViewHolder> {

    Context context;
    ArrayList<Mot> motArrayList;

    public interface MotsClickListener {
        void onMotSelected(Mot mot);
    }

    MotsClickListener motsClickListener;

    public MotsAdapter(Context context, ArrayList<Mot> motArrayList, MotsClickListener motsClickListener) {
        this.context = context;
        this.motArrayList = motArrayList;
        this.motsClickListener = motsClickListener;
    }

    @NonNull
    @Override
    public MotsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MotsViewHolder(LayoutInflater.from(context).inflate(R.layout.row_mot, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MotsViewHolder holder, int position) {
        Mot mot = motArrayList.get(position);
        holder.binding.motName.setText(mot.getMotName());
        holder.itemView.setOnClickListener(c-> {
            motsClickListener.onMotSelected(mot);
        });
    }

    @Override
    public int getItemCount() {
        return motArrayList.size();
    }

    public class MotsViewHolder extends RecyclerView.ViewHolder {

        RowMotBinding binding;

        public MotsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RowMotBinding.bind(itemView);
        }
    }
}
