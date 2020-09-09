package com.creative.automc.ui.accountinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.creative.automc.R;
import com.creative.automc.utils.PrefManager;

public class AccountInfoFragment extends Fragment {

    private AccountInfoViewModel accountInfoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        accountInfoViewModel =
                ViewModelProviders.of(this).get(AccountInfoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account_info, container, false);

        TextView full_name = root.findViewById(R.id.full_name);
        full_name.setText(PrefManager.getUserFullName(getActivity()));
        TextView email = root.findViewById(R.id.email);
        email.setText(PrefManager.getUserEmail(getActivity()));

        TextView location = root.findViewById(R.id.location);
        location.setText(PrefManager.getUserLocation(getActivity()));


        return root;
    }
}