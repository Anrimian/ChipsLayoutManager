package com.beloo.chipslayoutmanager.sample.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.beloo.chipslayoutmanager.sample.R;
import com.beloo.chipslayoutmanager.sample.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setTitle(getString(R.string.app_name_and_version, "BuildConfig.VERSION_NAME"));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, ItemsFragment.newInstance())
                    .commit();
        }

        setSupportActionBar(binding.toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

/*        drawer = new DrawerBuilder(this)
                .withToolbar(toolbar)
                .addDrawerItems(new PrimaryDrawerItem().withName(R.string.main).withIdentifier(1))
                .addDrawerItems(new PrimaryDrawerItem().withName(R.string.bottom_sheet).withIdentifier(2))
                .withOnDrawerItemClickListener(this::onDrawerItemClickListener)
                .build();*/
    }

/*    private boolean onDrawerItemClickListener(View view, int position, IDrawerItem drawerItem) {
        int id = (int) drawerItem.getIdentifier();
        switch (id) {
            case 1:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, ItemsFragment.newInstance())
                        .commit();
                drawer.closeDrawer();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, BottomSheetFragment.newInstance())
                        .commit();
                drawer.closeDrawer();
                break;
        }
        return true;
    }*/

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }
}
