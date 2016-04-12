package com.example.hsb.qw;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class SmsObserverService extends Service {
    public SmsObserverService() {
    }

    @Override
    public void onCreate() {
        Intent intent = new Intent(this, ProtectService.class);
        startService(intent);
        super.onCreate();
        SmsContentObserver observer=new SmsContentObserver(new Handler(),this);
        getContentResolver().registerContentObserver(Uri.parse("content://sms/raw"),true,observer);
        observer.setOnReceivedMessageListener(new SmsContentObserver.Message() {
            @Override
            public void onReceived(String message) {
                Log.e("hhh",message);
            }
        });
    }

    @Override
    public void onDestroy() {
        Intent intent = new Intent(this, ProtectService.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
