package com.test.kiran.fragmentsswitch;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.test.kiran.fragmentsswitch.home.BaseHomeFragment;
import com.test.kiran.fragmentsswitch.home.MenuItemFragment;
import com.test.kiran.fragmentsswitch.home.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements BaseHomeFragment.OnFragmentInteractionListener, MenuItemFragment.OnListFragmentInteractionListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BaseHomeFragment baseHomeFragment = new BaseHomeFragment();
        fragmentTransaction.add(R.id.home_fragment_container, baseHomeFragment, baseHomeFragment.getClass().getName());
        fragmentTransaction.addToBackStack(baseHomeFragment.getClass().getName());
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Log.d(TAG, item.toString());
    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        Log.d(TAG, "onBackPressed:" + count);
        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {

            getFragmentManager().popBackStack();
        }
    }
}
