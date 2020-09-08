package com.joesoft.gads2020leaderboard.services;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ProjectSubmissionService {
    String SUBMIT_PROJECT_URL
            = "https://docs.google.com/forms/d/e/"
            + "1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";

    @FormUrlEncoded
    @POST(SUBMIT_PROJECT_URL)
    Call<Void> newProject(
            @Field("entry.1877115667")String firstName,
            @Field("entry.2006916086")String lastName,
            @Field("entry.1824927963")String emailAddress,
            @Field("entry.284483984")String linkToProject

    );
}
