package com.manh.vumanh.mvparse.fragments;

import android.app.FragmentManager;
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
import com.manh.vumanh.mvparse.presenters.PresenterRegister;
import com.manh.vumanh.mvparse.views.ViewRegister;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterFragment extends BaseFragment implements ViewRegister{
    @BindView(R.id.reguser)
    EditText reguser;
    @BindView(R.id.regemail)
    EditText regemail;
    @BindView(R.id.regpass)
    EditText regpass;
    @BindView(R.id.regrepass)
    EditText regrepass;
    @BindView(R.id.regsignup)
    Button regsignup;
    @BindView(R.id.regfail)
    TextView regfail;
    String user, email, pass, repass;
    ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_register, container, false);
        final PresenterRegister presenterRegister = new PresenterRegister(this, getActivity());
        ButterKnife.bind(this, view);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().hide();
        regsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regfail.setText("");
                user = reguser.getText().toString();
                email = regemail.getText().toString();
                pass = regpass.getText().toString();
                repass = regrepass.getText().toString();
                if(presenterRegister.checkInfor(user, pass, repass, email)){
                    presenterRegister.registerAccount(user, pass, email);
                };

            }
        });
        return view;
    }

    @Override
    public void sendWrong(String text) {
        regfail.setText(text);
    }

    @Override
    public void registerSuccess() {
        changeFragment(new MemberFragment(), R.id.main,false);
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.progress_register_message));
        progressDialog.show();
    }

    @Override
    public void dismissProgress() {
        progressDialog.dismiss();
    }
}
