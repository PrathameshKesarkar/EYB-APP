package in.prathameshkesarkar.eyb.activities;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Animatable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.prathameshkesarkar.eyb.R;
import in.prathameshkesarkar.eyb.model.Login;
import in.prathameshkesarkar.eyb.model.Register;
import in.prathameshkesarkar.eyb.model.RegisterResponse;
import in.prathameshkesarkar.eyb.model.error.RetrofitError;
import in.prathameshkesarkar.eyb.network.ErrorUtils;
import in.prathameshkesarkar.eyb.network.LoginService;
import in.prathameshkesarkar.eyb.network.RegisterService;
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
     * TODO: Then Let's move the Network Task to Retrofit rather than Asynctask
     */
    private Unbinder unbinder;
    private Snackbar snackbar;
    private boolean toggleFlag = false;
    private AnimatorSet animatorSet = new AnimatorSet();
    private NetworkInfo networkInfo;

    ProgressDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        unbinder = ButterKnife.bind(this);

        //Requesting Service for checking connectivity
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

        //Initialize the Instance of Snackbar
        initializeSnackBar();

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

        dialog = new ProgressDialog(RegistrationActivity.this);
        dialog.setMessage("Please wait while we log you in");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();

        LoginService loginService = ServiceGenerator.createService(LoginService.class);
        Login login = new Login(email, password);

        Call<RegisterResponse> call = loginService.performLogin(login);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    Headers headers = response.headers();
                    String authToken = headers.get("x-auth");
                    saveAuthToken(authToken);
                    startMainActivity();
                } else {
                    RetrofitError error = ErrorUtils.parseError(response);
                    snackbar.setText(error.getError().getDescription());
                    snackbar.show();
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                dialog.dismiss();
                if (!(networkInfo != null && networkInfo.isConnected())) {
                    snackbar.setText("No Internet Connectivity Available").show();
                } else {
                    snackbar.setText("Error Communicating with server,Please Try again later").show();
                }
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

        dialog = new ProgressDialog(RegistrationActivity.this);
        dialog.setMessage("Please wait , Registering Account");
        dialog.setIndeterminate(true);
        dialog.show();

        RegisterService service = ServiceGenerator.createService(RegisterService.class);

        Register register = new Register(name, email, password);
        Call<RegisterResponse> call = service.performRegister(register);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    Headers headers = response.headers();
                    String authToken = headers.get("x-auth");
                    saveAuthToken(authToken);
                    startMainActivity();

                } else {
                    RetrofitError error = ErrorUtils.parseError(response);
                    snackbar.setText(error.getError().getDescription());
                    snackbar.show();
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                dialog.dismiss();
                if (!(networkInfo != null && networkInfo.isConnected())) {
                    snackbar.setText("No Internet Connectivity Available").show();
                } else {
                    snackbar.setText("Error Communicating with server,Please Try again later").show();
                }
            }
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void saveAuthToken(String authToken) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("x-auth",authToken);
        editor.apply();
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

    public void initializeSnackBar() {
        snackbar = Snackbar.make(registrationRootView, "", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.dismiss, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
    }

}
