package in.prathameshkesarkar.eyb.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

/**
 * Created by prathameshkesarkar on 04/02/17.
 * The purpose of this Activity is to check whether the onBoarding has been already shown to user the first time.
 * Also this activity serves the purpose to check whether the user is authenticate and based on that decision
 * this will either show user Login page or Student's Menu.
 */
public class CheckActivity extends Activity {


    SharedPreferences setting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setting = getSharedPreferences("prefes", 0);

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        String authToken =preferences.getString("x-auth","");

        boolean firstRun = setting.getBoolean("firstRun", false);
        if (!firstRun) {
            SharedPreferences.Editor editor = setting.edit();
            editor.putBoolean("firstRun", true);
            editor.apply();
            Intent onBoardingIntent = new Intent(this, OnBoardingActivity.class);
            startActivity(onBoardingIntent);
        } else if(authToken.isEmpty()){
            Intent loginIntent = new Intent(this,RegistrationActivity.class);
            startActivity(loginIntent);
        }
        else {
            Intent mainIntent = new Intent(this,MainActivity.class);
            startActivity(mainIntent);
        }
    }
}
