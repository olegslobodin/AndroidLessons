package ru.olegslobodin.simplecalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int op = -1; //-1 - no operation
    double value = 0, prevValue = 0;
    //    Button btnPlus, btnSub, btnMul, btnDiv, btnRoot, btnPow, btnPercent, btnEqu;
    Button[] buttons;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons = new Button[8];
        initButtons();
        editText = (EditText) findViewById(R.id.editText);
    }

    void initButtons() {
        buttons[0] = (Button) findViewById(R.id.buttonPlus);
        buttons[1] = (Button) findViewById(R.id.buttonSub);
        buttons[2] = (Button) findViewById(R.id.buttonMul);
        buttons[3] = (Button) findViewById(R.id.buttonDiv);
        buttons[4] = (Button) findViewById(R.id.buttonRoot);
        buttons[5] = (Button) findViewById(R.id.buttonPow);
        buttons[6] = (Button) findViewById(R.id.buttonPercent);
        buttons[7] = (Button) findViewById(R.id.buttonEqu);

        for (int i = 0; i < buttons.length; i++)
            buttons[i].setOnClickListener(this);
    }

    int getButtonIndex(Button btn) {
        for (int i = 0; i < buttons.length; i++)
            if (buttons[i] == btn)
                return i;
        return -1;
    }

    @Override
    public void onClick(View v) {
        String str = editText.getText().toString();
        int index = getButtonIndex((Button) v);
        switch (index) {
            case -1:
                Toast.makeText(this, "Unexpected button pressed!", Toast.LENGTH_LONG).show();
                break;
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
                op = index;
                if (str.length() != 0)  //if we aren't changing the sign of operation
                    try {
                        prevValue = Double.valueOf(str);
                    } catch (Exception e) {
                        Toast.makeText(this, "Hey, easy!", Toast.LENGTH_SHORT).show();
                    }
                editText.setText("");
                break;
            case 4:
                op = index;
                printResult();
                break;
            case 7:
                printResult();
        }
    }

    void printResult() {
        String str = editText.getText().toString();
        if (str.length() == 0)
            return;
        double result = 0;
        try {
            value = Double.valueOf(str);
        } catch (Exception e) {
            Toast.makeText(this, "Hey, easy!", Toast.LENGTH_SHORT).show();
        }
        switch (op) {
            case -1:
                result = value;
                break;
            case 0:
                result = prevValue + value;
                break;
            case 1:
                result = prevValue - value;
                break;
            case 2:
                result = prevValue * value;
                break;
            case 3:
                result = prevValue / value;
                break;
            case 4:
                result = Math.sqrt(value);
                break;
            case 5:
                result = Math.pow(prevValue, value);
                break;
            case 6:
                result = prevValue / 100 * value;
                break;
        }
        op = -1;
        editText.setText(String.valueOf(result));
    }
}
