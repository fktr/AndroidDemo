package top.kernelpanic.uglytoy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import top.kernelpanic.uglytoy.R;
import top.kernelpanic.uglytoy.activity.UpdatePagerActivity;
import top.kernelpanic.uglytoy.entity.Update;
import top.kernelpanic.uglytoy.entity.UpdateList;

/**
 * Created by fu on 16-12-8.
 */

public class UpdateListFragment extends ListFragment {

    private ArrayList<Update>mUpdates;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUpdates= UpdateList.get(getActivity()).getmUpdates();

        ArrayAdapter<Update>adapter=new UpdateListItemAdapter(mUpdates);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Update update=((UpdateListItemAdapter)getListAdapter()).getItem(position);
        Intent intent=new Intent(getActivity(), UpdatePagerActivity.class);
        intent.putExtra(UpdateFragment.EXTRA_UPDATE_ID,update.getmId());
        startActivity(intent);
    }

    public static UpdateListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        UpdateListFragment fragment = new UpdateListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private class UpdateListItemAdapter extends ArrayAdapter<Update>{

        public UpdateListItemAdapter(ArrayList<Update>updates){
            super(getActivity(),0,updates);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView=getActivity().getLayoutInflater().inflate(R.layout.update_list_item,null);
            }
            Update update=getItem(position);
            TextView titleTextView=(TextView)convertView.findViewById(R.id.update_list_item_title);
            titleTextView.setText(update.getmTitle());
            TextView dateTextView=(TextView)convertView.findViewById(R.id.update_list_item_pubdate);
            dateTextView.setText(DateFormat.format("yyyy/MM/dd  hh:mm:ss",update.getmPubDate()));

            return convertView;
        }
    }
}
