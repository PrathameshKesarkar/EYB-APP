package in.prathameshkesarkar.eyb.activities;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.prathameshkesarkar.eyb.R;

/**
 * Created by prathameshkesarkar on 04/02/17.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_card)
    View loginCardLayout;

    @BindView(R.id.signup_card)
    View signUpCardLayout;

    @BindView(R.id.loginEmailEditText)
    TextInputEditText emailEditText;

    @BindView(R.id.loginEmailLayout)
    TextInputLayout emailTextLayout;

    @BindView(R.id.root_layout)
    FrameLayout rootLayout;

    @BindView(R.id.loginPasswordEditText)
    TextInputEditText passwordEditText;

    @BindView(R.id.loginPasswordLayout)
    TextInputLayout passwordTextLayout;


    //Register View Widgets
    @BindView(R.id.signUpUserNameEditText)
    TextInputEditText userNameEditText;

    @BindView(R.id.signUpEmailEditText)
    TextInputEditText signUpEmailEditText;

    @BindView(R.id.signUpPasswordEditText)
    TextInputEditText signUpPasswordEditText;

    @BindView(R.id.signUpReTypeEditText)
    TextInputEditText retypePaswordEditText;

    @BindView(R.id.signUpUserNameLayout)
    TextInputLayout userNameTextLayout;

    @BindView(R.id.signUpEmailLayout)
    TextInputLayout signUpEmailTextLayout;

    @BindView(R.id.signUpPasswordLayout)
    TextInputLayout signUpPasswordTextLayout;

    @BindView(R.id.signUpReTypeLayout)
    TextInputLayout signUpRePasswordTextLayout;

    @BindView(R.id.signUpLogin)
    Button loginButton;

    @BindView(R.id.signUpButton)
    Button signUpButton;


    private float scale;
    private static final int DISTANCE = 8000;
    Unbinder unbinder;

    private static String EMAIL;
    private static String PASSWORD;
    private AnimatorSet outAnimator, inAnimator;
    private String email, password, userName;
    private SharedPreferences userCredential;
    private SharedPreferences.Editor editor;

    private InAnimationListener inAnimationListener;
    private OutAnimationListener outAnimationListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        scale = getResources().getDisplayMetrics().density;
        unbinder = ButterKnife.bind(this);

        setCameraDistance();
        initalizeAnimation();

        inAnimationListener = new InAnimationListener();
        outAnimationListener = new OutAnimationListener();
    }

    @OnClick(R.id.loginButton)
    public void loginUser(Button loginButton) {

        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();
        Log.d("MainActivity", email);
        Log.d("MainActivity", password);
        if (TextUtils.isEmpty(email)) {
            emailTextLayout.setErrorEnabled(true);
            emailTextLayout.setError("Email cannot be blank");
            return;
        } else {
            emailTextLayout.setErrorEnabled(false);
        }
        //password
        if (TextUtils.isEmpty(password)) {
            passwordTextLayout.setErrorEnabled(true);
            passwordTextLayout.setError("Password cannot be blank");
            return;
        } else {
            passwordTextLayout.setErrorEnabled(false);
        }

        final ProgressDialog dialog = ProgressDialog.show(this, "", "Logining in...", true);
        dialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                userCredential = getSharedPreferences("prefes", 0);
                EMAIL = userCredential.getString("email", "pratham.kesarkar@gmail.com");
                PASSWORD = userCredential.getString("password", "12345678");
                if (TextUtils.equals(email, EMAIL) && TextUtils.equals(password, PASSWORD)) {
                    dialog.dismiss();
                    startStudentModule();
                } else {
                    dialog.dismiss();
                    displaySnack();
                }
            }
        }, 3000);
    }

    @OnClick(R.id.signUpButton)
    public void showSignUp(Button signUpButton) {
        inAnimator.setTarget(signUpCardLayout);
        outAnimator.setTarget(loginCardLayout);

        inAnimationListener.setView(loginButton);
        outAnimationListener.setView(signUpButton);

        outAnimator.start();
        inAnimator.start();

//        inAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//                signUpCardLayout.setVisibility(View.VISIBLE);
//            }
//        });
//
//        outAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                loginCardLayout.setVisibility(View.GONE);
//            }
//        });
    }

    @OnClick(R.id.signUpLogin)
    public void showLogin(Button loginButton) {
        inAnimator.setTarget(loginCardLayout);
        outAnimator.setTarget(signUpCardLayout);

        inAnimationListener.setView(signUpButton);
        outAnimationListener.setView(loginButton);

        outAnimator.start();
        inAnimator.start();

//        inAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//                loginCardLayout.setVisibility(View.VISIBLE);
//            }
//        });
//        outAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                signUpCardLayout.setVisibility(View.GONE);
//            }
//        });
    }

    @OnClick(R.id.signUpRegister)
    public void registerUser(Button view) {
        userName = userNameEditText.getText().toString();
        email = signUpEmailEditText.getText().toString();
        password = signUpPasswordEditText.getText().toString();
        String reTypePassword = retypePaswordEditText.getText().toString();

        //userName condition
        if (checkForEmpty(userName)) {
            userNameTextLayout.setErrorEnabled(true);
            userNameTextLayout.setError("User Name cannot be blank");
            return;
        } else {
            userNameTextLayout.setErrorEnabled(false);
        }

        //email condition
        if (checkForEmpty(email)) {
            signUpEmailTextLayout.setErrorEnabled(true);
            signUpEmailTextLayout.setError("Email cannot be blank");
            return;
        } else {
            signUpEmailTextLayout.setErrorEnabled(false);
        }
        //password condition
        if (checkForEmpty(password)) {
            signUpPasswordTextLayout.setErrorEnabled(true);
            signUpPasswordTextLayout.setError("Password cannot be blank");
            return;
        } else {
            passwordTextLayout.setErrorEnabled(false);
        }
        //retype password condtion
        if (checkForEmpty(reTypePassword)) {
            signUpRePasswordTextLayout.setErrorEnabled(true);
            signUpRePasswordTextLayout.setError("You haven't typed password");
            return;
        } else {
            signUpRePasswordTextLayout.setErrorEnabled(false);
        }
        if (!TextUtils.equals(password, reTypePassword)) {
            signUpRePasswordTextLayout.setErrorEnabled(true);
            signUpRePasswordTextLayout.setError("Password doesn't match");
            return;
        } else {
            signUpRePasswordTextLayout.setErrorEnabled(false);
        }
        final ProgressDialog dialog = ProgressDialog.show(this, "", "Registering User...", true);
        dialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                userCredential = getSharedPreferences("prefes", 0);
                editor = userCredential.edit();
                editor.putString("email", email);
                editor.putString("password", password);
                editor.putString("userName", userName);
                editor.apply();
                dialog.dismiss();
                startStudentModule();
            }
        }, 3000);
    }

    public boolean checkForEmpty(String msg) {
        return TextUtils.isEmpty(msg);
    }

    public void initalizeAnimation() {
        outAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.rotate_out);
        inAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.rotate_in);

    }

    public void setCameraDistance() {
        loginCardLayout.setCameraDistance(DISTANCE * scale);
        signUpCardLayout.setCameraDistance(DISTANCE * scale);
    }

    public void startStudentModule() {
        Intent intent = new Intent(this, StudentModuleActivity.class);
        startActivity(intent);
    }

    public void displaySnack() {
        Snackbar.make(rootLayout, "Error in loggin in user pls check your email or password", Snackbar.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private class InAnimationListener extends AnimatorListenerAdapter {

        View view;

        public void setView(View view) {
            this.view = view;
        }


        @Override
        public void onAnimationStart(Animator animation) {
            super.onAnimationStart(animation);
            view.setClickable(true);
        }
    }
    private class OutAnimationListener extends AnimatorListenerAdapter {

        View view;

        public void setView(View view) {
            this.view = view;
        }


        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationStart(animation);
            view.setClickable(false);
        }
    }
}
