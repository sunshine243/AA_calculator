package my.edu.utar.individual_assignment;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class display implements Serializable {
    private double number = 0;
    private double result = 0;
    private String name = "";
    private int breakdown = 0;

    public display(String name, double number, int breakdown) {
        this.number = number;
        this.name = name;
        this.breakdown = breakdown;
    }

    public double getNumber() {
        return number;
    }

    public display(double result, int breakdown) {
        this.result = result;
        this.breakdown = breakdown;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public int getBreakdown() {
        return breakdown;
    }

    public double getResult() {
        return result;
    }

    @Override
    public String toString() {
//        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
//        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
//        String dcm = decimalFormat.format(result);
        if (breakdown == 1) {
            return "\nTOTAL: RM" + String.format("%.2f", result) + "/person";
        }
            else if (breakdown == 2) {
                return "\nName: " + name +  "\nPercentage: " + number + "%"
                    + "\nAmount: RM" + String.format("%.2f", result);
            }
            else if (breakdown == 3){
                return "\nName: " + name + "\nRatio: " + number
                    + "\nAmount = RM" + String.format("%.2f", result);
            }
            else
                return  "\nName: " + name +  "\nAmount: RM" + String.format("%.2f", number)
                        + "\nAdd on: RM" + String.format("%.2f", result);
    }
}