package com.incredible.computerstudies.Utils;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import butterknife.ButterKnife;

;

/**
 * Created by dakshal on 04/11/15.
 */
public abstract class AbstractFragmentActivity extends AbstractBaseActivity implements AbstractBaseFragment.FragmentTransacListener,
        AbstractBaseFragment.FragmentAttachListener {

    protected AbstractBaseFragment currentFragment;
    private String TAG = "AbstractFragmentActivity";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Logger.v("onCreate");

    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
            return;
        }

        if (currentFragment != null) {
            if (!currentFragment.onBackPressed()) {
                super.onBackPressed();
                return;
            }
        }

        super.onBackPressed();
    }

    private void transact(AbstractBaseFragment baseFragment, boolean shouldReplace) {
        if (getContainerId() == -1) {
//            Logger.e("Specify container");
            return;
        }

        if (shouldReplace) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerId(), baseFragment).addToBackStack(null);
        transaction.commit();
    }

    public void addFragment(AbstractBaseFragment baseFragment) {
        baseFragment.animate(baseFragment);
        transact(baseFragment, false);
    }

    public void replaceFragment(AbstractBaseFragment baseFragment) {
        baseFragment.animate(baseFragment);
        transact(baseFragment, true);
    }

    @Override
    public void requestTransaction(AbstractBaseFragment baseFragment, boolean shouldReplace) {
        baseFragment.animate(baseFragment);
        transact(baseFragment, shouldReplace);
    }

    @Override
    public void onAttached(AbstractBaseFragment fragment) {
        currentFragment = fragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
            getSupportFragmentManager().popBackStack();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    protected abstract int getContainerId();

    protected void animateFragment() {
    }

}
