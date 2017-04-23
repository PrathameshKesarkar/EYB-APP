package in.prathameshkesarkar.eyb.activities;

import android.animation.ArgbEvaluator;
import android.content.Intent;
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
    TabLayout tableLayout;

    private ArgbEvaluator evaluator;
    private float fabX;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        MainPagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());

        tableLayout.setupWithViewPager(viewPager);

        viewPager.setAdapter(pagerAdapter);
        evaluator = new ArgbEvaluator();

        final int purple = ContextCompat.getColor(this, R.color.resume_color_primary);
        final int yellow = ContextCompat.getColor(this, R.color.colorPrimary);

        fabX = actionButton.getX();
        Log.d("FabPosition", String.valueOf(fabX));

        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    backgroundView.setBackgroundColor((Integer) evaluator.evaluate(positionOffset, yellow, purple));
                    actionButton.setTranslationX(fabX * positionOffset);
                } else if (position == 1) {
                    backgroundView.setBackgroundColor(purple);
                    backgroundView.setBackgroundColor((Integer) evaluator.evaluate(positionOffset, purple, yellow));
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
