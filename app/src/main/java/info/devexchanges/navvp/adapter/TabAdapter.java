package info.devexchanges.navvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeep on 25/5/17.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();
    private Fragment mPrimaryFragment;

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    private String getFragmentTag(int viewPagerId, int fragmentPosition)
    {
        return "android:switcher:" + viewPagerId + ":" + fragmentPosition;
    }
    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

/*    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object object = super.instantiateItem(container, position);
        mFragments.add((Fragment) object);
        return object;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
        mFragments.remove(object);
        super.destroyItem(container, position, object);
    }

    @Override public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        mPrimaryFragment = (Fragment) object;
    }

    *//** Returns currently visible (primary) fragment *//*
    public Fragment getPrimaryFragment() {
        return mPrimaryFragment;
    }

    *//** Returned list can contain null-values for not created fragments *//*
    public List<Fragment> getFragments() {
        return Collections.unmodifiableList(mFragments);
    }*/
}
