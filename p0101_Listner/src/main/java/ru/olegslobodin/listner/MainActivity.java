package ru.olegslobodin.listner;

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

        OnClickListener listner = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.btnOk:
                        tvOut.setText("Нажата ОК");
                        break;
                    case R.id.btnCancel:
                        tvOut.setText("Нажата Cancel");
                }
            }
        };

        btnOk.setOnClickListener(listner);
        btnCancel.setOnClickListener(listner);
    }
}
