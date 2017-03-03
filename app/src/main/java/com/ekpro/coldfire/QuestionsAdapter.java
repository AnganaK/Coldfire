package com.incredible.computerstudies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by incredible on 4/7/16.
 */
public class QuestionsAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<String> chapters;
    private String TAG = "QuestionsAdapter";

    public QuestionsAdapter(Context context, ArrayList<String> chapters) {
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
    public View getView(final int position, View view, ViewGroup parent) {

        ViewHolder holder;

//        if (view != null) {
//            holder = (ViewHolder) view.getTag();
//        } else {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        holder = new ViewHolder(view);
        view.setTag(holder);
//        }


        holder.tvQuestionNumber.setText(chapters.get(position));
        holder.tvQuestion.setText("zxcvbnmasdfghjkl qwertyuiop asdfghjkl zxcvbnm, asdfghjkl qwertyuio sdfghjk xcvbnm, sdfghjkl ertyui");
        holder.rbOptionA.setText("Option A is the best option to choose. That's why when you dont know nothing try to choose option A.");
        holder.rbOptionA.setTextColor(context.getResources().getColor(R.color.black));
        holder.rbOptionB.setText("Option B is the last Option.");
        holder.rbOptionB.setTextColor(context.getResources().getColor(R.color.black));
        holder.rbOptionC.setText("Option C is the option choosen by most of the people.");
        holder.rbOptionC.setTextColor(context.getResources().getColor(R.color.black));
        holder.rbOptionD.setText("Option D is the least preferable option as most of the time it is all of the above.");
        holder.rbOptionD.setTextColor(context.getResources().getColor(R.color.black));

        if (ProblemsActivity.selectedArray[position] == -1) {

        } else {
            holder.rgOptions.check(holder.rgOptions.getChildAt(ProblemsActivity.selectedArray[position]).getId());
            RadioButton rb = (RadioButton) view.findViewById(holder.rgOptions.getCheckedRadioButtonId());
            rb.setTextColor(context.getResources().getColor(R.color.classesGreen));
        }

        final View finalView = view;
        holder.rgOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkPosition = -1;

                if(checkedId == group.getChildAt(0).getId()){
                    checkPosition = 0;
                } else if(checkedId == group.getChildAt(1).getId()){
                    checkPosition = 1;
                } else if(checkedId == group.getChildAt(2).getId()){
                    checkPosition = 2;
                } else if(checkedId == group.getChildAt(3).getId()){
                    checkPosition = 3;
                }

                if (ProblemsActivity.selectedArray[position] != -1) {
                    Log.d(TAG, "onCheckedChanged: " + ProblemsActivity.selectedArray[position]+ " position: "+checkPosition);
                    RadioButton previouslySelected = (RadioButton) group.getChildAt(ProblemsActivity.selectedArray[position]);
                    previouslySelected.setTextColor(context.getResources().getColor(R.color.black));
                }
                ProblemsActivity.selectedArray[position] = checkPosition;
                RadioButton rb = (RadioButton) finalView.findViewById(group.getCheckedRadioButtonId());
                rb.setTextColor(context.getResources().getColor(R.color.classesGreen));
            }
        });
        return view;
    }

    public class ViewHolder {

        @Bind(R.id.tvQuestion)
        TextView tvQuestion;

        @Bind(R.id.tvQuestionNumber)
        TextView tvQuestionNumber;

        @Bind(R.id.rgOptions)
        RadioGroup rgOptions;

        @Bind(R.id.rbOptionA)
        RadioButton rbOptionA;

        @Bind(R.id.rbOptionB)
        RadioButton rbOptionB;

        @Bind(R.id.rbOptionC)
        RadioButton rbOptionC;

        @Bind(R.id.rbOptionD)
        RadioButton rbOptionD;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
