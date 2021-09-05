package rajaapps.com.dimensionfitness.fragments.enquiresfragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rajaapps.com.dimensionfitness.R;
import rajaapps.com.dimensionfitness.adapters.EnquiresFragmentViewPagerAdapter;
import rajaapps.com.dimensionfitness.fragments.feedsfragments.FeedMainFragment;

/**
 * Created by Velmurugan on 7/17/2017.
 */

public class EnquiresFragment  extends Fragment {

    private static final String TAG = FeedMainFragment.class.getSimpleName();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public EnquiresFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feeds_main_fragment, container, false);
        getActivity().setTitle("Enquires");
        tabLayout = (TabLayout)view.findViewById(R.id.tabs);
        viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new EnquiresFragmentViewPagerAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
