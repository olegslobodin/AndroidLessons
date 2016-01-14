package ru.olegslobodin.onclickbuttons;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvOut;
    Button btnOk;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOut = (TextView) findViewById(R.id.tvOut);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        OnClickListener lstOk = new OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOut.setText("Нажата ОК");
            }
        };

        OnClickListener lstCancel = new OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOut.setText("Нажата Cancel");
            }
        };

        btnOk.setOnClickListener(lstOk);
        btnCancel.setOnClickListener(lstCancel);
    }
}
