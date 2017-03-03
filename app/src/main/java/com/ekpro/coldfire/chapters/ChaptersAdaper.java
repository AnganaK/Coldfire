package com.ekpro.coldfire.chapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ekpro.coldfire.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by incredible on 4/7/16.
 */
public class ChaptersAdaper extends BaseAdapter {
    private final Context context;
    private final ArrayList<String> chapters;

    public ChaptersAdaper(Context context, ArrayList<String> chapters) {
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
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.tvChapterName.setText(chapters.get(position));

        return view;
    }

    public class ViewHolder {

        @Bind(R.id.tvChapterName)
        TextView tvChapterName;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
