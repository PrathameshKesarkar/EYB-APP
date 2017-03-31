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
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.prathameshkesarkar.eyb.R;
import in.prathameshkesarkar.eyb.model.LoginRequest;
import in.prathameshkesarkar.eyb.model.LoginResponse;
import in.prathameshkesarkar.eyb.network.LoginService;
import in.prathameshkesarkar.eyb.service.ServiceGenerator;
import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by prathameshkesarkar on 04/02/17.
 */

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.toggleButton)
    FloatingActionButton actionButton;

    @BindView(R.id.loginView)
    View loginView;
    @BindView(R.id.signUpView)
    View signUpView;

    @BindView(R.id.registrationRootView)
    View registrationRootView;

    //Bind String
    @BindString(R.string.email_empty)
    String emailEmptyError;

    @BindString(R.string.password_empty)
    String passwordEmptyError;

    @BindString(R.string.invalid_email)
    String emailInvalid;

    @BindString(R.string.name_empty)
    String nameEmptyError;

    @BindString(R.string.retype_empty)
    String retypeEmptyError;

    @BindString(R.string.retype_not_match)
    String retypeNotMatch;

    //Text Layout
    @BindView(R.id.loginEmailLayout)
    TextInputLayout loginEmailTextLayout;

    @BindView(R.id.loginPasswordLayout)
    TextInputLayout loginPasswordTextLayout;

    @BindView(R.id.signUpUserNameLayout)
    TextInputLayout nameTextLayout;

    @BindView(R.id.signUpEmailLayout)
    TextInputLayout signUpEmailTextLayout;

    @BindView(R.id.signUpPasswordLayout)
    TextInputLayout signUpPasswordTextLayout;

    @BindView(R.id.signUpReTypeLayout)
    TextInputLayout signUpRetypeTextLayout;


    //EditText
    @BindView(R.id.loginEmailEditText)
    TextInputEditText loginEmailEditText;

    @BindView(R.id.loginPasswordEditText)
    TextInputEditText loginPasswordEditText;

    @BindView(R.id.signUpUserNameEditText)
    TextInputEditText nameEditText;

    @BindView(R.id.signUpEmailEditText)
    TextInputEditText signUpEmailEditText;

    @BindView(R.id.signUpPasswordEditText)
    TextInputEditText signUpPasswordEditText;

    @BindView(R.id.signUpReTypeEditText)
    TextInputEditText signUpRetypeEditText;

    private Animator slideOutAnimation, slideInAnimation;
    /**
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

    @OnClick(R.id.loginButton)
    public void loginUser() {
        String email = loginEmailEditText.getText().toString();
        String password = loginPasswordEditText.getText().toString();

        //Check either email or password is empty and return from the function to stop further Execution
        if (email.isEmpty() || password.isEmpty()) {
            //If email is empty than show the email cannot be blank Error
            if (email.isEmpty()) {
                showError(loginEmailTextLayout, emailEmptyError);
            }
            if (password.isEmpty()) {
                showError(loginPasswordTextLayout, passwordEmptyError);
            }
            return;
        } else if (isInvalidEmail(email)) {
            //If entered Email is invalid
            showError(loginEmailTextLayout, emailInvalid);
            return;
        } else {
            clearError(loginEmailTextLayout);
            clearError(loginPasswordTextLayout);
        }

        dialog = new AlertDialog.Builder(RegistrationActivity.this).setMessage("Checkin the Info with Server").create();
        dialog.show();

        LoginService loginService = ServiceGenerator.createService(LoginService.class);
        LoginRequest loginRequest = new LoginRequest(email, password);


        Call<LoginResponse> call = loginService.performLogin(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Headers headers = response.headers();
                    String authToken = headers.get("x-auth");
                    Log.d("Auth Token", authToken);
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                dialog.dismiss();
                Snackbar.make()
            }
        });

    }

    @OnClick(R.id.signUpRegister)
    public void signUpUser() {
        String name = nameEditText.getText().toString();
        String email = signUpEmailEditText.getText().toString();
        String password = signUpPasswordEditText.getText().toString();
        String retype = signUpRetypeEditText.getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || retype.isEmpty()) {

            if (name.isEmpty()) {
                showError(nameTextLayout, nameEmptyError);
            }

            if (email.isEmpty()) {
                showError(signUpEmailTextLayout, emailEmptyError);
            }

            if (password.isEmpty()) {
                showError(signUpPasswordTextLayout, passwordEmptyError);
            }
            if (retype.isEmpty()) {
                showError(signUpRetypeTextLayout, retypeEmptyError);
            }
            return;
        } else if (isInvalidEmail(email)) {
            showError(signUpEmailTextLayout, emailInvalid);
            return;
        }

        if (!password.equals(retype)) {
            showError(signUpRetypeTextLayout, retypeNotMatch);
            return;
        } else {
            clearError(signUpEmailTextLayout);
            clearError(signUpPasswordTextLayout);
            clearError(nameTextLayout);
            clearError(signUpRetypeTextLayout);
        }

        dialog = new AlertDialog.Builder(RegistrationActivity.this).setMessage("Checkin the Info with Server").create();
        dialog.show();
        waitForLogin();
    }

    private boolean isInvalidEmail(String email) {
        final Pattern VALID_EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_REGEX.matcher(email);
        return !matcher.find();
    }

    private void showError(TextInputLayout layout, String msg) {
        layout.setErrorEnabled(true);
        layout.setError(msg);
    }

    private void clearError(TextInputLayout layout) {
        layout.setError("");
        layout.setErrorEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}
