package ru.olegslobodin.activityresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvText;
    Button btnColor;
    Button btnAlign;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText = (TextView) findViewById(R.id.tvText);

        btnColor = (Button) findViewById(R.id.btnColor);
        btnAlign = (Button) findViewById(R.id.btnAlign);

        btnColor.setOnClickListener(this);
        btnAlign.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        int requestCode = 0;
        switch (v.getId()) {
            case R.id.btnColor:
                requestCode = 1;
                intent = new Intent(this, ru.olegslobodin.activityresult.Color.class);
                break;
            case R.id.btnAlign:
                requestCode = 2;
                intent = new Intent(this, Alignment.class);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    tvText.setTextColor(data.getIntExtra("color", android.graphics.Color.WHITE));
                    break;
                case 2:
                    tvText.setGravity(data.getIntExtra("align", Gravity.LEFT));
            }
        } else
            Toast.makeText(this, "Something was wrong :(", Toast.LENGTH_LONG).show();
    }
}
