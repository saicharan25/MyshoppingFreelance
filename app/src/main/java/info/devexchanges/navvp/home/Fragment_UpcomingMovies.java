package info.devexchanges.navvp.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import info.devexchanges.navvp.R;
import info.devexchanges.navvp.adapter.GridAdapter;
import info.devexchanges.navvp.model.Result;
import info.devexchanges.navvp.model.UpcomingMovies;

/**
 * Created by sandeep on 9/6/17.
 */

@SuppressLint("ValidFragment")
public class Fragment_UpcomingMovies extends Fragment {

    private View rootView;
    private GridView gridview;

    List<Result> arr_upcomingmovieslist = new ArrayList<Result>();
    private int count=0;

    public Fragment_UpcomingMovies(List<Result> arr_upcomingmovieslist) {
        this.arr_upcomingmovieslist=arr_upcomingmovieslist;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        rootView = inflater.inflate(R.layout.fragment_upcoming, container, false);


     //   Log.d("frag","frag1");
        initView();
        // Log.d("moviedashboard_response", response);
        //  setMovieDetails(response);
        GridAdapter addapter = new GridAdapter(arr_upcomingmovieslist, getActivity(), "home");
        gridview.setAdapter(addapter);

        gridview.setFastScrollEnabled(false);

        gridview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
               // Log.d("scroll2", "gridcount" + (int) Math.round(gridview.getCount() / 2));


                if (gridview.getFirstVisiblePosition() == 0) {
                    count = gridview.getFirstVisiblePosition();
                    //   gridview.smoothScrollToPosition(count);
                    count = count + 1;
                    //  gridview.setSelection(count);
                //    Log.d("scroll2", "count " + count);


                } else if (count > 0) {


                    if (count < (int) Math.round(gridview.getCount()) / 2) {
                        //count=0;

                        count = count + 1;
                     //   Log.d("scroll2", "count 1 " + count);
                        gridview.setSelection(count);

                        //  gridview.smoothScrollToPosition(count);
                        //gridview.scr

                        // gridview.scrollListBy(count);

                    }
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

              //  Log.d("scroll", "1st Item" + firstVisibleItem + " / " + "Visible count" + visibleItemCount);
                //gridview.set(visibleItemCount);


            }
        });
        return rootView;
    }

    private void initView() {
        gridview = (GridView) rootView.findViewById(R.id.grid_maindasboard_list);
    }
}
