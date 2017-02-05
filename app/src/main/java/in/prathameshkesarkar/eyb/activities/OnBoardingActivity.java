package in.prathameshkesarkar.eyb.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

import in.prathameshkesarkar.eyb.R;

/**
 * Created by prathameshkesarkar on 04/02/17.
 */

public class OnBoardingActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance("Become Competetive"
                , "Join the EYB and choose from various different model to increase your learninig"
                , R.drawable.intelligent_person, ContextCompat.getColor(this, R.color.colorIndigoIntro)));
        addSlide(AppIntroFragment.newInstance("Interactive Learning"
                , "Use app for Interactive Learninig of different topics"
                , R.drawable.interactive_learning
                , ContextCompat.getColor(this, R.color.colorRedIntro)));
        addSlide(AppIntroFragment.newInstance("Use Tools"
                , "Use Various tools from the app to learn build the perfect Resumè and various life lesson"
                , R.drawable.tools, ContextCompat.getColor(this, R.color.colorGreyIntro)));
        setFadeAnimation();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        callLoginActiviy();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        callLoginActiviy();
    }

    public void callLoginActiviy(){
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }
}
