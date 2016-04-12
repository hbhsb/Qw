package com.example.hsb.qw;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ProtectService extends Service {
    public ProtectService() {
    }

    @Override
    public void onCreate() {
        Intent intent = new Intent(this, SmsObserverService.class);
        startService(intent);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Intent intent = new Intent(this, SmsObserverService.class);
        stopService(intent);
        startService(intent);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
