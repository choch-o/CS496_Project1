package com.example.q.project1;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.app.TabActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_number_layout);
        /*
        ArrayList<Contact> contact_list = new ArrayList<Contact>();
        contact_list = getContactList();
        ArrayList<String> general = new ArrayList<String>();
        for (int i = 0; i < contact_list.size(); i++) {
            general.add(contact_list.get(i).name);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, general);

        ListView list_view = (ListView) findViewById(R.id.phone_number_list);
        list_view.setAdapter(adapter);
        list_view.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        */
        ArrayList<String> arrayList;
        ArrayAdapter<String> adapter;

        arrayList = new ArrayList<String>();
        arrayList.add("Hello");
        arrayList.add("Hi");
        arrayList.add("A-yo!");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        ListView list_view = (ListView) findViewById(R.id.phone_number_list);
        list_view.setAdapter(adapter);
        list_view.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    private ArrayList<Contact> getContactList() {
        ArrayList<Contact> contact_list = new ArrayList<Contact>();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[] {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };
        String sort_order = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;

        Cursor cursor_contacts = getContentResolver().query(uri, projection, null, null, sort_order);

        if (cursor_contacts.getCount() == 0) {
            cursor_contacts.close();
            return null;
        } else {
            cursor_contacts.moveToFirst();
            do {
                String name_str = cursor_contacts.getString(cursor_contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phone_num_str = cursor_contacts.getString(cursor_contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Contact contact = new Contact();
                contact.setName(name_str);
                contact.setPhonenum(phone_num_str);
                contact_list.add(contact);
            } while (cursor_contacts.moveToNext());
        }

        return contact_list;
    }

    public class Contact {
        String phonenum;
        String name;

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
