package com.joesoft.gads2020leaderboard;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.joesoft.gads2020leaderboard.models.LearningLeader;
import com.joesoft.gads2020leaderboard.services.LearningLeaderService;
import com.joesoft.gads2020leaderboard.services.ServiceBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLeadersFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "LearningLeadersFragment";
    private RecyclerView mLearningLeadersRecycler;
    private LearningLeadersRecyclerAdapter mLearningLeadersRecyclerAdapter;
    private ArrayList<LearningLeader> mLearningLeaders;
    private ILeaderBoard mILeaderBoard;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);
        mLearningLeadersRecycler = view.findViewById(R.id.rv_learning_leaders);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refesh_learning_leaders);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        fetchLearningLeaders();

        return view;
    }

    private void fetchLearningLeaders() {
        mILeaderBoard.showProgressBar();
        LearningLeaderService learningService = ServiceBuilder.buildService(LearningLeaderService.class);
        Call<ArrayList<LearningLeader>> learningRequest = learningService.getLearningLeaders();

        learningRequest.enqueue(new Callback<ArrayList<LearningLeader>>() {
            @Override
            public void onResponse(Call<ArrayList<LearningLeader>> call, Response<ArrayList<LearningLeader>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.message());
                    mLearningLeaders = response.body();
                    initLearningLeadersRecyclerView();
                } else {
                    Toast.makeText(getActivity(), "Failed to retrieve Learning Leaders", Toast.LENGTH_SHORT).show();
                }
                mILeaderBoard.hideProgressBar();
            }

            @Override
            public void onFailure(Call<ArrayList<LearningLeader>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getCause());
                Toast.makeText(getActivity(), "Failed to retrieve Learning Leaders", Toast.LENGTH_SHORT).show();
                mILeaderBoard.hideProgressBar();
            }
        });
    }

    private void initLearningLeadersRecyclerView() {
        mLearningLeadersRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLearningLeadersRecyclerAdapter = new LearningLeadersRecyclerAdapter(mLearningLeaders);
        mLearningLeadersRecycler.setAdapter(mLearningLeadersRecyclerAdapter);
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

    @Override
    public void onRefresh() {
        fetchLearningLeaders();
        onItemsLoadComplete();
    }

    private void onItemsLoadComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
