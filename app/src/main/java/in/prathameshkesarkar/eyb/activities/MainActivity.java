package in.prathameshkesarkar.eyb.activities;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.prathameshkesarkar.eyb.R;
import in.prathameshkesarkar.eyb.adapter.MainPagerAdapter;

/**
 * Created by prathameshkesarkar on 21/03/17.
 */

public class MainActivity extends AppCompatActivity {


    Unbinder unbinder;

    @BindView(R.id.am_background_view)
    View backgroundView;

    @BindView(R.id.am_view_pager)
    ViewPager viewPager;

    @BindView(R.id.am_fab)
    FloatingActionButton actionButton;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    private ArgbEvaluator evaluator;
    private float fabX;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());

        tabLayout.setupWithViewPager(viewPager);


        viewPager.setAdapter(pagerAdapter);
        evaluator = new ArgbEvaluator();

        actionButton.hide();

        final int purple = ContextCompat.getColor(this, R.color.resume_color_primary);
        final int yellow = ContextCompat.getColor(this, R.color.colorPrimary);

        final int darkPurple = ContextCompat.getColor(this, R.color.resume_color_dark);
        final int darkYellow = ContextCompat.getColor(this, R.color.colorPrimaryDark);


        fabX = actionButton.getX();
        Log.d("FabPosition", String.valueOf(fabX));

        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int backgroundColor, darkBackgroundColor = 0;
                if (position == 0) {
                    backgroundColor = (Integer) evaluator.evaluate(positionOffset, yellow, purple);
                    backgroundView.setBackgroundColor(backgroundColor);

                    actionButton.setTranslationX(fabX * positionOffset);


                    darkBackgroundColor = (int) evaluator.evaluate(positionOffset, darkYellow, darkPurple);


                } else if (position == 1) {
                    backgroundColor = (Integer) evaluator.evaluate(positionOffset, purple, yellow);
                    backgroundView.setBackgroundColor(backgroundColor);

                    darkBackgroundColor = (int) evaluator.evaluate(positionOffset, darkPurple, darkYellow);

                }
                tabLayout.setBackgroundColor(darkBackgroundColor);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    getWindow().setStatusBarColor(darkBackgroundColor);
            }

            @Override
            public void onPageSelected(int position) {
                Drawable drawable;
                switch (position) {

                    case 1:
                        drawable = ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_bussiness);
                        drawable.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.cyan_accent), PorterDuff.Mode.SRC_ATOP);
                        tabLayout.getTabAt(position).setIcon(drawable);

                        drawable = ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_profile);
                        drawable.setColorFilter(ContextCompat.getColor(MainActivity.this, android.R.color.white), PorterDuff.Mode.SRC_ATOP);
                        tabLayout.getTabAt(0).setIcon(drawable);
                        actionButton.hide();
                        break;
                    case 0:
                        drawable = ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_profile);
                        drawable.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.cyan_accent), PorterDuff.Mode.SRC_ATOP);
                        tabLayout.getTabAt(position).setIcon(drawable);

                        drawable = ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_bussiness);
                        drawable.setColorFilter(ContextCompat.getColor(MainActivity.this, android.R.color.white), PorterDuff.Mode.SRC_ATOP);
                        tabLayout.getTabAt(1).setIcon(drawable);
                        actionButton.show();

                        break;
                    default:
                        drawable = null;
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_profile);
        Drawable drawable = ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_bussiness);
        drawable.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.cyan_accent), PorterDuff.Mode.SRC_ATOP);
        tabLayout.getTabAt(1).setIcon(drawable);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
