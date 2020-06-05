package com.fiiss.operationslist.operations_performed.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fiiss.operationslist.R;
import com.fiiss.operationslist.operations_performed.adapter.TableRecycler;
import com.fiiss.operationslist.utilities.Constantes;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityOperationsUnimagdalena extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private RecyclerView recycleOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations);

        mDatabase = FirebaseDatabase.getInstance().getReference().child(Constantes.JSON_OPERATIONS);

        recycleOperations = findViewById(R.id.recycleOperations);

        getDataFirebase();
    }

    private void getDataFirebase() {
        TableRecycler tableRecycler = new TableRecycler(this, mDatabase);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recycleOperations.setLayoutManager(mLayoutManager);
        recycleOperations.setItemAnimator(new DefaultItemAnimator());
        recycleOperations.setAdapter(tableRecycler);
    }

}