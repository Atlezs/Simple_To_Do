package sg.edu.rp.c346.id2006248.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etElement;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    ListView lvColour;
    ArrayList<String> alActivity;
    Spinner spnAddOrRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etElement = findViewById(R.id.editTextColour);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnDelete = findViewById(R.id.buttonDeleteItem);
        btnClear = findViewById(R.id.buttonClearItem);
        lvColour = findViewById(R.id.listViewColour);
        spnAddOrRemove = findViewById(R.id.spinner);

        alActivity = new ArrayList<String>();

        ArrayAdapter<String> aaActivity = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,alActivity);

        lvColour.setAdapter(aaActivity);

        spnAddOrRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        etElement.setHint(R.string.hint1);
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        etElement.setHint(R.string.hint2);
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String color = etElement.getText().toString();
                alActivity.add(color);
                aaActivity.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if((alActivity.isEmpty() && etElement.getText().toString().equals("")) || (alActivity.isEmpty())){
                    Toast.makeText(MainActivity.this, R.string.empty, Toast.LENGTH_SHORT).show();
                }
                else if(etElement.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, R.string.no_index, Toast.LENGTH_SHORT).show();
                }

                else if(Integer.parseInt(etElement.getText().toString()) > alActivity.size() - 1){
                    Toast.makeText(MainActivity.this, R.string.wrong_index, Toast.LENGTH_SHORT).show();
                }else{
                    int pos = Integer.parseInt(etElement.getText().toString());
                    alActivity.remove(pos);
                    aaActivity.notifyDataSetChanged();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alActivity.clear();
                aaActivity.notifyDataSetChanged();
            }
        });

    }
}