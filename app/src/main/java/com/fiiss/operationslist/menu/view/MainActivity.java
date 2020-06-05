package com.fiiss.operationslist.menu.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fiiss.operationslist.R;
import com.fiiss.operationslist.areas.view.ActivityDetail;
import com.fiiss.operationslist.entities.MenuApp;
import com.fiiss.operationslist.menu.adapter.MenuRecycler;
import com.fiiss.operationslist.menu.interfaces.OnclicRecycleAdapter;
import com.fiiss.operationslist.operations_performed.view.ActivityOperationsUnimagdalena;
import com.fiiss.operationslist.utilities.Constantes;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements OnclicRecycleAdapter {

    private DatabaseReference mDatabase;
    private RecyclerView rcyMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference().child(Constantes.JSON_MENU);

        rcyMenu = findViewById(R.id.rcyMenu);

        getMenu();

    }

    public void getMenu() {

        MenuRecycler mAdapter = new MenuRecycler(this, mDatabase, this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        rcyMenu.setLayoutManager(mLayoutManager);
        rcyMenu.setItemAnimator(new DefaultItemAnimator());
        rcyMenu.setAdapter(mAdapter);

    }

    @Override
    public void onClicRecycle(MenuApp menuApp) {
        if (menuApp.getUid() == 3) {
            Intent intent = new Intent(this, ActivityOperationsUnimagdalena.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, ActivityDetail.class);
            intent.putExtra("data", (Serializable) menuApp);
            startActivity(intent);
        }

    }
}
