package com.manh.vumanh.mvparse.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.manh.vumanh.mvparse.R;
import com.manh.vumanh.mvparse.presenters.PresenterLogin;
import com.manh.vumanh.mvparse.views.ViewLogin;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends BaseFragment implements ViewLogin{
    @BindView(R.id.loguser)
    EditText loguser;
    @BindView(R.id.logpass)
    EditText logpass;
    @BindView(R.id.logreg)
    Button logreg;
    @BindView(R.id.logfail)
    TextView logfail;
    String username, password;
    ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().hide();
        final PresenterLogin presenterLogin = new PresenterLogin(this, getActivity());
        logreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logfail.setText("");
                username = loguser.getText().toString();
                password = logpass.getText().toString();
                if(presenterLogin.checkInfor(username, password)){
                    presenterLogin.checkLogin(username, password);
                }
            }
        });
        return view;
    }

    @Override
    public void sendWrong(String mess) {
        logfail.setText(mess);
    }

    @Override
    public void loginSuccess() {
        changeFragment(new MemberFragment(), R.id.main, false);
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.progress_login_message));
        progressDialog.show();
    }

    @Override
    public void dismissProgress() {
        progressDialog.dismiss();
    }


}
