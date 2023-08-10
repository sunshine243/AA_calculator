package my.edu.utar.individual_assignment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class amount extends AppCompatActivity {

    private LinearLayout linearLayout;
    List<my.edu.utar.individual_assignment.display> display = new ArrayList<>();
    List<my.edu.utar.individual_assignment.display> display2 = new ArrayList<>();
    private double condition = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount);

        Button bt_continues = findViewById(R.id.btn_continues3);
        Button bt_calculate = findViewById(R.id.btn_calculate3);
        Button bt_back = findViewById(R.id.back3);
        EditText totalNumPerson = findViewById(R.id.et_tp2);
        EditText et_ta = findViewById(R.id.et_ta2);
        linearLayout = findViewById(R.id.ll3);

        bt_continues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numOfPerson;
                try{
                    linearLayout.removeAllViews();
                    numOfPerson = Integer.parseInt(totalNumPerson.getText().toString());
                    createLine(numOfPerson);
                    bt_continues.setVisibility(View.GONE);
                    bt_calculate.setVisibility(View.VISIBLE);
                    bt_back.setVisibility(View.VISIBLE);
                } catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Please fill in the Number of Person", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.removeAllViews();
                bt_continues.setVisibility(View.VISIBLE);
                bt_calculate.setVisibility(View.GONE);
                bt_back.setVisibility(View.GONE);
                totalNumPerson.setText("");
            }
        });

        bt_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!empty()) {
                    double totalAmount = 0;
                    int totalPerson = 0;
                    double sumOfAmount = 0;
                    try {
                        totalAmount = Double.parseDouble(et_ta.getText().toString());
                        totalPerson = Integer.parseInt(totalNumPerson.getText().toString());
                        // Calculate and display percentages
                        double t_percentage = 0;
                        try {
                            if (condition == 1) {
                                for (int i = 0; i < display.size(); i++) {
                                    sumOfAmount += display.get(i).getNumber();
                                }
                                if (sumOfAmount == totalAmount) {
                                    for (int i = 0; i < display.size(); i++) {
                                        display.get(i).setResult(0);
                                    }
                                    Intent intent = new Intent(amount.this, result.class);
                                    intent.putExtra("TOTAL_AMOUNT", String.valueOf(totalAmount));
                                    intent.putExtra("PERSON", String.valueOf(totalPerson));
                                    intent.putExtra("DISPLAY", (Serializable) display);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Not equal total amount", Toast.LENGTH_LONG).show();
                                    condition++;
                                }
                            } else {
                                for (int i = 0; i < display2.size(); i++) {
                                    sumOfAmount += display2.get(i).getNumber();
                                }
                                if (sumOfAmount == totalAmount && display2.size() == display.size()) {
                                    for (int k = 0; k < display2.size(); k++) {
                                        double original = display.get(k).getNumber();
                                        double change = display2.get(k).getNumber();
                                        double result = change - original;
                                        display.get(k).setResult(result);
                                    }
                                    Intent intent = new Intent(amount.this, result.class);
                                    intent.putExtra("TOTAL_AMOUNT", String.valueOf(totalAmount));
                                    intent.putExtra("PERSON", String.valueOf(totalPerson));
                                    intent.putExtra("DISPLAY", (Serializable) display);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Still not equal total amount", Toast.LENGTH_SHORT).show();
                                    condition++;
                                }
                            }
                        } catch (NumberFormatException e) {
                            Toast.makeText(getApplicationContext(), "Please enter the percentage", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(), "Please enter total amount and number of person.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }



    private void createLine(int numOfPerson) {
        for(int i = 0; i < numOfPerson; i++){
            LinearLayout hll = new LinearLayout(this);

            EditText name = new EditText(this);
            name.setHint("Person "+ (i+1));
            name.setTextColor(Color.BLACK);
            name.setTextSize(18.0f);
            hll.addView(name,layoutParams());

            EditText percentage = new EditText(this);
            percentage.setHint("Amount");
            percentage.setTextColor(Color.BLACK);
            percentage.setTextSize(18.0f);
            percentage.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            hll.addView(percentage,layoutParams());

            linearLayout.addView(hll);
        }
    }

    private LinearLayout.LayoutParams layoutParams(){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1);
        return layoutParams;
    }

    private boolean empty(){
        //Get value from the et1 & et2
        // Iterate through the 'show' (LinearLayout)
        // 0 is the title
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View childView = linearLayout.getChildAt(i);
            //Ensure the childView is LinearLayout
            if (childView instanceof LinearLayout) {
                LinearLayout ll = (LinearLayout) childView;
                //Ensure the linearLayout has total two child
                if (ll.getChildCount() == 2) {
                    //Get position on the first child of linearLayout
                    View et1View = ll.getChildAt(0);
                    //Ensure the et1View is EditText
                    if (et1View instanceof EditText) {
                        EditText et1 = (EditText) et1View;
                        String name = et1.getText().toString().trim();
                        //Get position on the first child of linearLayout
                        View et2View = ll.getChildAt(1);
                        //Ensure the et2View is EditText
                        if (et2View instanceof EditText) {
                            EditText et2 = (EditText) et2View;
                            String input = et2.getText().toString().trim();
                            if (!name.isEmpty() && !input.isEmpty()) {
                                try {
                                    double inputValue = Double.parseDouble(input);
                                    if(condition == 1){
                                        display.add(new display(name, inputValue, 4));
                                    }else{
                                        display2.add(new display(name, inputValue, 4));
                                    }

                                }catch (NumberFormatException e){
                                    Toast.makeText(getApplicationContext(), "Please the value", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "Please fill both name and value", Toast.LENGTH_SHORT).show();
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}