package com.example.whatsappclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class FindUserActivity extends AppCompatActivity {
    RecyclerView recUserList;
    FindUserAdapter adpater;
    ArrayList<UserObject> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);
        intializeRecylerUserList();
        getUsersList();
    }

    private void intializeRecylerUserList() {
        recUserList=findViewById(R.id.recUserList);
        recUserList.setNestedScrollingEnabled(false);
        recUserList.setHasFixedSize(false);
        recUserList.setLayoutManager(new LinearLayoutManager(this));
        users=new ArrayList<>();
                //getUsersList();

        adpater= new FindUserAdapter(this);


        adpater.setUsers(users);
        recUserList.setAdapter(adpater);
    }

    private void getUsersList() {
        //ArrayList<UserObject> users= new ArrayList<>();

        Cursor phones= getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        while(phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            UserObject uo=new UserObject(name,phone);
            users.add(uo);
            adpater.notifyDataSetChanged();
        }



    }
}