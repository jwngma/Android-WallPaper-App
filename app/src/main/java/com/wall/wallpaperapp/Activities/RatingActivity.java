package com.wall.wallpaperapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.tapadoo.alerter.Alerter;
import com.wall.wallpaperapp.R;

public class RatingActivity extends AppCompatActivity {
    private static final String TAG = "RatingActivity";
    private ImageView chr_place;
    private TextView rate_value;
    private RatingBar rating_star;
    private Button feedback_Btn;
    private String star;

    Animation char_place_anim, bg_button_animate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);



        chr_place = findViewById(R.id.char_place);
        rate_value = findViewById(R.id.rate_value);
        rating_star = findViewById(R.id.rating);
        feedback_Btn = findViewById(R.id.feedbackBtn);

        char_place_anim = AnimationUtils.loadAnimation(this, R.anim.char_place_animate);
        chr_place.startAnimation(char_place_anim);

        bg_button_animate = AnimationUtils.loadAnimation(this, R.anim.bg_button_animate);
        //feedback_Btn.startAnimation(bg_button_animate);
        feedback_Btn.setVisibility(View.INVISIBLE);

        rating_star.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                star = String.valueOf((int) (rating_star.getRating()));

                if (star.equals("1")) {
                    chr_place.setImageResource(R.drawable.sad);
                    chr_place.startAnimation(char_place_anim);
                    rate_value.setText("One Start");
                    feedback_Btn.setVisibility(View.VISIBLE);
                    feedback_Btn.startAnimation(bg_button_animate);
                } else if (star.equals("2")) {
                    chr_place.setImageResource(R.drawable.one_star);
                    chr_place.startAnimation(char_place_anim);
                    rate_value.setText("Two Start");
                    feedback_Btn.setVisibility(View.VISIBLE);
                    feedback_Btn.startAnimation(bg_button_animate);
                } else if (star.equals("3")) {
                    chr_place.setImageResource(R.drawable.three_star);
                    chr_place.startAnimation(char_place_anim);
                    rate_value.setText("Three Start");
                    feedback_Btn.setVisibility(View.VISIBLE);
                    feedback_Btn.startAnimation(bg_button_animate);
                } else if (star.equals("4")) {
                    chr_place.setImageResource(R.drawable.four_star);
                    chr_place.startAnimation(char_place_anim);
                    rate_value.setText("Four Start");
                    feedback_Btn.setVisibility(View.VISIBLE);
                    feedback_Btn.startAnimation(bg_button_animate);
                } else if (star.equals("5")) {
                    chr_place.setImageResource(R.drawable.five_star);
                    chr_place.startAnimation(char_place_anim);
                    rate_value.setText("Five Start");
                    feedback_Btn.setVisibility(View.VISIBLE);
                    feedback_Btn.startAnimation(bg_button_animate);
                }
            }
        });
        /*feedback_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RatingActivity.this, "thank you for rating us with " + star, Toast.LENGTH_SHORT).show();
            }
        });
*/

        feedback_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alerter.create(RatingActivity.this)
                        .setTitle("FeedBack!!!")
                        .setText("Thank you for rating us with " + star)
                        .setBackgroundColorRes(R.color.colorPrimary)
                        .enableSwipeToDismiss()
                        .setDuration(500)
                        /*.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(RatingActivity.this, "Thank you for rating us with " + star, Toast.LENGTH_SHORT).show();
                            }
                        })*/
                        .show();

            }
        });


    }
}
