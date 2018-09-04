package com.manh.vumanh.mvparse.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.manh.vumanh.mvparse.R;
import com.parse.ParseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashFragment extends BaseFragment {
    @BindView(R.id.splashlayout)
    LinearLayout splashlayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_splash, container, false);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().hide();
        ButterKnife.bind(this, view);
        Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.splash);
        splashlayout.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(ParseUser.getCurrentUser() != null){
                    changeFragment(new MemberFragment(), R.id.main, false);

                } else {
                    changeFragment(new MainFragment(), R.id.main, false);

                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return view;
    }
}
