package com.example.q.project1;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

// Instances of this class are fragments representing a single
// object in our collection.
public class Tab1Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab1, container, false);
        ArrayAdapter<String> adapter;
        final JSONArray contact_list;
        ArrayList<String> str_contact_list;

        contact_list = getContactList();
        str_contact_list = parse_JSONArray(contact_list);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, str_contact_list);

        ListView list_view = (ListView) view.findViewById(R.id.tab1);
        list_view.setAdapter(adapter);
        list_view.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tab1OnClickFragment dialog_fragment = new Tab1OnClickFragment();
                Bundle args = new Bundle();
                try {
                    JSONObject item = contact_list.getJSONObject(position);
                    args.putString("name", item.getString("name"));
                    System.out.println("name : " + item.getString("name"));
                    args.putString("number", item.getString("number"));
                    System.out.println("number : " + item.getString("number"));
                    dialog_fragment.setArguments(args);
                    dialog_fragment.show(getActivity().getSupportFragmentManager(), "name and phone number");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    private JSONArray getContactList() {
        JSONArray contact_list = new JSONArray();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[] {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };
        String sort_order = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;

        Cursor cursor_contacts = getActivity().getContentResolver().query(uri, projection, null, null, sort_order);

        if (cursor_contacts.getCount() == 0) {
            cursor_contacts.close();
            return null;
        } else {
            try {
                cursor_contacts.moveToFirst();
                do {
                    JSONObject jobject = new JSONObject();
                    String name_str = cursor_contacts.getString(cursor_contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phone_num_str = cursor_contacts.getString(cursor_contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    jobject.put("name", name_str);
                    jobject.put("number", phone_num_str);
                    contact_list.put(jobject);
                } while (cursor_contacts.moveToNext());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return contact_list;
    }

    public ArrayList<String> parse_JSONArray(JSONArray jarray) {
        ArrayList<String> str_list = new ArrayList<String>();

        for (int i = 0; i < jarray.length(); i++) {
            try {
                JSONObject jobject = jarray.getJSONObject(i);
                String str = jobject.getString("name");
                str_list.add(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return str_list;
    }

    public class Contact {
        String phonenum;
        String name;

        public Contact() {}

        public Contact(String phonenum, String name) {
            this.phonenum = phonenum;
            this.name = name;
        }

        public String getPhonenum() {
            return phonenum;
        }
        public void setPhonenum(String phonenum) {
            this.phonenum = phonenum;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
