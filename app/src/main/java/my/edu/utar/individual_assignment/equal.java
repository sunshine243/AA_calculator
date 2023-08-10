package my.edu.utar.individual_assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class equal extends AppCompatActivity {

    private EditText totalAmount;
    private  EditText totalNumPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equal);

        Button bt_calculate = findViewById(R.id.btn_continues2);

       totalAmount = findViewById(R.id.et_ta3);
       totalNumPerson = findViewById(R.id.et_tp3);

        bt_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAndDisplayResult();
                totalAmount.setText("");
                totalNumPerson.setText("");
            }
        });
    }

    private void calculateAndDisplayResult() {
        EditText totalAmount = findViewById(R.id.et_ta3);
        EditText totalNumOfPerson = findViewById(R.id.et_tp3);

        List<display> display = new ArrayList<>();
        try{
            double amount = Double.parseDouble(totalAmount.getText().toString());
            int person = Integer.parseInt(totalNumOfPerson.getText().toString());

            double total_Amount = amount/person;
            display.add(new display(total_Amount,1));

            Intent intent = new Intent(equal.this, result.class);
            intent.putExtra("TOTAL_AMOUNT", String.format("%.2f",amount));
            intent.putExtra("PERSON", String.valueOf(person));
            intent.putExtra("DISPLAY", (Serializable) display);
            startActivity(intent);

        } catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Try a new number pls!", Toast.LENGTH_SHORT).show();
        }
    }
}