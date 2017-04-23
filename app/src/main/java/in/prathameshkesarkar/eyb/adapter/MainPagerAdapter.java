package in.prathameshkesarkar.eyb.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.prathameshkesarkar.eyb.fragment.JobsFragment;
import in.prathameshkesarkar.eyb.fragment.ProfileFragment;

/**
 * Created by prathameshkesarkar on 08/04/17.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ProfileFragment();
            case 1:
                return new JobsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
