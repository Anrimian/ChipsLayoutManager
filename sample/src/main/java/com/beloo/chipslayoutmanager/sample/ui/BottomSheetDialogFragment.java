package com.beloo.chipslayoutmanager.sample.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.beloo.chipslayoutmanager.sample.R;
import com.beloo.chipslayoutmanager.sample.databinding.FragmentBottomSheetModalBinding;
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration;

import java.util.List;

public class BottomSheetDialogFragment extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {

    private FragmentBottomSheetModalBinding binding;

    private IItemsFactory itemsFactory = new ChipsFactory();
    private RecyclerView.Adapter adapter;

    public static BottomSheetDialogFragment newInstance() {
        Bundle args = new Bundle();
        BottomSheetDialogFragment fragment = new BottomSheetDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBottomSheetModalBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressWarnings("unchecked")
    private RecyclerView.Adapter createAdapter() {

//        List<String> items = itemsFactory.getDoubleItems();
        List<String> items = itemsFactory.getItems();

        adapter = itemsFactory.createAdapter(items, null);
        return adapter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        ChipsLayoutManager layoutManager = ChipsLayoutManager.newBuilder(getContext()).build();
        binding.rvBottomSheet.setLayoutManager(layoutManager);
        binding.rvBottomSheet.setAdapter(createAdapter());
        binding.rvBottomSheet.addItemDecoration(new SpacingItemDecoration(getResources().getDimensionPixelOffset(R.dimen.item_space),
                getResources().getDimensionPixelOffset(R.dimen.item_space)));
    }
}
