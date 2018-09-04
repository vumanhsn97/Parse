package com.manh.vumanh.mvparse.views;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manh.vumanh.mvparse.R;
import com.manh.vumanh.mvparse.fragments.MainFragment;
import com.manh.vumanh.mvparse.fragments.MemberFragment;
import com.manh.vumanh.mvparse.fragments.SplashFragment;
import com.manh.vumanh.mvparse.presenters.PresenterLogin;
import com.parse.Parse;
import com.parse.ParseUser;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.loguser)
    EditText loguser;
    @BindView(R.id.logpass)
    EditText logpass;
    @BindView(R.id.logreg)
    Button logreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("lWHYuezwWwoALlQ1Co4KJ5ju3UOuoGGseUEfEdfn")
                .clientKey("0A2yTCdlrVewKurlqQ0mcNPYKIb1F1LpBmza5xVi")
                .server("https://parseapi.back4app.com/")
                .build()
        );
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main, new SplashFragment());
        fragmentTransaction.commit();
    }
}
