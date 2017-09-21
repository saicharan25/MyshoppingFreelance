package info.devexchanges.navvp.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


public class CustomFonts extends AppCompatTextView {
	public CustomFonts(Context context) {
		super(context);
		setFont();

	}

	public CustomFonts(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFont();
	}

	public CustomFonts(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setFont();
	}

	private void setFont() {
		/*Typeface font = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/NotoSans-Regular.ttf");*/
		Typeface font = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/open-sans.regular.ttf");
		setTypeface(font, Typeface.NORMAL);

	}

}