package com.beloo.chipslayoutmanager.sample.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.beloo.chipslayoutmanager.sample.databinding.FragmentBottomSheetBinding;

/**
 */
public class BottomSheetFragment extends Fragment {

    public BottomSheetFragment() {
        // Required empty public constructor
    }

    public static BottomSheetFragment newInstance() {
        Bundle args = new Bundle();
        BottomSheetFragment fragment = new BottomSheetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private FragmentBottomSheetBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnShowSheet.setOnClickListener(this::onShowSheetClicked);
    }

    void onShowSheetClicked(View view) {
        BottomSheetDialogFragment fragment = BottomSheetDialogFragment.newInstance();
        fragment.show(getChildFragmentManager(), fragment.getTag());
    }


}
