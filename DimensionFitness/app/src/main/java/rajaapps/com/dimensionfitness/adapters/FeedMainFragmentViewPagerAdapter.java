package rajaapps.com.dimensionfitness.adapters;

/**
 * Created by Velmurugan on 7/15/2017.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import rajaapps.com.dimensionfitness.fragments.feedsfragments.AllFeedsFragment;
import rajaapps.com.dimensionfitness.fragments.feedsfragments.MyFeedsFragment;

public class FeedMainFragmentViewPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = FeedMainFragmentViewPagerAdapter.class.getSimpleName();
    private static final int FRAGMENT_COUNT = 2;
    public FeedMainFragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new AllFeedsFragment();
            case 1:
                return new MyFeedsFragment();
        }
        return null;
    }
    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Feeds";
            case 1:
                return "My Feeds";

        }
        return null;
    }
}