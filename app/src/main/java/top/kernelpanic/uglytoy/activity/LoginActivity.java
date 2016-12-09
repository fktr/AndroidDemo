package top.kernelpanic.uglytoy.activity;

import android.support.v4.app.Fragment;

import top.kernelpanic.uglytoy.fragment.LoginFragment;

/**
 * Created by fu on 16-12-8.
 */

public class LoginActivity extends BaseFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return LoginFragment.newInstance();
    }
}
