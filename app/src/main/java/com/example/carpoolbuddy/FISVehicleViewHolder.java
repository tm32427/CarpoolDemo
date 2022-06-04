package com.example.carpoolbuddy;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FISVehicleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected TextView nameText;
    protected TextView statusText;
    onVehicleListener onVehicleListener;


     public FISVehicleViewHolder(@NonNull View itemView, onVehicleListener onVehicleListener) {
        super(itemView);
        nameText = itemView.findViewById(R.id.nameTextView);
        statusText = itemView.findViewById(R.id.statusTextView);
        this.onVehicleListener = onVehicleListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onVehicleListener.onVehicleClick(getAdapterPosition());
    }

    public interface onVehicleListener{
         void onVehicleClick(int position);
    }
}
