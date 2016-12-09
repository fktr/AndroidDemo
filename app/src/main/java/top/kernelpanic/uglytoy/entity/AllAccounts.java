package top.kernelpanic.uglytoy.entity;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by fu on 16-12-8.
 */

public class AllAccounts {

    private static AllAccounts sAllAccounts;
    private Context mAppContext;
    private ArrayList<Account>mAccounts;

    private AllAccounts(Context context){
        mAppContext=context;
        mAccounts=new ArrayList<Account>();
    }

    public static AllAccounts get(Context context){
        if(sAllAccounts==null)
            sAllAccounts=new AllAccounts(context.getApplicationContext());
        return sAllAccounts;
    }

    public ArrayList<Account> getmAccounts() {
        return mAccounts;
    }

    public Account getAccountByName(String name){
        for(Account account:mAccounts)
            if(account.getmUsername().equals(name))
                return account;
        return null;
    }

    public Account getAccountByEmail(String email){
        for(Account account:mAccounts)
            if(account.getmEmail().equals(email))
                return account;
        return null;
    }
}
