package com.bwf.hiit.workout.abs.challenge.home.fitness.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bwf.hiit.workout.abs.challenge.home.fitness.R;
import com.bwf.hiit.workout.abs.challenge.home.fitness.managers.AnalyticsManager;
import com.bwf.hiit.workout.abs.challenge.home.fitness.view.ScrollingActivity;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.myHolder> {

    private String[] title = {"BEGINNER", "INTERMEDIATE", "ADVANCED"};
    private int[] image = new int[]{R.drawable.main_screen_beginner_image, R.drawable.main_screen_intermediate_image,
            R.drawable.main_screen_advanced_image};
    private List<Integer> progress;

    public HomeAdapter(List<Integer> progress) {
        this.progress = progress;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_layout, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull myHolder holder, final int position) {

        holder.tvTitle.setText(title[position]);
        holder.itemView.setBackgroundResource(image[position]);
        holder.progressBar.setMax(30);
        holder.progressBar.setProgress(progress.get(position));
        int i = 30 - progress.get(position);
        holder.tvDayLeft.setText(i + "");
        holder.tvPercentage.setText((int) (progress.get(position) / 0.3) + "%");
        holder.itemView.setOnClickListener(view -> setOnClick(view, position));
    }

    @Override
    public int getItemCount() {
        return progress.size();
    }

    class myHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ProgressBar progressBar;
        TextView tvDayLeft;
        TextView tvPercentage;

        myHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tileId);
            progressBar = itemView.findViewById(R.id.progressBar);
            tvDayLeft = itemView.findViewById(R.id.home_textViewDaysleft);
            tvPercentage = itemView.findViewById(R.id.home_textVewPercentage);
        }
    }

    private void setOnClick(View view, int position) {
        Intent i = new Intent(view.getContext(), ScrollingActivity.class);
        i.putExtra(view.getContext().getString(R.string.plan), (position + 1));
        view.getContext().startActivity(i);
        AnalyticsManager.getInstance().sendAnalytics("plan_selected" + title[position], "plan_selected_" + title[position]);
    }

}
