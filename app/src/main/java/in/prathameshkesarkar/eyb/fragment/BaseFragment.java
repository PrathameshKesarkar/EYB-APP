package in.prathameshkesarkar.eyb.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by prathameshkesarkar on 08/04/17.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutRes(), container, false);
        inCreateView(rootView, container, savedInstanceState);
        return rootView;
    }

    @LayoutRes
    public abstract int getLayoutRes();

    public abstract void inCreateView(View rootView, ViewGroup container, Bundle savedInstanceState);
}
