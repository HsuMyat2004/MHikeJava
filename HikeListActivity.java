package com.kmd.uog.mhike;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    public static final int UPDATE_REQUEST =1;

    public static final int SEARCH_REQUEST=2;

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
                    gotoDetailView(hike);
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
                        search("");
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
                startActivityForResult(intent, UPDATE_REQUEST);
            }
        });

        search("");

            EditText txtSearch= findViewById(R.id.txtSearch);
            Button btnSearch= findViewById(R.id.btnSearch);
            Button btnAdvancedSearch= findViewById(R.id.btnAdvancedSearch);
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    search(txtSearch.getText().toString());
                }
            });
            btnAdvancedSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(getBaseContext(),HikeAdvancedSearchActivity.class);
                    startActivityForResult(intent, SEARCH_REQUEST);
                }
            });
    }



    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        search("");
    }

    private void search(String keyword){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    hikeList = databaseHelper.searchHike(keyword);
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
        fillIntentData(intent, hike);
        startActivityForResult(intent, UPDATE_REQUEST);

    }


    private void gotoDetailView(Hike hike)
    {
        Intent intent = new Intent(this,HikeDetailViewActivity.class);
        fillIntentData(intent, hike);
        startActivity(intent);

    }

    private void fillIntentData(Intent intent, Hike hike)
    {
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if ( requestCode== UPDATE_REQUEST && resultCode== RESULT_OK)
        {
            search("");
        }else if (requestCode==SEARCH_REQUEST && resultCode==RESULT_OK)
        {
            String name= data.getStringExtra(Hike.NAME);
            String location= data.getStringExtra(Hike.LOCATION);
            String date= data.getStringExtra(Hike.DATE);
            String length= data.getStringExtra(Hike.LENGTH);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        hikeList = databaseHelper.searchHike(name,location,date,(length!=null && !length.isEmpty()? Double.parseDouble(length):null));
                        hikeAdapter.setHikeList(hikeList);
                        hikeAdapter.notifyDataSetChanged(); //refresh the data

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
