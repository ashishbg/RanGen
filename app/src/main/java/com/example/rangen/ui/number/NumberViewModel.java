package com.example.rangen.ui.number;



import androidx.lifecycle.ViewModel;

import java.util.Random;

public class NumberViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    //Comment to check commit 3
    public int min, max;
    String rand = "", history1 = "", history2 = "";


    public void generateRandomNumberInRange() {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        history2 = history1;
        history1 = String.valueOf(rand);
        rand = String.valueOf(r.nextInt((max - min) + 1) + min);
    }

}