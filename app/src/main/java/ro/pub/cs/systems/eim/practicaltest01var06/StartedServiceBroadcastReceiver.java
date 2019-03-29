package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

public class StartedServiceBroadcastReceiver extends BroadcastReceiver {

    private TextView messageTextView;


    public StartedServiceBroadcastReceiver() {
        this.messageTextView = null;
    }

    public StartedServiceBroadcastReceiver(TextView messageTextView) {
        this.messageTextView = messageTextView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: exercise 7 - get the action and the extra information from the intent
        // and set the text on the messageTextView

        String action = intent.getAction();
        int data = 0;
        Log.d("!!!!!!!:", action);
//        if ("SERVICE_VALUE".equals(action)) {
            data = intent.getIntExtra("SERVICE_VALUE", -1);
//        }
        if (messageTextView != null) {
            messageTextView.setText(Integer.toString(data));
        }
    }
}
