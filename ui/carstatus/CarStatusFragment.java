package com.creative.automc.ui.carstatus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.creative.automc.R;
import com.creative.automc.activity.EditCarActivity;
import com.creative.automc.utils.PrefManager;

public class CarStatusFragment extends Fragment {

    private CarStatusViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(CarStatusViewModel.class);
        View root = inflater.inflate(R.layout.fragment_car_status, container, false);

        final TextView car_distance = root.findViewById(R.id.car_distance);
        final TextView last_maintenance_distance = root.findViewById(R.id.last_maintenance_distance);
        final TextView oil_life = root.findViewById(R.id.oil_life);
        final TextView tires_life = root.findViewById(R.id.tires_life);
        final TextView breaks_life = root.findViewById(R.id.breaks_life);
        final TextView air_conditioner_life = root.findViewById(R.id.air_conditioner_life);

        car_distance.setText(PrefManager.getCarDistance(getActivity()));
        last_maintenance_distance.setText(PrefManager.getCarLastMDistance(getActivity()));

        oil_life.setText(PrefManager.getCarOilLife(getActivity()));
        tires_life.setText(PrefManager.getCarTiresLife(getActivity()));
        breaks_life.setText(PrefManager.getCarBreaksLife(getActivity()));
        air_conditioner_life.setText(PrefManager.getCarAirconditionerLife(getActivity()));

        ImageButton car_distance_edit = root.findViewById(R.id.car_distance_edit);
        ImageButton m_distance_edit = root.findViewById(R.id.m_distance_edit);
        ImageButton oil_life_edit = root.findViewById(R.id.oil_life_edit);
        ImageButton tires_life_edit = root.findViewById(R.id.tires_life_edit);
        ImageButton breaks_life_edit = root.findViewById(R.id.breaks_life_edit);
        ImageButton air_conditioner_life_edit = root.findViewById(R.id.air_conditioner_life_edit);

        car_distance_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditCarActivity.start(getActivity(), car_distance.getText().toString(), "car_distance");
            }
        });


        m_distance_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditCarActivity.start(getActivity(), last_maintenance_distance.getText().toString(), "maintenance_distance");

            }
        });


        oil_life_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditCarActivity.start(getActivity(), oil_life.getText().toString(), "oil_life");

            }
        });


        tires_life_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditCarActivity.start(getActivity(), tires_life.getText().toString(), "tires_life");

            }
        });

        breaks_life_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditCarActivity.start(getActivity(), breaks_life.getText().toString(), "breaks_life");

            }
        });

        air_conditioner_life_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditCarActivity.start(getActivity(), air_conditioner_life.getText().toString(), "air_conditioner_life");

            }
        });


        return root;
    }
}