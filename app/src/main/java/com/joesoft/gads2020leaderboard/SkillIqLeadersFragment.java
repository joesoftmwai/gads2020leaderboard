package com.joesoft.gads2020leaderboard;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.joesoft.gads2020leaderboard.models.SkillIQLeader;
import com.joesoft.gads2020leaderboard.services.ServiceBuilder;
import com.joesoft.gads2020leaderboard.services.SkillIqLeaderService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIqLeadersFragment extends Fragment {
    private static final String TAG = "SkillIqLeadersFragment";
    private RecyclerView mSkillIqLeadersRecycler;
    private SkillIqLeadersRecyclerAdapter mSkillIqLeadersRecyclerAdapter;
    private ArrayList<SkillIQLeader> mSkillIQLeaders = new ArrayList<>();
    private ILeaderBoard mILeaderBoard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false);
        mSkillIqLeadersRecycler = view.findViewById(R.id.rv_skill_iq_leaders);

        fetchSkillIqLeaders();

        return view;
    }

    private void fetchSkillIqLeaders() {
        mILeaderBoard.showProgressBar();
        SkillIqLeaderService skillIqService = ServiceBuilder.buildService(SkillIqLeaderService.class);
        Call<ArrayList<SkillIQLeader>> skillIqRequest = skillIqService.getSkillIqLeaders();

        skillIqRequest.enqueue(new Callback<ArrayList<SkillIQLeader>>() {
            @Override
            public void onResponse(Call<ArrayList<SkillIQLeader>> call, Response<ArrayList<SkillIQLeader>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.message());
                    mSkillIQLeaders = response.body();
                    initSkillIqLeadersRecyclerView();
                } else {
                    Toast.makeText(getActivity(),
                            "Failed to retrieve Skill IQ Leaders", Toast.LENGTH_SHORT).show();
                }
                mILeaderBoard.hideProgressBar();
            }

            @Override
            public void onFailure(Call<ArrayList<SkillIQLeader>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getCause());
                Toast.makeText(getActivity(),
                        "Failed to retrieve Skill IQ Leaders", Toast.LENGTH_SHORT).show();
                mILeaderBoard.hideProgressBar();
            }
        });

    }

    private void initSkillIqLeadersRecyclerView() {
        mSkillIqLeadersRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSkillIqLeadersRecyclerAdapter = new SkillIqLeadersRecyclerAdapter(mSkillIQLeaders);
        mSkillIqLeadersRecycler.setAdapter(mSkillIqLeadersRecyclerAdapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mILeaderBoard = (ILeaderBoard) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}
