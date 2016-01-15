package ru.olegslobodin.dynamiclayout2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout llMain;
    RadioGroup rgGravity;
    EditText etName;
    Button btnCreate;
    Button btnClear;

    int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        llMain = (LinearLayout) findViewById(R.id.llMain);
        rgGravity = (RadioGroup) findViewById(R.id.rgGravity);
        etName = (EditText) findViewById(R.id.etName);

        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreate:
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(wrapContent, wrapContent);
                switch (rgGravity.getCheckedRadioButtonId()) {
                    case R.id.rbLeft:
                        layoutParams.gravity = Gravity.LEFT;
                        break;
                    case R.id.rbCenter:
                        layoutParams.gravity = Gravity.CENTER;
                        break;
                    case R.id.rbRight:
                        layoutParams.gravity = Gravity.RIGHT;
                        break;
                }
                Button button = new Button(this);
                button.setText(etName.getText());
                llMain.addView(button, layoutParams);
                etName.setText("");
                break;
            case R.id.btnClear:
                llMain.removeAllViews();
                Toast.makeText(this, "Cleared", Toast.LENGTH_SHORT).show();
        }
    }
}
