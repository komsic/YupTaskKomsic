package com.example.brainbell.yuptaskkomsic;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class BookingActivity extends AppCompatActivity {

    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            finish();
        } else {
            username = bundle.getString("username");

            TextView titleTxt = (TextView) findViewById(R.id.booking_title_txt);
            titleTxt.setText(bundle.getString("title", ""));

            TextView directorTxt = (TextView) findViewById(R.id.booking_director_txt);
            directorTxt.setText("Director: " + bundle.getString("director", ""));

            TextView durationTxt = (TextView) findViewById(R.id.booking_duration_txt);
            durationTxt.setText(String.valueOf(bundle.getInt("duration", 0)) + " mins");

            TextView plotTxt = (TextView) findViewById(R.id.booking_plot_txt);
            plotTxt.setText(bundle.getString("plot", ""));

            TextView ratingTxt = (TextView) findViewById(R.id.booking_rating_txt);
            ratingTxt.setText(String.valueOf(bundle.getDouble("rating", 0)));

            ImageView posterImg = (ImageView) findViewById(R.id.booking_poster_img);
            posterImg.setImageResource(bundle.getInt("imageResourceId", 0));
        }



    }

    public void onClickBookNow(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final ScrollView rootLayer = (ScrollView) findViewById(R.id.booking_root_layer);
        builder.setMessage("Are you sure you want to book").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar snackbar = Snackbar.make(rootLayer, "Thanks " + username
                                        + " Your ticket is booked... Cheers!!!",
                                Snackbar.LENGTH_LONG)
                                .setAction("Dismiss", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        finish();
                                    }
                                });
                        snackbar.show();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
