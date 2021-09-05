package rajaapps.com.dimensionfitness.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import rajaapps.com.dimensionfitness.fragments.enquiresfragment.FollowupCallFragment;
import rajaapps.com.dimensionfitness.fragments.enquiresfragment.FollowupMessageFragment;
import rajaapps.com.dimensionfitness.fragments.enquiresfragment.ListEnquiresFragment;

/**
 * Created by Velmurugan on 7/17/2017.
 */

public class EnquiresFragmentViewPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = FeedMainFragmentViewPagerAdapter.class.getSimpleName();
    private static final int FRAGMENT_COUNT = 3;
    public EnquiresFragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ListEnquiresFragment();
            case 1:
                return new FollowupMessageFragment();
            case 2:
                return new FollowupCallFragment();
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
                return "Enquires";
            case 1:
                return "Followup SMS";
            case 2:
                return "Followup Calls";
        }
        return null;
    }
}