package top.kernelpanic.uglytoy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import top.kernelpanic.uglytoy.R;
import top.kernelpanic.uglytoy.activity.RegisterActivity;
import top.kernelpanic.uglytoy.activity.UpdateListActivity;
import top.kernelpanic.uglytoy.entity.Account;
import top.kernelpanic.uglytoy.entity.AllAccounts;

/**
 * Created by fu on 16-12-8.
 */

public class LoginFragment extends Fragment {

    private static final String LOGIN_ERROR_TAG="LoginErrorTag";
    private static final String LOGIN_SUCCESS_TAG ="LoginSuccessTag";
    private static final int REQUEST_JMP_HOME=0;
    private TextView mGoRegisterTextView;
    private Account mAccount;
    private EditText mUserNameEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.login,container,false);
        mGoRegisterTextView=(TextView)v.findViewById(R.id.go_register);
        mUserNameEditText=(EditText) v.findViewById(R.id.login_username);
        mPasswordEditText=(EditText)v.findViewById(R.id.login_password);
        mLoginButton=(Button)v.findViewById(R.id.login_button);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageDialogFragment msgDialog;
                FragmentManager fm=getActivity().getSupportFragmentManager();
                String strUsername=mUserNameEditText.getText().toString();
                String strPassword=mPasswordEditText.getText().toString();
                if(strUsername.equals("")||strPassword.equals("")){
                    msgDialog=MessageDialogFragment.newInstance("信息不足","请填写完整的用户信息","重新登录");
                    msgDialog.show(fm,LOGIN_ERROR_TAG);
                    return ;
                }
                mAccount= AllAccounts.get(getActivity()).getAccountByName(strUsername);
                if(mAccount==null)
                    mAccount=AllAccounts.get(getActivity()).getAccountByEmail(strUsername);
                if(mAccount==null){
                    msgDialog=MessageDialogFragment.newInstance("非法登录","用户不存在,请先注册","确定");
                    msgDialog.show(fm,LOGIN_ERROR_TAG);
                    return ;
                }
                if(!mAccount.getmPassword().equals(strPassword)){
                    msgDialog=MessageDialogFragment.newInstance("密码错误","请仔细核对密码","重新登录");
                    msgDialog.show(fm,LOGIN_ERROR_TAG);
                    return ;
                }
                msgDialog=MessageDialogFragment.newInstance("登陆成功","快来看看你的订阅吧","即刻查看");
                msgDialog.setTargetFragment(LoginFragment.this,REQUEST_JMP_HOME);
                msgDialog.show(fm, LOGIN_SUCCESS_TAG);
            }
        });
        mGoRegisterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent=new Intent(getActivity(), UpdateListActivity.class);
        startActivity(intent);
    }

    public static LoginFragment newInstance() {
        
        Bundle args = new Bundle();
        
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
