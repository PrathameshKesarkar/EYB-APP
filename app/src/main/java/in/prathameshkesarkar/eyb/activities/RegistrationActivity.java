package in.prathameshkesarkar.eyb.activities;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.prathameshkesarkar.eyb.R;

/**
 * Created by prathameshkesarkar on 04/02/17.
 */

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.toggleButton)
    FloatingActionButton actionButton;

    @BindView(R.id.loginView )
    View loginView;
    @BindView(R.id.signUpView)
    View signUpView;

    @BindView(R.id.registrationRootView)
    View registrationRootView;

    private Button loginButton;
    private Button registerButton;
    private Animator slideOutAnimation, slideInAnimation;
    /**
     * TODO: Let me Add ButterKnife
     * TODO: Then Let's move the Network Task to AndroidJob rather than Asynctask
     */
    private Unbinder unbinder;

    private boolean toggleFlag = false;
    private AnimatorSet animatorSet = new AnimatorSet();

    AlertDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        unbinder = ButterKnife.bind(this);


        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.signUpRegister);


//        R.id.loginEmailEditText;

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AnimatedVectorDrawableCompat avd;
                animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                //Animation for the RegistrationActivity Screen
                Animatable animatable;
                if (!toggleFlag) {

                    slideOutAnimation = ObjectAnimator.ofFloat(loginView, "translationX", 0, -registrationRootView.getWidth());
                    slideOutAnimation.setDuration(500);

                    slideInAnimation = ObjectAnimator.ofFloat(signUpView, "translationX", registrationRootView.getWidth(), 0);
                    slideInAnimation.setDuration(500);

                    animatorSet.playTogether(slideOutAnimation, slideInAnimation);
                    animatorSet.start();
                    avd = AnimatedVectorDrawableCompat.create(RegistrationActivity.this, R.drawable.rightleftanimatedvector);


                } else {

                    slideOutAnimation = ObjectAnimator.ofFloat(loginView, "translationX", -registrationRootView.getWidth(), 0);
                    slideOutAnimation.setDuration(500);

                    slideInAnimation = ObjectAnimator.ofFloat(signUpView, "translationX", 0, registrationRootView.getWidth());
                    slideInAnimation.setDuration(500);

                    animatorSet.playTogether(slideInAnimation, slideOutAnimation);
                    animatorSet.start();

                    avd = AnimatedVectorDrawableCompat.create(RegistrationActivity.this, R.drawable.leftrightanimatedvector);
                }
                toggleFlag = !toggleFlag;
                actionButton.setImageDrawable(avd);
                animatable = (Animatable) actionButton.getDrawable();
                animatable.start();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new AlertDialog.Builder(RegistrationActivity.this).setMessage("Checkin the Info with Server").create();
                dialog.show();


                waitForLogin();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new AlertDialog.Builder(RegistrationActivity.this).setMessage("Checkin the Info with Server").create();
                dialog.show();
                waitForLogin();
            }
        });

    }

    public void waitForLogin() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                dialog.dismiss();
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}
