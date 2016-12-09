package top.kernelpanic.uglytoy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

import top.kernelpanic.uglytoy.R;
import top.kernelpanic.uglytoy.entity.Update;
import top.kernelpanic.uglytoy.entity.UpdateList;

/**
 * Created by fu on 16-12-8.
 */

public class UpdateFragment extends Fragment {

    public static final String EXTRA_UPDATE_ID="top.kernelpanic.uglytoy.update_id";
    private Update mUpdate;
    private TextView mTitleTextView;
    private TextView mDateTextView;
    private TextView mDescTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUpdate= UpdateList.get(getActivity()).getUpdate((UUID) getArguments().getSerializable(EXTRA_UPDATE_ID));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.update_detail,container,false);
        mTitleTextView=(TextView)v.findViewById(R.id.update_title);
        mDateTextView=(TextView)v.findViewById(R.id.update_pubdate);
        mDescTextView=(TextView)v.findViewById(R.id.update_desc);

        mTitleTextView.setText(mUpdate.getmTitle());
        mDateTextView.setText(DateFormat.format("发布于 yy/MM/dd  hh:mm:ss",mUpdate.getmPubDate()));
        mDescTextView.setText(mUpdate.getmDescription());
        return v;
    }

    public static UpdateFragment newInstance(UUID id) {
        
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_UPDATE_ID,id);
        
        UpdateFragment fragment = new UpdateFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
