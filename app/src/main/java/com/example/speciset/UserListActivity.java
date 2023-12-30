package com.example.speciset;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserListActivity extends ListActivity {
    private List<user> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBaseHelper base = new DataBaseHelper(this);
        userList = base.getAllusers();

        UserListAdapter adapter = new UserListAdapter(this, R.layout.user_list_item, userList);
        setListAdapter(adapter);
    }

    private static class UserListAdapter extends ArrayAdapter<user> {
        private final List<user> userList;
        private final int layoutResource;

        public UserListAdapter(Context context, int resource, List<user> userList) {
            super(context, resource, userList);
            this.userList = userList;
            this.layoutResource = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                view = inflater.inflate(layoutResource, null);
            }

            user currentUser = userList.get(position);

            if (currentUser != null) {
                TextView usernameTextView = view.findViewById(R.id.usernameTextView);
                TextView emailTextView = view.findViewById(R.id.emailTextView);

                if (usernameTextView != null) {
                    usernameTextView.setText(currentUser.getNom() + " " + currentUser.getPrenom());
                }

                if (emailTextView != null) {
                    emailTextView.setText(currentUser.getEmail());
                }
            }

            return view;
        }
    }
}
