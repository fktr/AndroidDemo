package top.kernelpanic.uglytoy.entity;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by fu on 16-12-8.
 */

public class UpdateList {

    private static UpdateList sUpdateList;
    private Context mAppContext;
    private ArrayList<Update> mUpdates;

    private UpdateList(Context context){
        mAppContext=context;
        mUpdates=new ArrayList<Update>();
        for(int i=0;i<64;i++){
            Update update=new Update();
            update.setmTitle("Update Title #"+i);
            update.setmDescription("Update Description @"+i);
            mUpdates.add(update);
        }
    }

    public static UpdateList get(Context context){
        if(sUpdateList==null)
            sUpdateList=new UpdateList(context.getApplicationContext());
        return sUpdateList;
    }

    public ArrayList<Update> getmUpdates() {
        return mUpdates;
    }

    public Update getUpdate(UUID id){
        for(Update update:mUpdates)
            if(update.getmId().equals(id))
                return update;
        return null;
    }
}
