package com.example.testautomation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class TableActivity extends AppCompatActivity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_table);

      final String dateText = getIntent().getStringExtra("date_text");
      TextView header = findViewById(R.id.table_header);
      header.setText("Rows for: " + dateText);

      LinearLayout rowsContainer = findViewById(R.id.rows_container);
      Random rnd = new Random();
      int rows = 12 + rnd.nextInt(8); // 12-19 rows

      for (int i = 0; i < rows; ++i) {
         final int idx = i;
         LinearLayout row = new LinearLayout(this);
         row.setOrientation(LinearLayout.HORIZONTAL);
         row.setPadding(16, 16, 16, 16);
         row.setId(View.generateViewId());

         TextView tv = new TextView(this);
         tv.setText("Row " + idx);
         tv.setTextSize(16f);
         tv.setPadding(12, 12, 12, 12);
         // set resource-id-like tag so uiautomator dump shows resource-id attribute
         tv.setTag("Table_RowElement_" + idx);
         tv.setClickable(true);
         tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent it = new Intent(TableActivity.this, DetailActivity.class);
               it.putExtra("row_index", idx);
               startActivity(it);
            }
         });
         row.addView(tv);
         rowsContainer.addView(row);
      }

      Button backBtn = findViewById(R.id.table_back_button);
      backBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            finish();
         }
      });
   }
}
