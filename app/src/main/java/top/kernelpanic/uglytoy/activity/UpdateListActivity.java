package top.kernelpanic.uglytoy.activity;

import android.support.v4.app.Fragment;

import top.kernelpanic.uglytoy.fragment.UpdateListFragment;

/**
 * Created by fu on 16-12-8.
 */

public class UpdateListActivity extends BaseFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return UpdateListFragment.newInstance();
    }
}
