package com.joesoft.gads2020leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joesoft.gads2020leaderboard.models.SkillIQLeader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SkillIqLeadersRecyclerAdapter extends RecyclerView.Adapter<SkillIqLeadersRecyclerAdapter.ViewHolder> {
    private ArrayList<SkillIQLeader> mSkillIQLeaders;

    public SkillIqLeadersRecyclerAdapter(ArrayList<SkillIQLeader> skillIQLeaders) {
        mSkillIQLeaders = skillIQLeaders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_skill_iq_leader, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SkillIQLeader skillIQLeader = mSkillIQLeaders.get(position);

        holder.mtvName.setText(skillIQLeader.getName());
        holder.mtvScore.setText(skillIQLeader.getScore());
        holder.mtvCountry.setText(skillIQLeader.getCountry());

        Picasso.get()
                .load(skillIQLeader.getBadgeUrl())
                .placeholder(R.drawable.top_learner_badge)
                .fit()
                .into(holder.mSkillIqBadge);

    }

    @Override
    public int getItemCount() {
        return mSkillIQLeaders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mSkillIqBadge;
        TextView mtvName, mtvScore, mtvCountry;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mSkillIqBadge = itemView.findViewById(R.id.skill_iq_badge);
            mtvName = itemView.findViewById(R.id.tv_name);
            mtvScore = itemView.findViewById(R.id.tv_score);
            mtvCountry = itemView.findViewById(R.id.tv_country);
        }
    }
}
