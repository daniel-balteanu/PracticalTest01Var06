package ro.pub.cs.systems.eim.practicaltest01var06;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var06Service extends Service {
    public PracticalTest01Var06Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("DEBUG:", "onStartCommand() method was invoked");
        ProcessingThread processingThread = new ProcessingThread(this);
        processingThread.start();
        return START_REDELIVER_INTENT;
    }
}
