package com.example.testautomation;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DetailActivity extends AppCompatActivity {
   private static final String[] PREDEFINED = {
      "Date","Time","Engine speed (RPM)","Frequency (HZ)",
      "Gen Excitation Voltage (V)","Gas pressure (mbar)",
      "Gas buffer tank pressure (bar)","Crank shaft casing pressure (mbar)",
      "Charge pressure (bar)","HT line inlet pressure (bar)",
      "LT Line inlet pressure (bar)","HT line outlet pressure (bar)",
      "LT Line outlet pressure (bar)","Cooling water pressure (bar)",
      "Oil Pressure (bar)","Room temp. (°C)","Return water temp. (°C)",
      "Cooling water Temp. (°C)","Oil Temp. (°C)",
      "Compressed outlet temp. (°C)","Charge Temp. (°C)",
      "Bearing temp.- DE (°C)","Bearing temp.-NDE (°C)",
      "Engine oil level","No. Radiator fan running",
      "Gas mixer 1 position (%)","Lambda (λ)",
      "Throttle valve position (%)","Compressor bypass(%)",
      "Power Factor","Running Hour","Avg. Voltage (V)",
      "Avg. Ampere (A)","Load ( KW )","KWH Meter",
      "GG FM 1 Opening","GG FM 1 Closing","GG FM 1 Consumption",
      "GG FM 2 Opening","GG FM 2 Closing","GG FM 2 Consumption",
      "Cylinder A1 (°C)","Cylinder A2 (°C)","Cylinder A3 (°C)",
      "Cylinder A4 (°C)","Cylinder A5 (°C)","Cylinder A6 (°C)",
      "Cylinder B1 (°C)","Cylinder B2 (°C)","Cylinder B3 (°C)",
      "Cylinder B4 (°C)","Cylinder B5 (°C)","Cylinder B6 (°C)",
      "Remarks"
   };

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_detail);

      LinearLayout container = findViewById(R.id.detail_container);
      Random rnd = new Random();

      // shuffle a bit
      List<String> keys = new ArrayList<>();
      for (String k : PREDEFINED) keys.add(k);
      Collections.shuffle(keys, rnd);

      for (String key : keys) {
         LinearLayout row = new LinearLayout(this);
         row.setOrientation(LinearLayout.HORIZONTAL);
         row.setPadding(12, 12, 12, 12);

         TextView label = new TextView(this);
         label.setText(key);
         label.setTextSize(14f);
         label.setPadding(8, 8, 8, 8);

         TextView value = new TextView(this);
         value.setText(generateValueForKey(key, rnd));
         value.setTextSize(14f);
         value.setPadding(8, 8, 8, 8);

         row.addView(label);
         row.addView(value);
         container.addView(row);
      }

      Button backBtn = findViewById(R.id.detail_back_button);
      backBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            finish();
         }
      });
   }

   private String generateValueForKey(String key, Random rnd) {
      if (key.contains("Temp") || key.contains("temp") || key.contains("Temperature")) {
         return String.format("%d°C", 20 + rnd.nextInt(60));
      } else if (key.toLowerCase().contains("rpm") || key.toLowerCase().contains("engine")) {
         return String.format("%d", 600 + rnd.nextInt(3000));
      } else if (key.toLowerCase().contains("voltage")) {
         return String.format("%.1f V", 100.0 + rnd.nextDouble() * 50.0);
      } else if (key.toLowerCase().contains("pressure") || key.toLowerCase().contains("bar")) {
         return String.format("%.2f", rnd.nextDouble() * 5.0);
      } else if (key.toLowerCase().contains("lambda")) {
         return String.format("%.2f", 0.8 + rnd.nextDouble() * 0.6);
      } else if (key.toLowerCase().contains("running hour")) {
         return String.format("%d h", rnd.nextInt(10000));
      } else if (key.toLowerCase().contains("remarks")) {
         return rnd.nextBoolean() ? "OK" : "Check"; 
      }
      return String.valueOf(rnd.nextInt(100));
   }
}
