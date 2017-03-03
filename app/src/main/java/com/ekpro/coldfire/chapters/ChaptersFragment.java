package com.incredible.computerstudies.chapters;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incredible.computerstudies.AppConstant;
import com.incredible.computerstudies.ProblemsActivity;
import com.incredible.computerstudies.R;
import com.incredible.computerstudies.Utils.AbstractBaseFragment;

import butterknife.OnClick;

/**
 * Created by incredible on 3/7/16.
 */
public class ChaptersFragment extends AbstractBaseFragment {


    private int chapterNo = 1;

    @Override
    public View onCreateKnifeView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chapters, container, false);
    }

    @Override
    public String getTitle() {
        return "Chapter " + chapterNo;
    }

    public static ChaptersFragment newInstance() {
        Bundle args = new Bundle();

        ChaptersFragment fragment = new ChaptersFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
    }

    public void initToolbar() {
        ((ChaptersActivity) getActivity()).toolbarHome.setBackgroundColor(getResources().getColor(R.color.classesGreen));
        ((ChaptersActivity) getActivity()).toolbarHome.setNavigationIcon(R.drawable.menu_purple);
        ((ChaptersActivity) getActivity()).tvActivityTitle.setTextColor(ContextCompat.getColor(context, R.color.classesTextColor));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.classesGreen));
        }
    }

    @OnClick(R.id.cvTimedTest)
    public void timedTest() {
        Intent intent = new Intent(getActivity(), ProblemsActivity.class);
        intent.putExtra(AppConstant.CHAPTER_NO, ChaptersActivity.chapterPosition + 1);
        startActivity(intent);
    }

    @OnClick(R.id.cvPracticeTest)
    public void practiceTest() {
        Intent intent = new Intent(getActivity(), ProblemsActivity.class);
        intent.putExtra(AppConstant.CHAPTER_NO, ChaptersActivity.chapterPosition + 1);
        startActivity(intent);
    }
}