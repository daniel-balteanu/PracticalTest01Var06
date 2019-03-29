package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

public class ProcessingThread extends Thread {

    private Context context;


    public ProcessingThread(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        Log.d("DEBUG:", "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
        while(true) {
            sendMessage(1);
            sleep();
        }
    }

    private void sendMessage(int messageType) {
        Intent intent = new Intent();
        intent.setAction("SERVICE_VALUE");
        Random r = new Random();
        int value = r.nextInt(5);
        intent.putExtra("SERVICE_VALUE", value);
        Log.d("PROCESSING:", "shows" + Integer.toString(value));
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException interruptedException) {
            Log.e("DEBUG:", interruptedException.getMessage());
            interruptedException.printStackTrace();
        }
    }

}