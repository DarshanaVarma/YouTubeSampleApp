package com.example.demo.youtubesampleapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by admin on 11/28/2017.
 */

public class PreferenceUtils {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    PreferenceUtils(Context context)
    {
     preferences= PreferenceManager.getDefaultSharedPreferences(context);
        editor=preferences.edit();
    }


    public void  setGmailId(String userId){
        editor.putString("Gmailid",userId);
        editor.commit();
    }
    public String getGmailId(){
        return preferences.getString("Gmailid","");
    }
}
