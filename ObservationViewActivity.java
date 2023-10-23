package com.kmd.uog.mhike;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kmd.uog.mhike.database.Observation;

public class ObservationViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation_view);

        TextView lblObservation= findViewById(R.id.lblObservationViewObservation);
        TextView lblDate= findViewById(R.id.lblObservationViewDate);
        TextView lblComment= findViewById(R.id.lblObservationViewComment);
        TextView lblStr1= findViewById(R.id.lblObservationViewStr1);
        TextView lblStr2= findViewById(R.id.lblObservationViewStr2);

        Bundle bundle =getIntent().getExtras();
        if( bundle != null)
        {
            lblObservation.setText(bundle.getString(Observation.OBSERVATION));
            lblDate.setText(bundle.getString(Observation.DATE_TIME));
            lblComment.setText(bundle.getString(Observation.COMMENT));
            lblStr1.setText(bundle.getString(Observation.STR1));
            lblStr2.setText(bundle.getString(Observation.STR2));
        }

        Button btnClose= findViewById(R.id.btnObservationViewClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}