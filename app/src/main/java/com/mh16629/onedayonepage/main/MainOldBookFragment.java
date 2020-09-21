package com.mh16629.onedayonepage.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mh16629.onedayonepage.R;
import com.mh16629.onedayonepage.bookdetail.BookDetailActivity;

/**
 * Use the {@link MainOldBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainOldBookFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "MainOldBookFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageButton coverButton;

    public MainOldBookFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach");
        super.onAttach(context);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainOldBookFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainOldBookFragment newInstance(String param1, String param2) {
        Log.d(TAG, "생성자");
        MainOldBookFragment fragment = new MainOldBookFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView");

        // 북커버 클릭 이벤트
//        coverButton = (ImageButton) getView().findViewById(R.id.button_main_old_book_bookcover);
        View v = inflater.inflate(R.layout.fragment_main_old_book, container, false);

        coverButton = (ImageButton) v.findViewById(R.id.button_main_old_book_bookcover);
        coverButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d(TAG, "onCreateView - onclickListener");
                // 도서 상세 화면(BookDetailActivity)으로 전이
                Intent intent = new Intent(getActivity(), BookDetailActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}