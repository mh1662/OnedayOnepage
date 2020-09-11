package com.mh16629.onedayonepage.bookdetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mh16629.onedayonepage.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookDetailContentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookDetailContentsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // 발췌리스트
    private RecyclerView excerptList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public BookDetailContentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookDetailContentsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookDetailContentsFragment newInstance(String param1, String param2) {
        BookDetailContentsFragment fragment = new BookDetailContentsFragment();
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

//        // 발췌 recyclerView
//        excerptList = (RecyclerView) getView().findViewById(R.id.book_detail_excerptList);
//        excerptList.setHasFixedSize(true);
//
//        layoutManager = new LinearLayoutManager(this);
//        excerptList.setLayoutManager(layoutManager);
//
//        mAdapter = new MyAdapte(myDataset);
//        excerptList.setAdapter(mAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail_contents, container, false);
    }
}