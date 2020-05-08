package com.test.pandemic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class NewCustDas extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewpager;
    private TabLayout tablayout;

    private detailfrag profilefragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cust_das);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewpager=findViewById(R.id.view_pager);
        tablayout=findViewById(R.id.tab_layout);

        profilefragment=new detailfrag();

        tablayout.setupWithViewPager(viewpager);

        ViewPagerAdapter viewpageradapter=new ViewPagerAdapter(getSupportFragmentManager(),0);
        viewpageradapter.addFragment(profilefragment,"Details");
//        viewpageradapter.addFragment(profilefragment,"Details");
//        viewpageradapter.addFragment(profilefragment,"Details");
//        viewpageradapter.addFragment(profilefragment,"Profile");
        viewpager.setAdapter(viewpageradapter);

        tablayout.getTabAt(0).setIcon(R.drawable.ic_description_black_24dp);

//        tablayout.getTabAt(1).setIcon(R.drawable.ic_description_black_24dp);
//        tablayout.getTabAt(2).setIcon(R.drawable.ic_inbox_black_24dp);
//        tablayout.getTabAt(3).setIcon(R.drawable.ic_account_circle_black_24dp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.Logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.newprof:
                startActivity(new Intent(this,profile.class));
                finish();
        }
        return true;
    }

    private class ViewPagerAdapter  extends FragmentPagerAdapter {
        private List<Fragment> fragments=new ArrayList<>();
        private List<String> fragmentstitle=new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment,String title){
            fragments.add(fragment);
            fragmentstitle.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentstitle.get(position);
        }
    }
}
