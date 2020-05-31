package com.fiiss.operationslist.menu.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.fiiss.operationslist.R;


public class ViewHolderMenu extends RecyclerView.ViewHolder {

    public TextView title;

    public ViewHolderMenu(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.lblTitle);
    }

}
