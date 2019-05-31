package c346.rp.edu.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText etTask;
Spinner options;
Button addTask, removeTask, clearTask;
ListView lvTasks;
ArrayList<String> tasksList;
ArrayAdapter taskaa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTask = findViewById(R.id.editTextTask);
        options = findViewById(R.id.spinnerOptions);
        addTask = findViewById(R.id.buttonAdd);
        removeTask = findViewById(R.id.buttonDelete);
        clearTask = findViewById(R.id.buttonClear);
        lvTasks = findViewById(R.id.taskList);

        tasksList = new ArrayList<String>();
        taskaa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, tasksList);
        lvTasks.setAdapter(taskaa);

        options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        removeTask.setEnabled(false);
                        etTask.setHint("Type in a new task here");
                    case 1:
                        addTask.setEnabled(false);
                        etTask.setHint("Type in index of a task to be removed");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTask.getText().toString();
                tasksList.add(task);
                taskaa.notifyDataSetChanged();
            }
        });
        removeTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tasksList.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT);
                } else {
                    int index = Integer.parseInt(etTask.getText().toString());
                    if (index < 0 || index > tasksList.size()) {
                         Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT);
                    } else {
                        tasksList.remove(index);
                        taskaa.notifyDataSetChanged();
                    }
                }
            }
        });
        clearTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tasksList.isEmpty() == false) {
                    tasksList.clear();
                    taskaa.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MainActivity.this, "You have no more task to clear", Toast.LENGTH_SHORT);
                }
            }
        });
    }
}
