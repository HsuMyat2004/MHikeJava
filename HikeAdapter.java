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
import java.util.List;

public class HikeAdapter extends RecyclerView.Adapter<HikeAdapter.ViewHolder>{

    public interface ClickListener{
        public void onButtonClick(int position, View v, long id);
    }

    private static ClickListener listener;
    public void setListener(ClickListener listener)
    {
        this.listener= listener;
    }
    private List<Hike> hikeList;
    public HikeAdapter(List<Hike>list){this.hikeList=list;}
    public void setHikeList (List<Hike> list) {this.hikeList=list;}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hike_lest_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hike hike= hikeList.get(position);
        holder.lblHikeName.setText(hike.getName());
        holder.lblHikeLocation.setText(hike.getLocation());
        holder.lblHikeDate.setText(hike.getDate());
    }

    @Override
    public int getItemCount() {
        return hikeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView lblHikeName, lblHikeLocation, lblHikeDate;
        Button btnDetail, btnEdit, btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblHikeName = itemView.findViewById(R.id.lblHikeName);
            lblHikeLocation = itemView.findViewById(R.id.lblHikeLocation);
            lblHikeDate = itemView.findViewById(R.id.lblHikeDate);
            btnDetail = itemView.findViewById(R.id.btnDetail);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete= itemView.findViewById(R.id.btnDelete);

            btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onButtonClick(getAdapterPosition(),view, R.id.btnDetail);
                }
            });

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onButtonClick(getAdapterPosition(),view, R.id.btnEdit);
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onButtonClick(getAdapterPosition(),view, R.id.btnDelete);
                }
            });
        }
    }
}
