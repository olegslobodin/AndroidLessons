package ru.olegslobodin.dynamiclayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(linearLayout.VERTICAL);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setContentView(linearLayout, layoutParams);

        LayoutParams viewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        TextView textView = new TextView(this);
        textView.setText("Hi :)");
        linearLayout.addView(textView, viewParams);

        Button button = new Button(this);
        button.setText("I'm useless");
        linearLayout.addView(button, viewParams);

        LinearLayout.LayoutParams gravityParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        gravityParams.gravity = Gravity.CENTER;

        Button button1 = new Button(this);
        button1.setText("Center");
        linearLayout.addView(button1, gravityParams);
    }
}
