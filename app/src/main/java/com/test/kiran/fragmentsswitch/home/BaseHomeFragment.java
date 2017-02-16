package com.test.kiran.fragmentsswitch.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.kiran.fragmentsswitch.R;
import com.test.kiran.fragmentsswitch.detail.DetailFragment;
import com.test.kiran.fragmentsswitch.home.dummy.DummyContent;
import com.test.kiran.fragmentsswitch.live.LiveFragment;
import com.test.kiran.fragmentsswitch.otherfragment.OtherBaseFragment;
import com.test.kiran.fragmentsswitch.player.PlayerFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BaseHomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BaseHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaseHomeFragment extends Fragment implements MenuItemFragment.OnListFragmentInteractionListener, LiveFragment.OnFragmentInteractionListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "BaseHomeFragment";

    private final int LIVE_TV = 1;
    private final int DETAIL = 2;
    private final int PLAYER = 3;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private LiveFragment liveFragment;
    private DetailFragment detailFragment;
    private PlayerFragment playerFragment;

    public BaseHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BaseHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BaseHomeFragment newInstance(String param1, String param2) {
        BaseHomeFragment fragment = new BaseHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        return inflater.inflate(R.layout.fragment_base_home, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MenuItemFragment menuItemFragment = MenuItemFragment.newInstance(this);
        fragmentTransaction.replace(R.id.base_left_layout, menuItemFragment, menuItemFragment.getClass().getName());
        fragmentTransaction.commit();
        //replaceFragment(new LiveFragment());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Log.d(TAG, "onListFragmentInteraction" + item.id);
        switch (Integer.parseInt(item.id)) {
            case LIVE_TV:
                if (null == liveFragment) {
                    liveFragment = new LiveFragment();
                }
                replaceFragment(liveFragment, R.id.base_right_layout);
                break;
            case DETAIL:
                if (null == detailFragment) {
                    detailFragment = new DetailFragment();
                }
                replaceFragment(detailFragment, R.id.base_right_layout);
                break;
            case PLAYER:
                if (null == playerFragment) {
                    playerFragment = new PlayerFragment();
                }
                replaceFragment(playerFragment, R.id.base_right_layout);
                break;
            case 4:

                replaceFragment(new OtherBaseFragment(), R.id.home_fragment_container, true);
                break;
            case 5:
                FragmentManager fm = getFragmentManager();

                for (int entry = 0; entry < fm.getBackStackEntryCount(); entry++) {
                    Log.i(TAG, "Found fragment: " + fm.getBackStackEntryAt(entry).getName());
                }
                break;
        }
    }

    private void replaceFragment(Fragment fragment, int id, boolean addToBackstack) {
        String backStateName = fragment.getClass().getName();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        Fragment fragmentPopped = manager.findFragmentByTag(backStateName);
        Log.d(TAG, "fragmentPopped:" + fragmentPopped);
        Log.d(TAG, "Creating " + backStateName);
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(id, fragment, backStateName);
        if (addToBackstack) {
            ft.addToBackStack(backStateName);
        }
        ft.commit();
    }

    private void replaceFragment(Fragment fragment, int id) {
        replaceFragment(fragment, id, false);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
