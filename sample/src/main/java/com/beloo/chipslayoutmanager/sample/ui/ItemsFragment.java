package com.beloo.chipslayoutmanager.sample.ui;


import android.os.Bundle;
import androidx.annotation.RestrictTo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.beloo.chipslayoutmanager.sample.R;
import com.beloo.chipslayoutmanager.sample.databinding.FragmentItemsBinding;
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 */
public class ItemsFragment extends Fragment {

    private static final String EXTRA = "data";

    private FragmentItemsBinding binding;

    private RecyclerView.Adapter adapter;
    private List<String> positions;
    private List items;

    /** replace here different data sets */
    private IItemsFactory itemsFactory = new ShortChipsFactory();


    @RestrictTo(RestrictTo.Scope.SUBCLASSES)
    public ItemsFragment() {
        // Required empty public constructor
    }

    public static ItemsFragment newInstance() {
        return new ItemsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentItemsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @SuppressWarnings("unchecked")
    private RecyclerView.Adapter createAdapter(Bundle savedInstanceState) {

        List<String> items;
        if (savedInstanceState == null) {
//            items = itemsFactory.getFewItems();
//            items = itemsFactory.getALotOfItems();
            items = itemsFactory.getItems();
        } else {
            items = savedInstanceState.getStringArrayList(EXTRA);
        }

        adapter = itemsFactory.createAdapter(items, onRemoveListener);
        this.items = items;

        return adapter;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = createAdapter(savedInstanceState);

        ChipsLayoutManager spanLayoutManager = ChipsLayoutManager.newBuilder(getContext())
                .setOrientation(ChipsLayoutManager.HORIZONTAL)
                .build();

        binding.rvTest.addItemDecoration(new SpacingItemDecoration(getResources().getDimensionPixelOffset(R.dimen.item_space),
                getResources().getDimensionPixelOffset(R.dimen.item_space)));

        positions = new LinkedList<>();
        for (int i = 0; i< items.size(); i++) {
            positions.add(String.valueOf(i));
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, positions);
        ArrayAdapter<String> spinnerAdapterMoveTo = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, positions);
        binding.spinnerPosition.setAdapter(spinnerAdapter);
        binding.spinnerMoveTo.setAdapter(spinnerAdapterMoveTo);

        binding.rvTest.setLayoutManager(spanLayoutManager);
        binding.rvTest.getRecycledViewPool().setMaxRecycledViews(0, 10);
        binding.rvTest.getRecycledViewPool().setMaxRecycledViews(1, 10);
        binding.rvTest.setAdapter(adapter);

        binding.btnRevert.setOnClickListener(this::onRevertClicked);
        binding.btnDelete.setOnClickListener(this::onDeleteClicked);
        binding.btnMove.setOnClickListener(this::onMoveClicked);
        binding.btnScroll.setOnClickListener(this::onScrollClicked);
        binding.btnInsert.setOnClickListener(this::onInsertClicked);

    }

    private OnRemoveListener onRemoveListener = new OnRemoveListener() {
        @Override
        public void onItemRemoved(int position) {
            items.remove(position);
            Log.i("activity", "delete at " + position);
            adapter.notifyItemRemoved(position);
            updateSpinners();
        }
    };

    @Override
    @SuppressWarnings("unchecked")
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA, new ArrayList<>(items));
    }

    private void updateSpinners() {
        positions = new LinkedList<>();
        for (int i = 0; i< items.size(); i++) {
            positions.add(String.valueOf(i));
        }

        int selectedPosition = Math.min(binding.spinnerPosition.getSelectedItemPosition(), positions.size() - 1);
        int selectedMoveToPosition = Math.min(binding.spinnerMoveTo.getSelectedItemPosition(), positions.size() -1);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, positions);
        binding.spinnerPosition.setAdapter(spinnerAdapter);
        selectedPosition = Math.min(spinnerAdapter.getCount() -1 , selectedPosition);
        binding.spinnerPosition.setSelection(selectedPosition);

        ArrayAdapter<String> spinnerAdapterMoveTo = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, positions);
        binding.spinnerMoveTo.setAdapter(spinnerAdapterMoveTo);
        binding.spinnerMoveTo.setSelection(selectedMoveToPosition);
    }

    public void onRevertClicked(View view) {
        int position = binding.spinnerPosition.getSelectedItemPosition();
        if (position == Spinner.INVALID_POSITION)
            return;

        int positionMoveTo = binding.spinnerMoveTo.getSelectedItemPosition();
        if (positionMoveTo == Spinner.INVALID_POSITION)
            return;

        if (position == positionMoveTo) return;

        binding.spinnerPosition.setSelection(positionMoveTo);
        binding.spinnerMoveTo.setSelection(position);
    }

    public void onDeleteClicked(View view) {
        int position = binding.spinnerPosition.getSelectedItemPosition();
        if (position == Spinner.INVALID_POSITION)
            return;
        items.remove(position);
        Log.i("activity", "delete at " + position);
        adapter.notifyItemRemoved(position);
        updateSpinners();
    }

    public void onMoveClicked(View view) {
        int position = binding.spinnerPosition.getSelectedItemPosition();
        if (position == Spinner.INVALID_POSITION)
            return;

        int positionMoveTo = binding.spinnerMoveTo.getSelectedItemPosition();
        if (positionMoveTo == Spinner.INVALID_POSITION)
            return;

        if (position == positionMoveTo) return;

        Object item = items.remove(position);
        items.add(positionMoveTo, item);

        adapter.notifyItemMoved(position, positionMoveTo);
    }

    public void onScrollClicked(View view) {
//        rvTest.scrollBy(0, 500);
        binding.rvTest.scrollToPosition(binding.spinnerPosition.getSelectedItemPosition());
    }

    public void onInsertClicked(View view) {
        int position = binding.spinnerPosition.getSelectedItemPosition();
        if (position == Spinner.INVALID_POSITION)
            position = 0;
        items.add(position, itemsFactory.createOneItemForPosition(position));
        Log.i("activity", "insert at " + position);
        adapter.notifyItemInserted(position);
        updateSpinners();
    }

}
