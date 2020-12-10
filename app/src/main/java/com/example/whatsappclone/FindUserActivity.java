package com.example.whatsappclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class FindUserActivity extends AppCompatActivity {
    RecyclerView recUserList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);
        intializeRecylerUserList();

    }

    private void intializeRecylerUserList() {
        recUserList=findViewById(R.id.recUserList);
        recUserList.setNestedScrollingEnabled(false);
        recUserList.setHasFixedSize(false);
        recUserList.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<UserObject> users= new ArrayList<>();
        users.add(new UserObject("zain Iftikhar","03238492056"));
        users.add(new UserObject("zain Iftikhar","03238492056"));
        users.add(new UserObject("zain Iftikhar","03238492056"));
        users.add(new UserObject("zain Iftikhar","03238492056"));
        users.add(new UserObject("zain Iftikhar","03238492056"));
        users.add(new UserObject("zain Iftikhar","03238492056"));
        users.add(new UserObject("zain Iftikhar","03238492056"));
        users.add(new UserObject("zain Iftikhar","03238492056"));
        users.add(new UserObject("zain Iftikhar","03238492056"));
        users.add(new UserObject("zain Iftikhar","03238492056"));

        FindUserAdapter adpater= new FindUserAdapter(this);
        adpater.setUsers(users);

        recUserList.setAdapter(adpater);

    }
}