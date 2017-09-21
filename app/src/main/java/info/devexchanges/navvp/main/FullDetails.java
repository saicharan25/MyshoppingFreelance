package info.devexchanges.navvp.main;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import info.devexchanges.navvp.R;

public class FullDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_details);
        TextView redtextview=(TextView)findViewById(R.id.redtextview);
        redtextview.setPaintFlags(redtextview.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        ImageView backimage=(ImageView)findViewById(R.id.backimage);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
