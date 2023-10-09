package com.kmd.uog.mhike;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kmd.uog.mhike.adapter.HikeAdapter;
import com.kmd.uog.mhike.database.DatabaseHelper;
import com.kmd.uog.mhike.database.Hike;

import java.util.ArrayList;
import java.util.List;

public class HikeListActivity extends AppCompatActivity {

    private List<Hike> hikeList = new ArrayList<>();
    private DatabaseHelper databaseHelper;
    private HikeAdapter hikeAdapter;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hike_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseHelper = new DatabaseHelper(getBaseContext());
        hikeAdapter = new HikeAdapter(hikeList);
        hikeAdapter.setListener(new HikeAdapter.ClickListener() {
            @Override
            public void onButtonClick(int position, View v, long id) {
                Hike hike= hikeList.get(position);
                if (id==R.id.btnDetail )
                {
                    //TODO goto hike detail and observation entry

                }else if ( id== R.id.btnEdit)
                {
                    //TODO pass data to HikeEntryActivity
                    gotoEntry(hike);
                }
                else if( id== R.id.btnDelete)
                {
                    //delete the records

                    long result = databaseHelper.deleteHike(hike.getId());
                    if( result != 1)
                    {
                        //data deleting error
                        new AlertDialog.Builder(getBaseContext()).setTitle("Error").setMessage("Can't delete this hike").show();
                    }else {
                        // extract the data again from database
                        search();
                    }
                }
            }
        });
        recyclerView.setAdapter(hikeAdapter);
        FloatingActionButton fab = findViewById(R.id.fabAddHike);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), HikeEntryActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        search();
    }

    private void search(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    hikeList = databaseHelper.searchHike("");
                    hikeAdapter.setHikeList(hikeList);
                    hikeAdapter.notifyDataSetChanged();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    /* *
    *Pass the data to HikeEntryActivity for editing the hike
     */
    private void gotoEntry(Hike hike)
    {
        Intent intent = new Intent(this,HikeEntryActivity.class);
        intent.putExtra(Hike.ID, hike.getId());
        intent.putExtra(Hike.NAME, hike.getName());
        intent.putExtra(Hike.LOCATION, hike.getLocation());
        intent.putExtra(Hike.DATE, hike.getDate());
        intent.putExtra(Hike.PARKING,hike.getParking());
        intent.putExtra(Hike.LENGTH, hike.getLength() +"");
        intent.putExtra(Hike.DIFFICULTY, hike.getDifficulty());
        intent.putExtra(Hike.DESCRIPTION, hike.getDescription());
        intent.putExtra(Hike.ADDITIONAL1, hike.getAdditional1());
        intent.putExtra(Hike.ADDITIONAL2, hike.getAdditional2());
        startActivity(intent);

    }
}