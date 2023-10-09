package com.kmd.uog.mhike;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kmd.uog.mhike.database.DatabaseHelper;
import com.kmd.uog.mhike.database.Hike;

import java.text.BreakIterator;

public class HikeDetailActivity extends AppCompatActivity {
    String name, location, date, parking, difficulty, description, additional1, additional2;
    private int id = 0;
    private double length;

    private Hike hike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hike_detail);

        TextView lblName = findViewById(R.id.lblName);
        TextView lblLocation = findViewById(R.id.lblLocation);
        TextView lblDate = findViewById(R.id.lblDate);
        TextView lblParking = findViewById(R.id.lblParking);
        TextView lblLength = findViewById(R.id.lblLength);
        TextView lblDifficulty = findViewById(R.id.lblDifficulty);
        TextView lblDescription = findViewById(R.id.lblDescription);
        TextView lblAdditional1 = findViewById(R.id.lblAdditional1);
        TextView lblAdditional2 = findViewById(R.id.lblAdditional2);

        Button btnBack = findViewById(R.id.btnBack);
        Button btnSave = findViewById(R.id.btnSave);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getInt(Hike.ID, 0);
            name = bundle.getString(Hike.NAME);
            location = bundle.getString(Hike.LOCATION);
            date = bundle.getString(Hike.DATE);
            parking = bundle.getString(Hike.PARKING);
            length = Double.parseDouble(bundle.getString(Hike.LENGTH,"0"));
            difficulty = bundle.getString(Hike.DIFFICULTY);
            description = bundle.getString(Hike.DESCRIPTION);
            additional1 = bundle.getString(Hike.ADDITIONAL1);
            additional2 = bundle.getString(Hike.ADDITIONAL2);

            lblName.setText(name);
            lblLocation.setText(location);
            lblDate.setText(date);
            lblParking.setText(parking);
            lblLength.setText(length + "");
            lblDifficulty.setText(difficulty);
            lblDescription.setText(description);
            lblAdditional1.setText(additional1);
            lblAdditional2.setText(additional2);


            hike = new Hike(
                    id,
                    name,
                    location,
                    date,
                    parking,
                    length,
                    difficulty,
                    description,
                    additional1,
                    additional2,
                    null,
                    null
            );

        }// END OF IF
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext());
                long result = 0;
                if (id == 0) {
                    //this is new record
                    result = databaseHelper.saveHike(hike);
                } else {
                    //this is existing record
                    result = databaseHelper.updateHike(hike);
                }
                if (result > 0) {
                    //successful
                    new AlertDialog.Builder(HikeDetailActivity.this).setTitle("Success")
                            .setMessage("Hike data saved successfully").show();
                } else {
                    new AlertDialog.Builder(HikeDetailActivity.this).setTitle("Fail")
                            .setMessage("Hike data cannot be saved").show();
                }
            }
        });
    }
}
