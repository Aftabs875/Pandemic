package com.test.pandemic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashcustomer extends AppCompatActivity implements View.OnClickListener {
    private CardView info,profileI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashcustomer);
        info=(CardView)findViewById(R.id.infoID);
        profileI=(CardView)findViewById(R.id.profileID);
        //Add clock listener
        info.setOnClickListener(this);
        profileI.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch(v.getId()){
            case R.id.infoID:i=new Intent(this,NewCustDas.class);
                startActivity(i);
                break;
            case R.id.profileID:i=new Intent(this,profile.class);
                startActivity(i);
                break;
        }
    }
}