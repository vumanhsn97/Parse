package com.manh.vumanh.mvparse.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.manh.vumanh.mvparse.R;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MemberFragment extends BaseFragment {

    @BindView(R.id.mem_name)
    TextView username;
    @BindView(R.id.mem_age)
    TextView userage;
    @BindView(R.id.mem_email)
    TextView useremail;
    @BindView(R.id.mem_gender)
    TextView usergender;
    @BindView(R.id.mem_image)
    CircleImageView userimage;
    String name, age, gender, email;
    ParseUser user;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_member, container, false);
        ButterKnife.bind(this, view);
        user = ParseUser.getCurrentUser();
        name = user.getString("Name");
        age = user.getString("Age");
        email = user.getEmail();
        gender = user.getString("Gender");
        showInfor();
        setHasOptionsMenu(true);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().show();
        return view;
    }

    private void showInfor() {
        if(!TextUtils.isEmpty(name)){
            username.setText(name);
        }
        if(!TextUtils.isEmpty(age)){
            userage.setText(age);
        }
        if(!TextUtils.isEmpty(email)){
            useremail.setText(email);
        }
        if(!TextUtils.isEmpty(gender)){
            usergender.setText(gender);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_member, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                ParseUser.logOut();
                changeFragment(new MainFragment(), R.id.main, false);
                break;

            case R.id.changeinfor:
                changeFragment(new ChangeInforFragment(), R.id.main, true);
        }
        return super.onOptionsItemSelected(item);
    }
}
