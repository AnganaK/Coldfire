package com.incredible.computerstudies;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import com.incredible.computerstudies.Utils.AbstractBaseActivity;
import com.incredible.computerstudies.chapters.ChaptersActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class ProblemsActivity extends AbstractBaseActivity {

    private ArrayList<String> chapters;

    @Bind(R.id.toolbarHome)
    Toolbar toolbarHome;

    @Bind(R.id.tvActivityTitle)
    TextView tvActivityTitle;

    @Bind(R.id.lvQuestions)
    ListView lvQuestions;

    @Bind(R.id.tvSubmit)
    TextView tvSubmit;


    public static int selectedArray[];


    public int chapterNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems);

        chapters = new ArrayList<>();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            chapterNumber = extras.getInt(AppConstant.CHAPTER_NO, 1);
        }

        initToolbar();

        setUpAdapter();

    }

    private void setUpAdapter() {
        chapters.add("1");
        chapters.add("2");
        chapters.add("3");
        chapters.add("4");
        chapters.add("5");
        chapters.add("6");
        chapters.add("7");
        chapters.add("8");
        chapters.add("9");
        chapters.add("10");
        chapters.add("11");
        chapters.add("12");
        chapters.add("13");
        chapters.add("14");
        chapters.add("15");
        chapters.add("16");
        chapters.add("17");
        chapters.add("18");
        chapters.add("19");
        chapters.add("20");

        selectedArray = new int[chapters.size()];
        for(int i = 0; i<chapters.size(); i++){
            selectedArray[i] = -1;
        }

        QuestionsAdapter progressAdpter = new QuestionsAdapter(context, chapters);
        lvQuestions.setAdapter(progressAdpter);
/*
        lvQuestions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ProblemsActivity.this, ProblemsActivity.class);
                intent.putExtra(AppConstant.CHAPTER_NO, Integer.parseInt(chapters.get(position).split(" ")[1]));
                startActivity(intent);

            }
        });
*/

    }

    private void initToolbar() {
        toolbarHome.setBackgroundColor(getResources().getColor(R.color.classesGreen));
//        toolbarHome.setNavigationIcon(R.drawable.menu_purple);
        tvActivityTitle.setText("Chapter " + chapterNumber);
        tvActivityTitle.setTextColor(ContextCompat.getColor(context, R.color.classesTextColor));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.getWindow().setStatusBarColor(getResources().getColor(R.color.classesGreen));
        }
    }


    @OnClick(R.id.tvSubmit)
    public void submit() {
        Intent intent = new Intent(context, ChaptersActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(AppConstant.FROM_EXAM_REPORT, true);
        intent.putExtra(AppConstant.CHAPTER_NO, chapterNumber - 1);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }
}
