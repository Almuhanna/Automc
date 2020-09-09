package com.creative.automc.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.creative.automc.utils.PrefManager;
import com.creative.automc.R;


public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        // setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);


        TextView vin_number = view.findViewById(R.id.vin_number);
        TextView brand = view.findViewById(R.id.brand);
        TextView model = view.findViewById(R.id.model);
        TextView year = view.findViewById(R.id.year);
        TextView car_distance = view.findViewById(R.id.car_distance);
        TextView last_maintenance_distance = view.findViewById(R.id.last_maintenance_distance);
        TextView last_maintenance_date = view.findViewById(R.id.last_maintenance_date);

        vin_number.setText(PrefManager.getCarVinNumber(getActivity()));
        brand.setText(PrefManager.getCarBrand(getActivity()));
        model.setText(PrefManager.getCarModel(getActivity()));
        year.setText(PrefManager.getCarYear(getActivity()));

        car_distance.setText(PrefManager.getCarDistance(getActivity()));
        last_maintenance_distance.setText(PrefManager.getCarLastMDistance(getActivity()));
        last_maintenance_date.setText(PrefManager.getCarLastMDate(getActivity()));


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // restore RecyclerView state

        try {
            getActivity().setTitle(R.string.title_home);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
