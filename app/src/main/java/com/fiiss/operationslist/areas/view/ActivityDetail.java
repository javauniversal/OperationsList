package com.fiiss.operationslist.areas.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fiiss.operationslist.R;
import com.fiiss.operationslist.areas.adapter.DetailRecycler;
import com.fiiss.operationslist.entities.MenuApp;
import com.fiiss.operationslist.menu.adapter.MenuRecycler;

public class ActivityDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        RecyclerView rcyDetail = findViewById(R.id.rcyDetail);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        MenuApp menuApp = (MenuApp) bundle.getSerializable("data");
        assert menuApp != null;

        DetailRecycler mAdapter = new DetailRecycler(this, menuApp.getAreas());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        rcyDetail.setLayoutManager(mLayoutManager);
        rcyDetail.setItemAnimator(new DefaultItemAnimator());
        rcyDetail.setAdapter(mAdapter);

    }
}