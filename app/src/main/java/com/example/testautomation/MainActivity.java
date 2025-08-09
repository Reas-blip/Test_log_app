package com.example.testautomation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      LinearLayout container = findViewById(R.id.dates_container);
      List<String> dates = generateDates(40);
      for (int i = 0; i < dates.size(); ++i) {
         final String dt = dates.get(i);
         TextView tv = new TextView(this);
         tv.setText(dt);
         tv.setTextSize(18f);
         tv.setPadding(20, 20, 20, 20);
         tv.setClickable(true);
         tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent it = new Intent(MainActivity.this, TableActivity.class);
               it.putExtra("date_text", dt);
               startActivity(it);
            }
         });
         container.addView(tv);
      }
   }

   private List<String> generateDates(int count) {
      List<String> out = new ArrayList<>();
      Calendar cal = Calendar.getInstance();
      Random rnd = new Random();
      for (int i = 0; i < count; ++i) {
         cal.add(Calendar.DAY_OF_MONTH, -rnd.nextInt(3) - 1);
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
         out.add(sdf.format(cal.getTime()));
      }
      Collections.reverse(out);
      return out;
   }
}
