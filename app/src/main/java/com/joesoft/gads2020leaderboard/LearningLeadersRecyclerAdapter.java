package com.joesoft.gads2020leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joesoft.gads2020leaderboard.models.LearningLeader;

import java.util.ArrayList;

public class LearningLeadersRecyclerAdapter extends RecyclerView.Adapter<LearningLeadersRecyclerAdapter.ViewHolder> {
    private  ArrayList<LearningLeader> mLearningLeaders;

    public LearningLeadersRecyclerAdapter(ArrayList<LearningLeader> learningLeaders) {
        mLearningLeaders = learningLeaders;
    }

    @NonNull
    @Override
    public LearningLeadersRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_learning_leader, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningLeadersRecyclerAdapter.ViewHolder holder, int position) {
        LearningLeader learningLeader = mLearningLeaders.get(position);

        holder.mtvName.setText(learningLeader.getName());
        holder.mtvHours.setText(learningLeader.getHours());
        holder.mtvCountry.setText(learningLeader.getCountry());
    }

    @Override
    public int getItemCount() {
        return mLearningLeaders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mTopLearnerBadge;
        TextView mtvName, mtvHours, mtvCountry;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTopLearnerBadge = itemView.findViewById(R.id.top_learner_badge);
            mtvName = itemView.findViewById(R.id.tv_name);
            mtvHours = itemView.findViewById(R.id.tv_hours);
            mtvCountry = itemView.findViewById(R.id.tv_country);
        }
    }
}
