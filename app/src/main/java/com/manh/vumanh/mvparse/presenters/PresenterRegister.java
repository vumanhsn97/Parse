package com.manh.vumanh.mvparse.presenters;

import android.app.Activity;
import android.text.TextUtils;

import com.manh.vumanh.mvparse.R;
import com.manh.vumanh.mvparse.views.ViewRegister;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class PresenterRegister implements InterfaceRegister {
    ViewRegister view;
    Activity activity;

    public PresenterRegister(ViewRegister view, Activity activity) {
        this.view = view;
        this.activity = activity;
    }

    @Override
    public boolean checkInfor(String username, String pass, String repass, String email) {
        if(TextUtils.isEmpty(username)){
            view.sendWrong(activity.getString(R.string.user_register_fail));
            return false;
        } else if(TextUtils.isEmpty(email)){
            view.sendWrong(activity.getString(R.string.email_register_fail));
            return false;
        } else if(TextUtils.isEmpty(pass)){
            view.sendWrong(activity.getString(R.string.pass_register_fail));
            return false;
        } else if(TextUtils.isEmpty(repass)){
            view.sendWrong(activity.getString(R.string.pass_confirm_register_fail));
            return false;
        } else if(repass.length() != pass.length() || pass.lastIndexOf(repass) == -1){
            view.sendWrong(activity.getString(R.string.pass_repass_register_fail));
            return false;
        } else if(email.lastIndexOf('@') == -1){
            view.sendWrong(activity.getString(R.string.email_register_wrong));
            return false;
        }
        return true;
    }

    @Override
    public void registerAccount(String usermane, String pass, String email) {
        ParseUser user = new ParseUser();
        user.setUsername(email);
        user.setPassword(pass);
        user.setEmail(email);
        user.add("Name",usermane);
        view.showProgress();
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
               if(e == null){
                   view.dismissProgress();
                   view.registerSuccess();
               } else {
                   view.dismissProgress();
                   view.sendWrong(activity.getString(R.string.user_register_exist));
               }
            }
        });
    }
}
