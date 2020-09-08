package com.joesoft.gads2020leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;

public class LeaderboardActivity extends AppCompatActivity
        implements View.OnClickListener, ILeaderBoard {
    public static final int LEARNING_LEADERS_INDEX = 0;
    public static final int SKILL_IQ_LEADERS_INDEX = 1;

    private ViewPager mViewPager;
    private MaterialButton mBtnSubmit;
    private ProgressBar mProgressBar;

    private LearningLeadersFragment mLearningLeadersFragment;
    private SkillIqLeadersFragment mSkillIqLeadersFragment;
    private LeaderBoardPageAdapter mLeaderBoardPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        mViewPager = findViewById(R.id.view_pager);
        mBtnSubmit = findViewById(R.id.btn_submit);
        mProgressBar = findViewById(R.id.progressBar);

        mBtnSubmit.setOnClickListener(this);

        setUpViewPager();
    }

    private void setUpViewPager() {
        mLearningLeadersFragment = new LearningLeadersFragment();
        mSkillIqLeadersFragment = new SkillIqLeadersFragment();

        mLeaderBoardPageAdapter = new LeaderBoardPageAdapter(getSupportFragmentManager());
        mLeaderBoardPageAdapter.addFragment(mLearningLeadersFragment);
        mLeaderBoardPageAdapter.addFragment(mSkillIqLeadersFragment);

        mViewPager.setAdapter(mLeaderBoardPageAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(LEARNING_LEADERS_INDEX).setText(getString(R.string.learning_leaders_fragment));
        tabLayout.getTabAt(SKILL_IQ_LEADERS_INDEX).setText(R.string.skill_iq_leaders_fragment);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                Intent intent = new Intent(LeaderboardActivity.this, ProjectSubmissionActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}
