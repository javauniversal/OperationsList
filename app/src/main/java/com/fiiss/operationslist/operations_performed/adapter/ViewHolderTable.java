package com.fiiss.operationslist.operations_performed.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fiiss.operationslist.R;


public class ViewHolderTable extends RecyclerView.ViewHolder {

    public TextView txtNumber;
    public TextView txtOperation;
    public TextView txtData;
    public TextView txtResult;

    public ViewHolderTable(View itemView) {
        super(itemView);
        txtNumber = itemView.findViewById(R.id.txtNumber);
        txtOperation = itemView.findViewById(R.id.txtOperation);
        txtData = itemView.findViewById(R.id.txtData);
        txtResult = itemView.findViewById(R.id.txtResult);

    }

}
