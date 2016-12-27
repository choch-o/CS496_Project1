package com.example.q.project1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


// Instances of this class are fragments representing a single
// object in our collection.
public class Tab3Fragment extends Fragment {
    private ArrayList<Tab3Item> items;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.tab3, container, false);
        GridView grid_view = (GridView) view.findViewById(R.id.gridview3);
        items = new ArrayList<>();

        try {
            FileInputStream image_list_file = new FileInputStream("image_list.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(image_list_file));
            String image_name = reader.readLine();
            while (image_name != null) {
                String image_file_route = getActivity().getFilesDir().getAbsolutePath() + image_name + ".jpg";
                items.add(new Tab3Item(image_name, image_file_route));
                image_name = reader.readLine();
            }
            System.out.println("check!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        grid_view.setAdapter(new GridAdapter(getActivity()));

        return view;
    }

    public class GridAdapter extends BaseAdapter {
        private Context m_context;

        public GridAdapter(Context context) { m_context = context; }

        @Override
        public int getCount() { return items.size(); }

        @Override
        public Object getItem(int position) { return items.get(position); }

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

            Bitmap bitmap = BitmapFactory.decodeFile(items.get(position).getImageRoute());
            image_view.setImageBitmap(bitmap);

            return image_view;
        }
    }
}