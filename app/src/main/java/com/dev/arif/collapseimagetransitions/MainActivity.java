package com.dev.arif.collapseimagetransitions;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.app_bar) AppBarLayout app_bar;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.sliding_tabs) TabLayout tabLayout;
    @BindView(R.id.username) TextView _username;
    @BindView(R.id.name) TextView _name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    _name.setTextColor(Color.parseColor("#FFFFFF"));
                    _username.setTextColor(Color.parseColor("#FFFFFF"));
                    toolbar.setBackgroundResource(R.color.blur);
                }
                else if(verticalOffset<-650) {
                    _name.setTextColor(Color.parseColor("#c6c6c6"));
                    _username.setTextColor(Color.parseColor("#c6c6c6"));
                    toolbar.setBackgroundResource(R.color.blur3);
                }
                else if (verticalOffset <-450 && verticalOffset>-650) {
                    _name.setTextColor(Color.parseColor("#c6c6c6"));
                    _username.setTextColor(Color.parseColor("#c6c6c6"));
                    toolbar.setBackgroundResource(R.color.blur4);
                }
                else if (verticalOffset <-300 && verticalOffset>-450) {
                    _name.setTextColor(Color.parseColor("#c6c6c6"));
                    _username.setTextColor(Color.parseColor("#c6c6c6"));
                    toolbar.setBackgroundResource(R.color.blur2);
                }
                else if (verticalOffset < 0 && verticalOffset>-300) {
                    _name.setTextColor(Color.parseColor("#88232323"));
                    _username.setTextColor(Color.parseColor("#88232323"));
                    toolbar.setBackgroundResource(R.color.blur0);
                }
                else if (verticalOffset == 0) {
                    _name.setTextColor(Color.parseColor("#000000"));
                    _username.setTextColor(Color.parseColor("#000000"));
                    toolbar.setBackgroundResource(R.color.blur0);
                } else {
                    _name.setTextColor(Color.parseColor("#c6c6c6"));
                    _username.setTextColor(Color.parseColor("#c6c6c6"));
                    toolbar.setBackgroundResource(R.color.blur2);
                }
            }
        });


    }
}
