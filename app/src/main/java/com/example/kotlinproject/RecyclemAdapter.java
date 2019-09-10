package com.example.kotlinproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kotlinproject.app.AppConstant;

import java.util.ArrayList;
import java.util.List;


public class RecyclemAdapter extends RecyclerView.Adapter<RecyclemAdapter.ComViewHolder> {


    private Context mContext;
    private List<String> mList = new ArrayList<>();


    public RecyclemAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public ComViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new ComViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComViewHolder viewHolder, final int i) {
        viewHolder.tv.setText(mList.get(i));
        viewHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mList.get(i);
                if (title.equals(AppConstant.LIST_TITLE1)) {
                    Intent intent = new Intent(mContext, KotlinMainActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void setData(ArrayList<String> list) {
        mList.clear();
        if (list != null && list.size() > 0) {
            mList.addAll(list);
            notifyDataSetChanged();
        } else {
            notifyDataSetChanged();
        }
    }


    class ComViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public ComViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tvItem);
        }
    }
}
