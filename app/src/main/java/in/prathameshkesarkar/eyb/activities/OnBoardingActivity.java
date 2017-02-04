package in.prathameshkesarkar.eyb.activities;

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

    ViewPager.PageTransformer pageTransformer;
    int[] colors = {R.color.colorStatusIndigoIntro, R.color.colorStatusRedIntro, R.color.colorStatusGreyIntro};

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
        pageTransformer = new ZoomOutPageTransformer();
        setFadeAnimation();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
