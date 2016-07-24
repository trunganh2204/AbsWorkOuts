package anh.excam.com.testapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import anh.excam.com.testapp.fragment.FragmentExercises;
import anh.excam.com.testapp.fragment.FragmentMore;
import anh.excam.com.testapp.fragment.FragmentSetting;
import anh.excam.com.testapp.fragment.FragmentWorkOuts;

/**
 * Created by Anh on 6/27/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentWorkOuts();
            case 1:
                return new FragmentExercises();
            case 2:
                return new FragmentSetting();
            case 3:
                return new FragmentMore();
            default:
                break;
        }
        return null;
    }
    @Override
    public int getCount() {
        return 4;
    }
}
