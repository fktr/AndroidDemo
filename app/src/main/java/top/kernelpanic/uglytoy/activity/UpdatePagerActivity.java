package top.kernelpanic.uglytoy.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;

import top.kernelpanic.uglytoy.R;
import top.kernelpanic.uglytoy.entity.Update;
import top.kernelpanic.uglytoy.entity.UpdateList;
import top.kernelpanic.uglytoy.fragment.UpdateFragment;

/**
 * Created by fu on 16-12-8.
 */

public class UpdatePagerActivity extends FragmentActivity {

    private ArrayList<Update>mUpdates;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager=new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        FragmentManager fm=getSupportFragmentManager();
        mUpdates= UpdateList.get(this).getmUpdates();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Update update=mUpdates.get(position);
                return UpdateFragment.newInstance(update.getmId());
            }

            @Override
            public int getCount() {
                return mUpdates.size();
            }
        });
        UUID updateId=(UUID)getIntent().getSerializableExtra(UpdateFragment.EXTRA_UPDATE_ID);
        for(int i=0;i<mUpdates.size();i++){
            if(mUpdates.get(i).getmId().equals(updateId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
