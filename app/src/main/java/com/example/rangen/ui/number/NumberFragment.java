package com.example.rangen.ui.number;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rangen.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class NumberFragment extends Fragment {

    String TAG = "NumberViewModel";

    private NumberViewModel mViewModel;

    Button generateNumBtn;
    TextView newNumTv, history1Tv, history2Tv;
    EditText rangeMinEt, rangeMaxEt;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.number_fragment, container, false);

        mViewModel = ViewModelProviders.of(this).get(NumberViewModel.class);

        generateNumBtn = root.findViewById(R.id.btn_gen_num);
        newNumTv = root.findViewById(R.id.new_number);
        history1Tv = root.findViewById(R.id.number_history_1);
        history2Tv = root.findViewById(R.id.number_history_2);
        rangeMinEt = root.findViewById(R.id.range_min_et);
        rangeMaxEt = root.findViewById(R.id.range_max_et);

        generateNumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String minStr = rangeMinEt.getText().toString();
                String maxStr = rangeMaxEt.getText().toString();

                mViewModel.min = Integer.parseInt(minStr);
                mViewModel.max = Integer.parseInt(maxStr);

                try {
                    mViewModel.generateRandomNumberInRange();
                    newNumTv.setText(mViewModel.rand);
                } catch (IllegalArgumentException e) {
                    Snackbar snackbar = Snackbar.make(root, "Min greater than or equal to Max", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                history1Tv.setText(mViewModel.history1);
                history2Tv.setText(mViewModel.history2);
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        newNumTv.setText(mViewModel.rand);
        history1Tv.setText(mViewModel.history1);
        history2Tv.setText(mViewModel.history2);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NumberViewModel.class);
        // TODO: Use the ViewModel
    }
}
