package com.joesoft.gads2020leaderboard.services;

import com.joesoft.gads2020leaderboard.models.SkillIQLeader;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillIqLeaderService {
    @GET("/api/skilliq")
    Call<ArrayList<SkillIQLeader>> getSkillIqLeaders();
}
