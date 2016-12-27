package com.example.q.project1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

// Instances of this class are fragments representing a single
// object in our collection.
public class Tab3Fragment extends Fragment {
    private ArrayList<Tab3Item> images;
    private GridAdapter grid_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.tab3, container, false);
        FloatingActionButton addButton =
                (FloatingActionButton) rootView.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), DrawActivity.class);
                getActivity().startActivity(i);
            }
        });

        GridView grid_view = (GridView) rootView.findViewById(R.id.gridview3);

        images = fetchAllImages();
        grid_adapter = new GridAdapter(getActivity());
        grid_view.setAdapter(grid_adapter);

        grid_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Tab3OnLongClickFragment dialog_fragment = new Tab3OnLongClickFragment();
                Bundle args = new Bundle();
                args.putString("file_path", images.get(position).getFilePath());
                args.putSerializable("images", images);
                args.putInt("position", position);

                dialog_fragment.setArguments(args);
                dialog_fragment.show(getActivity().getSupportFragmentManager(), "Deletion Alert");



                return true;
            }
        });

        return rootView;
    }

    private ArrayList<Tab3Item> fetchAllImages() {
        String[] projection = {
                MediaStore.Images.Media.DATA
        };
        Cursor cursor_image = getContext().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null, null, null
        );

        ArrayList<Tab3Item> result = new ArrayList<>(cursor_image.getCount());

        try {
            if (cursor_image != null) {
                if (cursor_image.moveToFirst()) {
                    do {
                        String file_path = cursor_image.getString(cursor_image.getColumnIndex(projection[0]));
                        Uri image_uri = Uri.parse("file://" + file_path);
                        Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), image_uri);
                        result.add(new Tab3Item(file_path, image_uri, image_bitmap));
                    } while (cursor_image.moveToNext());
                }
            }
            cursor_image.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public void deleteItem(int position) {
        images.remove(position);
        grid_adapter.notifyDataSetChanged();
    }

    public class GridAdapter extends BaseAdapter {
        private Context m_context;

        public GridAdapter(Context context) { m_context = context; }

        @Override
        public int getCount() { return images.size(); }

        @Override
        public Object getItem(int position) { return images.get(position); }

        @Override
        public long getItemId(int position) { return position; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView image_view;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                image_view = new ImageView(m_context);
                image_view.setLayoutParams(new GridView.LayoutParams(350, 350));
                image_view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                image_view = (ImageView) convertView;
            }

            image_view.setImageBitmap(images.get(position).getImageBitmap());

            return image_view;
        }
    }
}