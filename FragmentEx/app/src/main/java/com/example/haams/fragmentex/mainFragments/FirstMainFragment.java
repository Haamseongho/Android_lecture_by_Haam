package com.example.haams.fragmentex.mainFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haams.fragmentex.R;
import com.example.haams.fragmentex.subFragments.FirstSubFragment;
import com.example.haams.fragmentex.subFragments.SecondSubFragment;
import com.example.haams.fragmentex.subFragments.ThirdSubFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstMainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstMainFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ViewPager subPager;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FirstMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstMainFragment newInstance(String param1, String param2) {
        FirstMainFragment fragment = new FirstMainFragment();
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
        return inflater.inflate(R.layout.fragment_first_main, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    /*
    onViewCreated --> 뷰에 대한 내용 (onCreateView) 가 모두 완성될 경우 시작!! 그래서 인플레이터에서의 뷰로
    작업할 필요 없이 여기서의 view로 작업하면 된다.
     */

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
    }

    private void initViews(View view) {
        view.findViewById(R.id.MainToSub1).setOnClickListener(this);
        view.findViewById(R.id.MainToSub2).setOnClickListener(this);
        view.findViewById(R.id.MainToSub3).setOnClickListener(this);
        view.findViewById(R.id.remove_sub_button).setOnClickListener(this);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        FragmentManager childFragmentManager = getChildFragmentManager();

        switch (v.getId()) {
            case R.id.MainToSub1:
                childFragmentManager.beginTransaction()
                        .replace(R.id.subFrame, FirstSubFragment.newInstance("sub1", "fragment"))
                        .addToBackStack(null).commit();
                break;

            case R.id.MainToSub2:
                childFragmentManager.beginTransaction()
                        .replace(R.id.subFrame, SecondSubFragment.newInstance("sub2", "fragment"))
                        .addToBackStack(null).commit();
                break;

            case R.id.MainToSub3:
                childFragmentManager.beginTransaction()
                        .replace(R.id.subFrame, ThirdSubFragment.newInstance("sub3", "fragment"))
                        .addToBackStack(null).commit();
                break;

            case R.id.remove_sub_button:
                childFragmentManager.popBackStack();
        }
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
