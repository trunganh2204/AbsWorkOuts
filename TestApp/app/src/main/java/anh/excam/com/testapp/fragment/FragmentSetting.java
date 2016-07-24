package anh.excam.com.testapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import anh.excam.com.testapp.R;

/**
 * Created by Anh on 5/14/2016.
 */
public class FragmentSetting extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_setting, container, false);
    }
}
