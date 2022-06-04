package com.example.carpoolbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FISVehicleAdapter extends RecyclerView.Adapter<FISVehicleViewHolder> {

    private ArrayList<Vehicle> mData;
    private FISVehicleViewHolder.onVehicleListener mOnVehicleListener;

    public FISVehicleAdapter(ArrayList data , FISVehicleViewHolder.onVehicleListener onVehicleListener){
        mData = data;
        this.mOnVehicleListener = onVehicleListener;
    }


    @NonNull
    @Override
    public FISVehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fis_row_view , parent, false);

        FISVehicleViewHolder holder = new FISVehicleViewHolder(myView, mOnVehicleListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FISVehicleViewHolder holder, int position) {
        holder.nameText.setText(mData.get(position).getModel());
        holder.statusText.setText(mData.get(position).getVehicleType());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


}
