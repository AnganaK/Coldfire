package com.incredible.computerstudies.chapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.incredible.computerstudies.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by incredible on 4/7/16.
 */
public class ProgressAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<String> chapters;
    private String TAG = "ProgressAdapter";

    public ProgressAdapter(Context context, ArrayList<String> chapters) {
        this.context = context;
        this.chapters = chapters;
    }

    @Override
    public int getCount() {
        return chapters.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;

        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_progress_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.tvChapterNumber.setText(chapters.get(position));
        holder.tvChapterName.setText("Introduction to Programming");
        holder.pbChapterProgress.setProgress(30);

        return view;
    }

    public class ViewHolder {

        @Bind(R.id.tvChapterName)
        TextView tvChapterName;

        @Bind(R.id.tvChapterNumber)
        TextView tvChapterNumber;

        @Bind(R.id.pbChapterProgress)
        ProgressBar pbChapterProgress;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
