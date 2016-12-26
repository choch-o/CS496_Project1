package com.example.q.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.GridLayoutAnimationController;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

// Instances of this class are fragments representing a single
// object in our collection.
public class Tab2Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(getActivity()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                /* Intent i = new Intent(getActivity(), );
                i.putExtra("position", position);
                getActivity().startActivity(i); */
            }
        });
        return rootView;
    }
}