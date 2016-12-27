package com.example.q.project1;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by q on 2016-12-26.
 */

public class Tab1OnClickFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceStates) {
        View view = inflater.inflate(R.layout.tab1_onclick, container);
        TextView name = (TextView) view.findViewById(R.id.tab1_onclick_name);
        TextView number = (TextView) view.findViewById(R.id.tab1_onclick_number);
        Bundle params = getArguments();
        System.out.println("name : " + params.getString("name"));
        System.out.println("number : " + params.getString("number"));

        name.setText(params.getString("name"));
        number.setText(params.getString("number"));

        return view;
    }

}
