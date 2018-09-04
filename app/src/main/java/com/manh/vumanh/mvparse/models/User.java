package com.manh.vumanh.mvparse.models;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseUser;

public class User extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("lWHYuezwWwoALlQ1Co4KJ5ju3UOuoGGseUEfEdfn")
                .clientKey("0A2yTCdlrVewKurlqQ0mcNPYKIb1F1LpBmza5xVi")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
