package top.kernelpanic.uglytoy.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

import top.kernelpanic.uglytoy.R;

/**
 * Created by fu on 16-12-8.
 */

public class MessageDialogFragment extends DialogFragment {

    private static String MSG_TITLE="msg_title";
    private static String MSG_OK_TEXT="msg_ok_text";
    private static String MSG_CONTENT="msg_content";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v=getActivity().getLayoutInflater().inflate(R.layout.message_dialog,null);
        TextView okTextView=(TextView) v.findViewById(R.id.message_content);
        okTextView.setText(getArguments().getSerializable(MSG_CONTENT).toString());
        return new AlertDialog.Builder(getActivity()).setView(v)
                .setTitle(getArguments().getSerializable(MSG_TITLE).toString())
                .setPositiveButton(getArguments().get(MSG_OK_TEXT).toString(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sendResult(Activity.RESULT_OK);
                    }
                }).create();
    }

    private void sendResult(int resultCode){
        if(getTargetFragment()==null)
            return ;
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,null);
    }

    public static MessageDialogFragment newInstance(String title,String content,String okText) {

        Bundle args = new Bundle();
        args.putSerializable(MSG_TITLE,title);
        args.putSerializable(MSG_CONTENT,content);
        args.putSerializable(MSG_OK_TEXT,okText);

        MessageDialogFragment fragment = new MessageDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
