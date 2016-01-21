package ru.olegslobodin.layoutinflaterlist;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String[] name = {"Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
            "Костя", "Игорь"};
    String[] position = {"Программер", "Бухгалтер", "Программер",
            "Программер", "Бухгалтер", "Директор", "Программер", "Охранник"};
    int salary[] = {13000, 10000, 13000, 13000, 10000, 15000, 13000, 8000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linLayout);
        LayoutInflater layoutInflater = getLayoutInflater();

        int color1 = Color.parseColor("#FF303F9F");
        int color2 = Color.parseColor("#FF3F51B5");

        for (int i = 0; i < name.length; i++) {
            View item = layoutInflater.inflate(R.layout.item, linearLayout, false);
            TextView tvName = (TextView) item.findViewById(R.id.tvName);
            TextView tvPosition = (TextView) item.findViewById(R.id.tvPosition);
            TextView tvSalary = (TextView) item.findViewById(R.id.tvSalary);
            tvName.setText(name[i]);
            tvPosition.setText("Должность: " + position[i]);
            tvSalary.setText("Оклад: " + salary[i]);
            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            item.setBackgroundColor((i % 2 == 0) ? color1 : color2);
            linearLayout.addView(item);
        }
    }
}
