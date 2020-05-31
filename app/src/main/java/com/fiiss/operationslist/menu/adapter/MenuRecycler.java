package com.fiiss.operationslist.menu.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fiiss.operationslist.R;
import com.fiiss.operationslist.entities.MenuApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import java.util.ArrayList;
import java.util.List;

public class MenuRecycler extends RecyclerView.Adapter<ViewHolderMenu> {

    private Context mContext;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;

    private List<String> mMenuIds = new ArrayList<>();
    private List<MenuApp> menuAppList = new ArrayList<>();

    public MenuRecycler(final Context context, DatabaseReference databaseReference) {
        this.mContext = context;
        this.mDatabaseReference = databaseReference;

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {

                MenuApp menuApp = dataSnapshot.getValue(MenuApp.class);
                mMenuIds.add(dataSnapshot.getKey());
                menuAppList.add(menuApp);
                notifyItemInserted(menuAppList.size() - 1);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {

                MenuApp newMenuApp = dataSnapshot.getValue(MenuApp.class);
                String commentKey = dataSnapshot.getKey();

                int commentIndex = mMenuIds.indexOf(commentKey);

                if (commentIndex > -1) {
                    menuAppList.set(commentIndex, newMenuApp);
                    notifyItemChanged(commentIndex);
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String commentKey = dataSnapshot.getKey();
                int commentIndex = mMenuIds.indexOf(commentKey);
                if (commentIndex > -1) {
                    // Remove data from the list
                    mMenuIds.remove(commentIndex);
                    menuAppList.remove(commentIndex);
                    notifyItemRemoved(commentIndex);
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                MenuApp movedMenuApp = dataSnapshot.getValue(MenuApp.class);
                String commentKey = dataSnapshot.getKey();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(mContext, "Failed to load comments.", Toast.LENGTH_SHORT).show();
            }
        };

        databaseReference.addChildEventListener(childEventListener);

        // Store reference to listener so it can be removed on app stop
        mChildEventListener = childEventListener;
    }

    @NonNull
    @Override
    public ViewHolderMenu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_menu, parent, false);
        return new ViewHolderMenu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMenu holder, @SuppressLint("RecyclerView") final int position) {
        MenuApp menuApp = menuAppList.get(position);
        holder.title.setText(menuApp.getName());
    }

    @Override
    public int getItemCount() {
        return menuAppList.size();
    }

    public void cleanupListener() {
        if (mChildEventListener != null) {
            mDatabaseReference.removeEventListener(mChildEventListener);
        }
    }

}
