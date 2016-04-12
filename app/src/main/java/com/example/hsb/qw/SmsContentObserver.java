package com.example.hsb.qw;

import android.app.Service;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

/**
 * ,
 * Created by 黄帅博 on 4/11/2016.
 */
public class SmsContentObserver extends ContentObserver {
    private Service service;
    private Message message;

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public SmsContentObserver(Handler handler, Service service) {
        super(handler);
        this.service = service;
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        try {
            //暂停一下，等待新短信写入数据库后再读取
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String body = getBody();
        message.onReceived(body);
    }

    private String getBody() {
        ContentResolver resolver = service.getContentResolver();
        Cursor cursor = resolver.query(Uri.parse("content://sms/"), new String[]{"address", "date", "type", "body"},
                null, null, null);
        String body;
        assert cursor != null;
        cursor.moveToNext();
        int indexBody = cursor.getColumnIndex("body");
        body = cursor.getString(indexBody);
        cursor.close();
        return body;
    }

    public interface Message{
        void onReceived(String message);
    }

    public void setOnReceivedMessageListener(Message message){
        this.message=message;
    }
}
