package com.fiiss.operationslist.areas.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fiiss.operationslist.R;
import com.fiiss.operationslist.areas.view.ActivityDetail;
import com.fiiss.operationslist.entities.Areas;
import com.fiiss.operationslist.menu.adapter.ViewHolderMenu;

import java.util.List;
import java.util.Locale;

public class DetailRecycler extends RecyclerView.Adapter<ViewHolderMenu> {

    private Context mContext;
    private ActivityDetail activityDetail;
    private List<Areas> areasList;

    public DetailRecycler(final Context context, List<Areas> areasList, ActivityDetail activityDetail) {
        this.mContext = context;
        this.areasList = areasList;
        this.activityDetail = activityDetail;

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
        final Areas areas = areasList.get(position);

        if (!Locale.getDefault().getLanguage().equals("en")) {
            holder.title.setText(areas.getName());
        } else {
            holder.title.setText(areas.getNameUS());
        }

        holder.contentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityDetail.onClicRecycle(areas);
            }
        });


    }

    @Override
    public int getItemCount() {
        return areasList.size();
    }

}
