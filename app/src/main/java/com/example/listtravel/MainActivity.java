package com.example.listtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    ListView lvTravel;
    ArrayAdapter<Travel> adapter;
    Button btnSave;
    EditText editText;
    Travel travel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTravel = findViewById(R.id.lvTravel);
        btnSave = findViewById(R.id.btnSave);
        editText = findViewById(R.id.editText);

//        Travel travel1 = new Travel(1, "Đà Lạt");
//        Travel travel2 = new Travel(2, "Vũng Tàu");
        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("tag", "onCreate");
//        db.addTravel(travel1);
//        db.addTravel(travel2);


        List<Travel> travelList = db.getAllTravel();
        adapter = new ArrayAdapter<>(this, R.layout.listview_listtravel, R.id.tvTen, travelList);
        lvTravel.setAdapter(adapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 0;
                String name = editText.getText().toString();
                travel = new Travel(name);
                db.addTravel(travel);
                adapter.add(travel);
                Toast.makeText(MainActivity.this, "Thêm travel thành công!!!", Toast.LENGTH_SHORT).show();
            }
        });

        lvTravel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageButton imageButton = view.findViewById(R.id.btnXoa);
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.deleteTravel(travelList, position);
                        recreate();
                    }
                });
                ImageView btnEdit = view.findViewById(R.id.btnEdit);
                String name = editText.getText().toString();
                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.updateTravel(name, travelList, position);
                        recreate();
                    }
                });
            }
        });
    }
}