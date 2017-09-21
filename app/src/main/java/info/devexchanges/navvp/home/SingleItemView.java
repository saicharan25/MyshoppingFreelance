package info.devexchanges.navvp.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import info.devexchanges.navvp.R;
import info.devexchanges.navvp.adapter.ImageLoader;

public class SingleItemView extends Activity {
	// Declare Variables
	String rank;
	String country;
	String population;
	String flag;
	String position;
	ImageLoader imageLoader = new ImageLoader(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.singleitemview);

		Intent i = getIntent();
		// Get the result of rank
		rank = i.getStringExtra("category_name");
		// Get the result of flag
		flag = i.getStringExtra("icon_image");

		// Locate the TextViews in singleitemview.xml
		TextView txtrank = (TextView) findViewById(R.id.rank);


		// Locate the ImageView in singleitemview.xml
		ImageView imgflag = (ImageView) findViewById(R.id.flag);

		// Set results to the TextViews
		txtrank.setText(rank);


		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class
		imageLoader.DisplayImage(flag, imgflag);
	}
}