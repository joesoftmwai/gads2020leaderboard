package com.joesoft.gads2020leaderboard.services;

import com.joesoft.gads2020leaderboard.models.LearningLeader;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LearningLeaderService {
    @GET("/api/hours")
    Call<ArrayList<LearningLeader>> getLearningLeaders();
}
