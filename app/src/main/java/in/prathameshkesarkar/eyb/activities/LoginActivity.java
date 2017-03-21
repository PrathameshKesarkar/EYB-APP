package in.prathameshkesarkar.eyb.activities;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import in.prathameshkesarkar.eyb.R;

/**
 * Created by prathameshkesarkar on 04/02/17.
 */

public class LoginActivity extends AppCompatActivity {

    FloatingActionButton actionButton;
    private View loginView, signUpView;
    private View registrationRootView;
    private Animator slideOutAnimation, slideInAnimation;


    private boolean toggleFlag = false;
    private AnimatorSet animatorSet = new AnimatorSet();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        actionButton = (FloatingActionButton) findViewById(R.id.toggleButton);

        loginView = findViewById(R.id.loginView);
        signUpView = findViewById(R.id.signUpView);
        registrationRootView = findViewById(R.id.registrationRootView);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AnimatedVectorDrawableCompat avd;
                animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                //Animation for the Registration Screen
                Animatable animatable;
                if (!toggleFlag) {

                    slideOutAnimation = ObjectAnimator.ofFloat(loginView, "translationX", 0, -registrationRootView.getWidth());
                    slideOutAnimation.setDuration(500);

                    slideInAnimation = ObjectAnimator.ofFloat(signUpView, "translationX", registrationRootView.getWidth(), 0);
                    slideInAnimation.setDuration(500);

                    animatorSet.playTogether(slideOutAnimation,slideInAnimation);
                    animatorSet.start();
                    avd = AnimatedVectorDrawableCompat.create(LoginActivity.this, R.drawable.rightleftanimatedvector);


                } else {

                    slideOutAnimation = ObjectAnimator.ofFloat(loginView, "translationX", -registrationRootView.getWidth(), 0);
                    slideOutAnimation.setDuration(500);

                    slideInAnimation = ObjectAnimator.ofFloat(signUpView, "translationX", 0, registrationRootView.getWidth());
                    slideInAnimation.setDuration(500);

                    animatorSet.playTogether(slideInAnimation,slideOutAnimation);
                    animatorSet.start();

                    avd = AnimatedVectorDrawableCompat.create(LoginActivity.this, R.drawable.leftrightanimatedvector);
                }
                toggleFlag = !toggleFlag;
                actionButton.setImageDrawable(avd);
                animatable = (Animatable) actionButton.getDrawable();
                animatable.start();
            }
        });


    }
}
