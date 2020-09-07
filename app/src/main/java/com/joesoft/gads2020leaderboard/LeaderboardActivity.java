package com.joesoft.gads2020leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class LeaderboardActivity extends AppCompatActivity {
    public static final int LEARNING_LEADERS_INDEX = 0;
    public static final int SKILL_IQ_LEADERS_INDEX = 1;
    private ViewPager mViewPager;

    private LearningLeadersFragment mLearningLeadersFragment;
    private SkillIqLeadersFragment mSkillIqLeadersFragment;
    private LeaderBoardPageAdapter mLeaderBoardPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        mViewPager = findViewById(R.id.view_pager);

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
}
