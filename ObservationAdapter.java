package com.kmd.uog.mhike.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kmd.uog.mhike.R;
import com.kmd.uog.mhike.database.Hike;
import com.kmd.uog.mhike.database.Observation;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ObservationAdapter extends RecyclerView.Adapter<ObservationAdapter.ViewHolder>{

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.observation_list_item,parent,false);
        return new ObservationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Observation observation= observationList.get(position);
        holder.lblObservation.setText(observation.getObservation());
        holder.lblObservationDate.setText(observation.getDateTime().format(DateTimeFormatter.ofPattern("dd/MMM/yyyy hh:mm a")));
    }

    @Override
    public int getItemCount() {
        return observationList.size();
    }

    public interface ClickListener{
        public void onButtonClick(int position, View v, int buttonId);
    }

    private static ClickListener listener;
    public void setListener(ClickListener listener)
    {
        this.listener= listener;
    }

    private List<Observation> observationList;

    public ObservationAdapter( List<Observation> observationList)
    {
        this.observationList= observationList;
    }
    public void setObservationList(List<Observation> observationList)
    {
        this.observationList= observationList;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView lblObservation, lblObservationDate;
        Button btnObservationView, btnObservationEdit, btnObservationDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblObservation = itemView.findViewById(R.id.lblObservation);
            lblObservationDate= itemView.findViewById(R.id.lblObservationDate);

            btnObservationView = itemView.findViewById(R.id.btnObservationView);
            btnObservationEdit = itemView.findViewById(R.id.btnObservationEdit);
            btnObservationDelete= itemView.findViewById(R.id.btnObservationDelete);

            btnObservationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onButtonClick(getAdapterPosition(),view, R.id.btnObservationView);
                }
            });

            btnObservationEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onButtonClick(getAdapterPosition(), view, R.id.btnObservationEdit);
                }
            });

            btnObservationDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onButtonClick(getAdapterPosition(), view, R.id.btnObservationDelete);
                }
            });
        }
    }
}
