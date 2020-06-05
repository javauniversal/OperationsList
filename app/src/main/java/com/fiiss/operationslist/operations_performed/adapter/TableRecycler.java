package com.fiiss.operationslist.operations_performed.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fiiss.operationslist.R;
import com.fiiss.operationslist.entities.MenuApp;
import com.fiiss.operationslist.entities.ParameterFirebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class TableRecycler extends RecyclerView.Adapter<ViewHolderTable> {

    private Context mContext;

    private List<String> mMenuIds = new ArrayList<>();
    private List<ParameterFirebase> parameterFirebaseArrayList = new ArrayList<>();

    public TableRecycler(final Context context, DatabaseReference databaseReference) {
        this.mContext = context;

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {

                ParameterFirebase parameterFirebase = dataSnapshot.getValue(ParameterFirebase.class);
                mMenuIds.add(dataSnapshot.getKey());
                parameterFirebaseArrayList.add(parameterFirebase);
                notifyItemInserted(parameterFirebaseArrayList.size());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {

                ParameterFirebase parameterFirebasenew = dataSnapshot.getValue(ParameterFirebase.class);
                String commentKey = dataSnapshot.getKey();

                int commentIndex = mMenuIds.indexOf(commentKey);

                if (commentIndex > -1) {
                    parameterFirebaseArrayList.set(commentIndex, parameterFirebasenew);
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
                    parameterFirebaseArrayList.remove(commentIndex);
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
                Toast.makeText(mContext, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        };

        databaseReference.addChildEventListener(childEventListener);

    }

    @NonNull
    @Override
    public ViewHolderTable onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_table, parent, false);
        return new ViewHolderTable(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTable holder, @SuppressLint("RecyclerView") final int position) {

        int rowPos = holder.getAdapterPosition();

        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            holder.txtNumber.setBackgroundResource(R.drawable.table_header_cell_bg);
            holder.txtOperation.setBackgroundResource(R.drawable.table_header_cell_bg);
            holder.txtData.setBackgroundResource(R.drawable.table_header_cell_bg);
            holder.txtResult.setBackgroundResource(R.drawable.table_header_cell_bg);

            holder.txtNumber.setTextColor(Color.parseColor("#FFFFFF"));
            holder.txtOperation.setTextColor(Color.parseColor("#FFFFFF"));
            holder.txtData.setTextColor(Color.parseColor("#FFFFFF"));
            holder.txtResult.setTextColor(Color.parseColor("#FFFFFF"));

            holder.txtNumber.setTypeface(Typeface.DEFAULT_BOLD);
            holder.txtOperation.setTypeface(Typeface.DEFAULT_BOLD);
            holder.txtData.setTypeface(Typeface.DEFAULT_BOLD);
            holder.txtResult.setTypeface(Typeface.DEFAULT_BOLD);

        } else {
            ParameterFirebase parameterFirebase = parameterFirebaseArrayList.get(position-1);

            // Content Cells. Content appear here
            holder.txtNumber.setBackgroundResource(R.drawable.table_content_cell_bg);
            holder.txtOperation.setBackgroundResource(R.drawable.table_content_cell_bg);
            holder.txtData.setBackgroundResource(R.drawable.table_content_cell_bg);
            holder.txtResult.setBackgroundResource(R.drawable.table_content_cell_bg);

            holder.txtNumber.setText(String.format("%s", position));
            holder.txtOperation.setText(parameterFirebase.getOperation());
            holder.txtData.setText(parameterFirebase.getParameterOne());
            holder.txtResult.setText(parameterFirebase.getResult());
        }


    }

    @Override
    public int getItemCount() {
        return parameterFirebaseArrayList.size()+1;
    } // one more to add header row

}
