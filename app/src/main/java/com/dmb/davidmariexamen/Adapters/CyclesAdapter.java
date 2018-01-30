package com.dmb.davidmariexamen.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmb.davidmariexamen.Models.Cycle;
import com.dmb.davidmariexamen.R;

import java.util.ArrayList;

/**
 * Created by davidmari on 30/1/18.
 */

public class CyclesAdapter extends RecyclerView.Adapter<CyclesAdapter.CycleViewHolder>{

    private ArrayList<Cycle> cycles;
    private static RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public CyclesAdapter(ArrayList<Cycle> cycles, @NonNull RecyclerViewOnItemClickListener recyclerViewOnItemClickListener){
        this.cycles = cycles;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    public int getItemCount() {
        return cycles.size();
    }

    @Override
    public CycleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cycle, viewGroup, false);
        CycleViewHolder cvh = new CycleViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(final CycleViewHolder cycleViewHolder, final int position) {
        Cycle cycle = cycles.get(position);
        cycleViewHolder.cycleCourse.setText(cycle.getCourse());
        cycleViewHolder.cycleTitle.setText(cycle.getTitle());
        cycleViewHolder.cycleYear.setText(cycle.getPromotionYear());
        cycleViewHolder.cycleStudents.setText(cycle.getStudents());
        if (cycle.getCourse().equals("2n") && cycle.getTitle().equals("DAM")){
            cycleViewHolder.imgCycle.setImageResource(R.mipmap.disappointment);
        }else if(cycle.getCourse().equals("2n") && cycle.getTitle().equals("DAW")){
            cycleViewHolder.imgCycle.setImageResource(R.mipmap.anger);
        }else if(cycle.getCourse().equals("1r") && cycle.getTitle().equals("DAM")){
            cycleViewHolder.imgCycle.setImageResource(R.mipmap.sad);
        }else if(cycle.getCourse().equals("1r") && cycle.getTitle().equals("DAW")){
            cycleViewHolder.imgCycle.setImageResource(R.mipmap.cute);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class CycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cardCycle;
        ImageView imgCycle;
        TextView cycleCourse;
        TextView cycleTitle;
        TextView cycleYear;
        TextView cycleStudents;

        CycleViewHolder(View itemView) {
            super(itemView);
            cardCycle = itemView.findViewById(R.id.cardCycle);
            imgCycle = itemView.findViewById(R.id.imgCycle);
            cycleCourse = itemView.findViewById(R.id.tvCycleCourse);
            cycleTitle = itemView.findViewById(R.id.tvCycleTitle);
            cycleYear = itemView.findViewById(R.id.tvCycleYear);
            cycleStudents = itemView.findViewById(R.id.tvCycleStudents);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            recyclerViewOnItemClickListener.onClick(v,getAdapterPosition());
        }

    }

    public interface RecyclerViewOnItemClickListener{
        void onClick(View v, int position);
    }

}
