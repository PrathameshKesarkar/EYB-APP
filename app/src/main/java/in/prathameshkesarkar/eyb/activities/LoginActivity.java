package in.prathameshkesarkar.eyb.activities;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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

    private float scale;
    private int distance = 9000;
    Unbinder unbinder;

    private AnimatorSet outAnimator, inAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        scale = getResources().getDisplayMetrics().density;
        unbinder = ButterKnife.bind(this);

        setCameraDistance();
        initalizeAnimation();


    }

    @OnClick(R.id.signUpButton)
    public void showSignUp(Button signUpButton) {
        inAnimator.setTarget(signUpCardLayout);
        outAnimator.setTarget(loginCardLayout);
        outAnimator.start();
        inAnimator.start();

    }

    @OnClick(R.id.signUpLogin)
    public void showLogin(Button loginButton) {
        inAnimator.setTarget(loginCardLayout);
        outAnimator.setTarget(signUpCardLayout);
        outAnimator.start();
        inAnimator.start();
    }

    public void initalizeAnimation() {
        outAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.rotate_out);
        inAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.rotate_in);
    }

    public void setCameraDistance() {
        loginCardLayout.setCameraDistance(distance * scale);
        signUpCardLayout.setCameraDistance(distance * scale);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
