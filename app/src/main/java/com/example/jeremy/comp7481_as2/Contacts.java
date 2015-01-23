package com.example.jeremy.comp7481_as2;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class Contacts extends ListActivity {
    private CommentsDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        datasource = new CommentsDataSource(this);
        datasource.open();

        final List<Comment> values = datasource.getAllComments();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        /*ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
                android.R.layout.simple_list_item_1, values);*/

        ArrayAdapter<Comment> adapter = new ArrayAdapter (this, android.R.layout.simple_list_item_2, android.R.id.text1, values)
        {
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                text1.setText(values.get(position).getContactName());
                text2.setText(values.get(position).getContactPhone() + " - " + values.get(position).getContactAddress());
                return view;
            }
        };

        setListAdapter(adapter);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
        Comment comment = null;
        switch (view.getId()) {
            case R.id.add:
                /*String[] names = new String[] { "Cool", "Very nice", "Hate it" };
                String phone = new String("123456789");
                String address = new String("123 Android Street");
                int nextInt = new Random().nextInt(3);*/

                EditText editTextName = (EditText) findViewById(R.id.name);
                String name = editTextName.getText().toString();

                EditText editTextPhone = (EditText) findViewById(R.id.phone);
                String phone = editTextPhone.getText().toString();

                EditText editTextAddress = (EditText) findViewById(R.id.address);
                String address = editTextAddress.getText().toString();

                // save the new comment to the database
                comment = datasource.createComment(name, phone, address);
                adapter.add(comment);

                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    comment = (Comment) getListAdapter().getItem(0);
                    datasource.deleteComment(comment);
                    adapter.remove(comment);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}
