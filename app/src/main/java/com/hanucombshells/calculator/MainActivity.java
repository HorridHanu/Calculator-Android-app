package com.hanucombshells.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import soup.neumorphism.NeumorphButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView resultTv, solutionTv;
    NeumorphButton buttonC, buttonPercent;
    NeumorphButton buttonPlus, buttonMinus, buttonMultiply, buttonDivide, buttonEquals;
    NeumorphButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    NeumorphButton buttonAc, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Finds id's
        resultTv = findViewById(R.id.resultTv);
        solutionTv = findViewById(R.id.solutionTv);


//        Assign id
        assignId(buttonC, R.id.button_c);
        assignId(buttonPercent, R.id.button_percent);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(buttonMinus, R.id.button_subtract);
        assignId(buttonEquals, R.id.button_equals);
        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonPlus, R.id.button_plus);
        assignId(buttonAc, R.id.button_ac);
        assignId(buttonDot, R.id.button_dot);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(button0, R.id.button_0);
    }

    void assignId(NeumorphButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener((View.OnClickListener) this);
    }

    @SuppressLint("SetTextI18n")
    public void onClick(View view) {
        NeumorphButton button = (NeumorphButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("AC")) {
            resultTv.setText("00");
            solutionTv.setText("");
            return;
        }

        if (buttonText.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }

        if (buttonText.equals("$")) {
            if (dataToCalculate.equals("0") && dataToCalculate.equals("") && dataToCalculate.equals(" ")) {
                resultTv.setText("00");
            } else {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }


        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }

        solutionTv.setText(dataToCalculate);
        String finalResult = getReslut(dataToCalculate);

        if (!finalResult.equals("Error")) {
            resultTv.setText(finalResult);
        } else {
            resultTv.setText("00");
        }


    }

    String getReslut(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String result = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            return result;
        } catch (Exception e) {
            return "Error";
        }
    }
}