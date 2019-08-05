package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("cyBpo4doYer7ANZjdhvGI8QDhfevqf3Arlm5ne85")
                // if defined
                .clientKey("bfgMptd78AIExbxi21GAce9ilyMS4D5C2diVsc3x")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
