package com.ekpro.coldfire.chapters;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ekpro.coldfire.AppConstant;
import com.ekpro.coldfire.R;
import com.ekpro.coldfire.Utils.AbstractFragmentActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by incredible on 3/7/16.
 */
public class ChaptersActivity extends AbstractFragmentActivity {

    private ActionBarDrawerToggle drawerToggle;
    private boolean isAutomatic = false;
    private Fragment f;
    private String screenName = "Navigation Drawer";
    private boolean isTemp;
    private LinearLayout llRepair;
    private LinearLayout llInstallation;
    private LinearLayout llRoutineService;
    private MenuItem menuItem;
    FragmentManager fragmentManager;
//    AppPreferences prefs;

    @Bind(R.id.toolbarHome)
    public Toolbar toolbarHome;

    @Bind(R.id.tvActivityTitle)
    public TextView tvActivityTitle;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.vStatusBarOffset)
    View vStatusBarOffset;

    @Bind(R.id.vBelowToolbar)
    View vBelowToolbar;

    @Bind(R.id.lvChapters)
    ListView lvChapters;

    private ArrayList<String> chapters;
    public static int chapterPosition = 0;
    private boolean isFromExamReport = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        boolean isNotification = false;
//        boolean isDiagnose = false;
//        boolean isFromDiagnosisReport = false;

        chapters = new ArrayList<>();

        chapters.add("chapter 1");
        chapters.add("chapter 2");
        chapters.add("chapter 3");
        chapters.add("chapter 4");
        chapters.add("chapter 5");
        chapters.add("chapter 6");
        chapters.add("chapter 7");
        chapters.add("chapter 8");
        chapters.add("chapter 9");
        chapters.add("chapter 10");
        chapters.add("chapter 11");
        chapters.add("chapter 12");
        chapters.add("chapter 13");
        chapters.add("chapter 14");


        isAutomatic = false; // did hard code false for manually diagnose & true for automatic

//        prefs = new AppPreferences(this);
//        prefs.saveDiagnosis(false);
//        prefs.saveLastActiveScreen(screenName);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            isFromExamReport = extras.getBoolean(AppConstant.FROM_EXAM_REPORT, false);
            chapterPosition = extras.getInt(AppConstant.CHAPTER_NO, 0);
//            isFromDiagnosisReport = extras.getBoolean(AppConstant.FROM_DIAGNOSIS_REPORT);
//            isNotification = extras.getBoolean("isNotification", false);
//            isDiagnose = extras.getBoolean("isDiagnose", false);
        }


        setTransitions();
        setToolbarAsActionBar();
        initDrawer();
        setFragmentManager();

//            rlServiceRequest.setVisibility(View.GONE);
        if(isFromExamReport){
            replaceFragment(ChaptersFragment.newInstance());
            tvActivityTitle.setText("Chapter " + Integer.toString(chapterPosition + 1));
        } else {
            replaceFragment(ProgressFragment.newInstance());
        }

//        setNavCounts();
    }

    private void setFragmentManager() {
        fragmentManager = this.getSupportFragmentManager();
    }


    private void setUpBottomSheetContent() {
    }

//    @OnClick(R.id.ivDrawer)
//    public void openDrawer() {
//        drawerLayout.openDrawer(Gravity.LEFT);
//    }

    @Override
    public void onBackPressed() {

        Fragment f = this.getSupportFragmentManager().findFragmentById(R.id.flHomeContainer);

        if (f instanceof ProgressFragment) {
            // can do something with f
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(Gravity.LEFT);
                return;
            }
            finish();
        } else if (f instanceof ChaptersFragment) {

            ((ChaptersFragment) f).onBackPressed();

        } else {

            replaceFragment(ProgressFragment.newInstance());
//            rlServiceRequest.setVisibility(View.VISIBLE);
            tvActivityTitle.setText("Your Progress");

        }
    }

    public void setToolbarAsActionBar() {

        toolbarHome.setTitle("Your Progress");
        toolbarHome.setNavigationIcon(R.drawable.menu_purple);
        tvActivityTitle.setText("Your Progress");
//        setSupportActionBar(toolbarHome);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            vStatusBarOffset.setVisibility(View.VISIBLE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.getWindow().setStatusBarColor(getResources().getColor(R.color.classesGreen));
        }

    }

    public void setTransitions() {

        //Activity transitions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Fade());
            getWindow().setEnterTransition(new Slide());
        }

    }

    private void initDrawer() {

        ChaptersAdaper chaptersAdaper = new ChaptersAdaper(context, chapters);
        lvChapters.setAdapter(chaptersAdaper);
        lvChapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(ChaptersActivity.this, "position clicked: " + position, Toast.LENGTH_SHORT).show();
                setFragmentManager();
                f = fragmentManager.findFragmentById(R.id.flHomeContainer);
                if (f instanceof ChaptersFragment && chapterPosition == position) {
                    // can do something with f
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    chapterPosition = position;

                } else {

                    chapterPosition = position;

                    replaceFragment(ChaptersFragment.newInstance());
                    tvActivityTitle.setText("Chapter " + Integer.toString(chapterPosition + 1));
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbarHome, R.string.app_name, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                Fragment f = getSupportFragmentManager().findFragmentById(R.id.flHomeContainer);

                if (f instanceof ProgressFragment) {
                    vBelowToolbar.setVisibility(View.GONE);
                } else {
                    vBelowToolbar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                recordScreensData("Side menu");

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            }
        };

        drawerToggle.setDrawerIndicatorEnabled(false);
//        toolbarHome.setNavigationIcon(R.drawable.menu_purple);
//        drawerToggle.setHomeAsUpIndicator(R.drawable.menu_purple);

        drawerToggle.syncState();

        drawerLayout.setDrawerListener(drawerToggle);

        toolbarHome.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f = ChaptersActivity.this.getSupportFragmentManager().findFragmentById(R.id.flHomeContainer);
                drawerLayout.openDrawer(Gravity.LEFT);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

//        setScreenImpression("Home Container");

        invalidateOptionsMenu();
    }

    static final ButterKnife.Action<View> DISABLE_NAV_ITEM = new ButterKnife.Action<View>() {
        @Override
        public void apply(View view, int index) {
            view.setVisibility(View.GONE);
        }
    };

    static final ButterKnife.Action<View> ENABLE_NAV_ITEM = new ButterKnife.Action<View>() {
        @Override
        public void apply(View view, int index) {
            view.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        if (menuItem.getItemId() == android.R.id.home) {

            return true;
        } /*else if (menuItem.getItemId() == R.id.tbLoginProfile) {

            if (Hawk.get(AppConstant.IS_TEMP_CONSUMER)) {

                Intent intent = new Intent(HomeActivity.this, LoginDiveInActivity.class);
                intent.putExtra(AppConstant.FROM_PROBLEM_ACTIVITY, false);
                startActivity(intent);

            }

            return true;
        }*/

        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected int getContainerId() {
        return R.id.flHomeContainer;
    }

//    @OnClick(R.id.rlHome)
//    public void navHome() {
//        createButtonClickImpression("Navigation Drawer", "Home");
//
//        f = this.getSupportFragmentManager().findFragmentById(R.id.flHomeContainer);
//        if (f instanceof HomeFragmentNew) {
//            // can do something with f
//            drawerLayout.closeDrawer(Gravity.LEFT);
//
//        } else {
//
//            replaceFragment(HomeFragmentNew.newInstance());
//            rlServiceRequest.setVisibility(View.VISIBLE);
//            tvActivityTitle.setText("");
//            drawerLayout.closeDrawer(Gravity.LEFT);
//        }
//    }

//    @OnClick(R.id.rlInRepair)
//    public void navInRepair() {

//        createButtonClickImpression("Navigation Drawer", "In Service");
//
//        f = this.getSupportFragmentManager().findFragmentById(R.id.flHomeContainer);
//        if (f instanceof InRepairFragment) {
//            // can do something with f
//            drawerLayout.closeDrawer(Gravity.LEFT);
//        } else {
//
//            rlServiceRequest.setVisibility(View.GONE);
//            replaceFragment(InRepairFragment.newInstance());
//            tvActivityTitle.setText("IN SERVICE");
//            drawerLayout.closeDrawer(Gravity.LEFT);
//        }
//    }

//    @OnClick(R.id.rlPayments)
//    public void navPayments() {
//
//        createButtonClickImpression("Navigation Drawer", "Payments");
//
//        drawerLayout.closeDrawer(Gravity.LEFT);
//        Utilities.instance.shortSnack(rlHomeActivity, "Payments");
//    }

    @OnClick(R.id.rlProgress)
    public void navMyProgress() {


        f = this.getSupportFragmentManager().findFragmentById(R.id.flHomeContainer);
        if (f instanceof ProgressFragment) {
            // can do something with f
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            replaceFragment(ProgressFragment.newInstance());
            tvActivityTitle.setText("YOUR PROGRESS");
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
    }


}
