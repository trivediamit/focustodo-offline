package com.simplelilfe.focustodo.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.simplelilfe.focustodo.R;
import com.simplelilfe.focustodo.database.DatabaseAdapter;

public class CreateTask extends AppCompatActivity {

    private DatabaseAdapter databaseAdapter;
    private EditText editTextToDoTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        setupViews();

        databaseAdapter = new DatabaseAdapter(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                NavUtils.navigateUpFromSameTask(this);
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    private void setupViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.create_task));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextInputLayout taskTitleWrapper = (TextInputLayout) findViewById(R.id.task_title_wrapper);
        taskTitleWrapper.setHint("I want to");

        editTextToDoTitle = (EditText) findViewById(R.id.task_title);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(databaseAdapter != null){
            databaseAdapter.createToDoEntry(editTextToDoTitle.getText().toString().trim());
        }else {
            Toast.makeText(CreateTask.this, "Database handle error", Toast.LENGTH_SHORT).show();
        }
    }
}
