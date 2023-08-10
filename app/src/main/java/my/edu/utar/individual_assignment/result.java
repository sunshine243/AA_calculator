
package my.edu.utar.individual_assignment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String totalAmount = intent.getStringExtra("TOTAL_AMOUNT");
        String person = intent.getStringExtra("PERSON");
        List<display> display = (List<display>) intent.getSerializableExtra("DISPLAY");

        TextView totalAmountTextView = findViewById(R.id.totalAmount1);
        TextView numPersonTextView = findViewById(R.id.numPerson1);
        LinearLayout linearLayout = findViewById(R.id.resultShow);
        Button bt_home = findViewById(R.id.bt_home);
        Button bt_history = findViewById(R.id.bt_share);

        totalAmountTextView.setText("RM" + totalAmount);
        numPersonTextView.setText(person + " person");

        StringBuilder resultText = new StringBuilder();
        for (int i = 0; i < display.size(); i++) {
            TextView resultTextView = new TextView(this);


            resultTextView.setTextColor(getResources().getColor(R.color.black));
            resultTextView.setTextSize(getResources().getDimension(R.dimen.text_size_small));

            if(display.get(i).getBreakdown() == 4){
                if(display.get(i).getResult() != 0){
                    resultTextView.setTextColor(Color.RED);
                }
            }
            resultTextView.setText(display.get(i).toString());
            linearLayout.addView(resultTextView);
        }

        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_up);
                bt_home.startAnimation(anim);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button btn_share = findViewById(R.id.bt_share);
        Button btn_share1 = findViewById(R.id.bt_share1);

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                if(display.get(display.size() - 1).getBreakdown() == 0) {
                    intent.putExtra(Intent.EXTRA_TEXT, getEqualResult(totalAmount,person,display));
                }
                else{
                    intent.putExtra(Intent.EXTRA_TEXT,getCustomResult(totalAmount,person,display));
                }
                intent.setType("text/plain");

                if(intent.resolveActivity((getPackageManager()))!= null){
                    startActivity(intent);
                }
            }
        });

        btn_share1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                if(display.get(display.size() - 1).getBreakdown() == 0) {
                    intent.putExtra(Intent.EXTRA_TEXT, getEqualResult(totalAmount,person,display));
                }
                else{
                    intent.putExtra(Intent.EXTRA_TEXT,getCustomResult(totalAmount,person,display));
                }
                intent.setType("text/plain");

                if(intent.resolveActivity((getPackageManager()))!= null){
                    startActivity(intent);
                }
            }
        });
    }




    private String getEqualResult(String total_amount, String total_person, List<display> personList){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Total amount : " + total_amount
                + "\nNumber of person : " + total_person
                + "\nAmount/person : " + personList.get(personList.size() - 1).getNumber());
        return stringBuilder.toString();
    }

    private String getCustomResult(String total_amount, String total_person, List<display> personList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Total amount : " + total_amount
                + "\nNumber of person : " + total_person
                + "\n\nDetails of person:");
        for (int i = 0; i < personList.size(); i++) {
            String temp = personList.get(i).toString();
            stringBuilder.append(temp).append("\n"); // Separate items with newlines
        }
        return stringBuilder.toString();
    }
}
