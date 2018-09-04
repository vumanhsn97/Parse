package com.manh.vumanh.mvparse.presenters;

import android.app.Activity;
import android.text.TextUtils;

import com.manh.vumanh.mvparse.R;
import com.manh.vumanh.mvparse.views.ViewLogin;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class PresenterLogin implements InterfaceLogin{
    ViewLogin view;
    Activity activity;

    public PresenterLogin(ViewLogin view, Activity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public boolean checkInfor(String user, String pass) {
        if(TextUtils.isEmpty(user)){
            view.sendWrong(activity.getString(R.string.user_login_fail));
            return false;
        } else if(TextUtils.isEmpty(pass)){
            view.sendWrong(activity.getString(R.string.pass_login_fail));
            return false;
        }
        return true;
    }

    @Override
    public void checkLogin(String user, String pass) {
        view.showProgress();
        ParseUser.logInInBackground(user, pass, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e == null){
                    view.dismissProgress();
                    view.loginSuccess();
                } else {
                    view.dismissProgress();
                    view.sendWrong(activity.getString(R.string.user_pass_login_fail));
                }
            }
        });
    }
}
