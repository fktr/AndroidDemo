package top.kernelpanic.uglytoy.fragment;

import android.app.Activity;
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
import top.kernelpanic.uglytoy.activity.LoginActivity;
import top.kernelpanic.uglytoy.entity.Account;
import top.kernelpanic.uglytoy.entity.AllAccounts;

/**
 * Created by fu on 16-12-8.
 */

public class RegisterFragment extends Fragment {

    private static final String REGISTER_ERROR_TAG="RegisterErrorTag";
    private static final String REGISTER_SUCCESS_TAG ="RegisterSuccessTag";
    private static final int REQUEST_JMP_LOGIN=0;
    private TextView mGoLoginTextView;
    private Account mAccount;
    private EditText mUserNameEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mRepeatPasswordEditText;
    private Button mRegisterButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAccount=new Account();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.register,container,false);
        mGoLoginTextView=(TextView)v.findViewById(R.id.go_login);
        mUserNameEditText=(EditText)v.findViewById(R.id.register_username);
        mEmailEditText=(EditText)v.findViewById(R.id.register_email);
        mPasswordEditText=(EditText)v.findViewById(R.id.register_password);
        mRepeatPasswordEditText=(EditText)v.findViewById(R.id.register_password_repeat);
        mRegisterButton=(Button)v.findViewById(R.id.register_button);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getActivity().getSupportFragmentManager();
                MessageDialogFragment msgDialog;
                String strUsername=mUserNameEditText.getText().toString();
                String strEmail=mEmailEditText.getText().toString();
                String strPassword=mPasswordEditText.getText().toString();
                String strRepeatPassword=mRepeatPasswordEditText.getText().toString();
                if(strUsername.equals("")||strEmail.equals("") ||strPassword.equals("")||strRepeatPassword.equals("")){
                    msgDialog=MessageDialogFragment.newInstance("信息不足","请填写完整的用户信息","重新注册");
                    msgDialog.show(fm,REGISTER_ERROR_TAG);
                    return ;
                }
                if(AllAccounts.get(getActivity()).getAccountByName(strUsername)!=null
                        || AllAccounts.get(getActivity()).getAccountByEmail(strEmail)!=null){
                    msgDialog=MessageDialogFragment.newInstance("非法注册","该用户名或邮箱已被注册","重新注册");
                    msgDialog.show(fm,REGISTER_ERROR_TAG);
                    return ;
                }
                if(!strPassword.equals(strRepeatPassword)){
                    msgDialog=MessageDialogFragment.newInstance("密码不一致","请确保两次密码输入相同","重新注册");
                    msgDialog.show(fm,REGISTER_ERROR_TAG);
                    return ;
                }
                mAccount.setmUsername(strUsername);
                mAccount.setmEmail(strEmail);
                mAccount.setmPassword(strPassword);
                AllAccounts.get(getActivity()).getmAccounts().add(mAccount);
                msgDialog=MessageDialogFragment.newInstance("注册成功","睁眼看世界,用心品人生.朋友,欢迎你","前往登录");
                msgDialog.setTargetFragment(RegisterFragment.this,REQUEST_JMP_LOGIN);
                msgDialog.show(fm, REGISTER_SUCCESS_TAG);
            }
        });
        mGoLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode!= Activity.RESULT_OK)
            return ;
        if(requestCode==REQUEST_JMP_LOGIN){
            Intent intent=new Intent(getActivity(),LoginActivity.class);
            startActivity(intent);
        }
    }

    public static RegisterFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
