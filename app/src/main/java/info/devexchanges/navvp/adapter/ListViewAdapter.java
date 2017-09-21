package info.devexchanges.navvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import info.devexchanges.navvp.R;
import info.devexchanges.navvp.home.Fragmentshop;
import info.devexchanges.navvp.home.SingleItemView;


public class ListViewAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	 LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	ImageLoader imageLoader;
	HashMap<String, String> resultp;


	public ListViewAdapter(Context context,
                           ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		data = arraylist;
		imageLoader = new ImageLoader(context);

	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		TextView rank;
		ImageView flag;
		View itemView = convertView;

		if (itemView == null) {
		//	inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//	convertView = inflater.inflate(R.layout.listview_item, parent, false);
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item, parent, false);
			resultp = data.get(position);

			rank = (TextView) convertView.findViewById(R.id.rank);
			flag = (ImageView) convertView.findViewById(R.id.flag);

			// Capture position and set results to the TextViews
			rank.setText(resultp.get(Fragmentshop.NAME));
			imageLoader.DisplayImage(resultp.get(Fragmentshop.IMAGE), flag);
			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// Get the position
					resultp = data.get(position);
					Intent intent = new Intent(context, SingleItemView.class);
					intent.putExtra("category_name", resultp.get(Fragmentshop.NAME));
					intent.putExtra("icon_image", resultp.get(Fragmentshop.IMAGE));
					context.startActivity(intent);

				}
			});
		}
			return itemView;
		}

}


