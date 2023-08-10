package my.edu.utar.individual_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class custom extends MainActivity {

    private LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        Button bt_percentage = findViewById(R.id.percentage);
        Button bt_ratio = findViewById(R.id.ratio);
        Button bt_amount = findViewById(R.id.amount);

        bt_percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_up);
                bt_percentage.startAnimation(anim);

                Intent intent = new Intent(getApplicationContext(), percentage.class);
                startActivity(intent);
            }
        });

        bt_ratio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_up);
                bt_ratio.startAnimation(anim);

                Intent intent = new Intent(getApplicationContext(), ratio.class);
                startActivity(intent);
            }
        });

        bt_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_up);
                bt_amount.startAnimation(anim);

                Intent intent = new Intent(getApplicationContext(), amount.class);
                startActivity(intent);
            }
        });
    }
}