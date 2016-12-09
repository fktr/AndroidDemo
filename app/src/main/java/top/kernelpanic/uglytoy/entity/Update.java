package top.kernelpanic.uglytoy.entity;

import java.util.Date;
import java.util.UUID;

/**
 * Created by fu on 16-12-8.
 */

public class Update {

    private UUID mId;
    private String mTitle;
    private String mDescription;
    private Date mPubDate;

    public Update(){
        mId=UUID.randomUUID();
        mPubDate=new Date();
    }

    public UUID getmId() {
        return mId;
    }

    public Date getmPubDate() {
        return mPubDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
