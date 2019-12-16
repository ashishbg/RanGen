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

    Button generateNum;
    TextView newNum, history1, history2;
    EditText rangeMin, rangeMax;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.number_fragment, container, false);
        ;

        Log.d(TAG, "onCreateView: started");

        generateNum = root.findViewById(R.id.btn_gen_num);
        newNum = root.findViewById(R.id.new_number);
        history1 = root.findViewById(R.id.number_history_1);
        history2 = root.findViewById(R.id.number_history_2);

        rangeMin = root.findViewById(R.id.range_min_et);
        rangeMax = root.findViewById(R.id.range_max_et);

        generateNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int min, max, rand;

                String minStr = rangeMin.getText().toString();
                String maxStr = rangeMax.getText().toString();

                min = Integer.parseInt(minStr);
                max = Integer.parseInt(maxStr);

                history2.setText(history1.getText().toString());
                history1.setText(newNum.getText().toString());
                try {
                    rand = getRandomNumberInRange(min, max);
                    newNum.setText(String.valueOf(rand));
                } catch (IllegalArgumentException e) {
                    Snackbar snackbar = Snackbar.make(root, "Min greater than Max", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NumberViewModel.class);
        // TODO: Use the ViewModel
    }

    private int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            Log.d("", "getRandomNumberInRange: max < min");
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        int rand = r.nextInt((max - min) + 1) + min;
        return rand;
    }

}
