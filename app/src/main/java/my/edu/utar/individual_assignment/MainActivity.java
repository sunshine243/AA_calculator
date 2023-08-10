package my.edu.utar.individual_assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_equal = findViewById(R.id.equal);
        Button bt_custom = findViewById(R.id.custom);

        bt_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_up);
                bt_equal.startAnimation(anim);

                Intent intent = new Intent(getApplicationContext(), equal.class);
                startActivity(intent);
            }
        });

        bt_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_up);
                bt_custom.startAnimation(anim);

                Intent intent = new Intent(getApplicationContext(), custom.class);
                startActivity(intent);
            }
        });
    }
}