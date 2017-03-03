package com.ekpro.coldfire.chapters;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ekpro.coldfire.AppConstant;
import com.ekpro.coldfire.ProblemsActivity;
import com.ekpro.coldfire.R;
import com.ekpro.coldfire.Utils.AbstractBaseFragment;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by xps-windows on 3/3/2017.
 */
public class CurrentFragment  extends AbstractBaseFragment {

    private ArrayList<String> chapters;

    @Bind(R.id.lvChapterProgress)
    ListView lvChapterProgress;

    @Override
    public View onCreateKnifeView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.current_class_fragment, container, false);
    }

    @Override
    public String getTitle() {
        return "Current Classes";
    }

    public static ProgressFragment newInstance() {
        Bundle args = new Bundle();

        ProgressFragment fragment = new ProgressFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public void initToolbar() {
        ((ChaptersActivity) getActivity()).toolbarHome.setBackgroundColor(getResources().getColor(R.color.classesGreen));
        ((ChaptersActivity) getActivity()).toolbarHome.setNavigationIcon(R.drawable.menu_purple);
        ((ChaptersActivity) getActivity()).tvActivityTitle.setTextColor(ContextCompat.getColor(context, R.color.classesTextColor));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.classesGreen));
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chapters = new ArrayList<>();

        chapters.add("CSC252 Adv. Operating Systems");
        chapters.add("CSC270 Artificial Intelligence");
//        chapters.add("CSC284 Computer Networking, Processing and Communication");
//        chapters.add("chapter 7");
//        chapters.add("chapter 8");
//        chapters.add("chapter 9");

        initToolbar();

//        ProgressAdapter progressAdpter = new ProgressAdapter(context, chapters);
//        lvChapterProgress.setAdapter(progressAdpter);
//        lvChapterProgress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), ProblemsActivity.class);
//                intent.putExtra(AppConstant.CHAPTER_NO, Integer.parseInt(chapters.get(position).split(" ")[1]));
//                startActivity(intent);
//
//            }
//        });
    }
}
