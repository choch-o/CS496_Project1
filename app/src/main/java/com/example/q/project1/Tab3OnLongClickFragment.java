package com.example.q.project1;

import android.app.Activity;
import android.app.Dialog;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import static android.content.DialogInterface.BUTTON_POSITIVE;

/**
 * Created by q on 2016-12-27.
 */

public class Tab3OnLongClickFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Are you sure to delete '" + getArguments().getString("name") + "'?")
                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle params = getArguments();
                        File fdelete = new File(params.getString("file_path"));
                        if (fdelete.exists()) {
                            System.out.println(fdelete.delete());
                        }
                        int pos = params.getInt("position");

                        Uri image_uri = Uri.parse("file://" + params.getString("file_path"));

                        MainActivity calling_activity = (MainActivity) getActivity() ;
                        calling_activity.onTab3ItemDelete(params.getInt("position"));
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
