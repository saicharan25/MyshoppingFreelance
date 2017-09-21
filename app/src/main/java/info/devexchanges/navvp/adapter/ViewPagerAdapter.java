package info.devexchanges.navvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import info.devexchanges.navvp.home.Fragment1;
import info.devexchanges.navvp.home.Fragment2;
import info.devexchanges.navvp.home.Fragment3;
import info.devexchanges.navvp.home.Fragmentfood;
import info.devexchanges.navvp.home.Fragmentshop;
import info.devexchanges.navvp.listview.frag;
import info.devexchanges.navvp.listview.fragresturentslist;


/**
 * Created by acer on 9/10/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position ==0) {
            return new Fragment1();
        } else if (position == 1) {
            return new Fragmentfood();
        } else return new frag();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
