package com.fezrestia.android.application.gotosleep.receiver;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.fezrestia.android.common.log.LogConfig;

public class DeviceSecurityReceiver extends DeviceAdminReceiver {
    private static final String TAG = "DeviceSecurityReceiver";

    @Override
    public void onEnabled(Context context, Intent intent) {
        if(LogConfig.dLog) Log.d("TraceLog", TAG + ".onEnabled():[IN]");
        // NOP.
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        if(LogConfig.dLog) Log.d("TraceLog", TAG + ".onDisabled():[IN]");
        // NOP.
    }

    @Override
    public void onPasswordChanged(Context context, Intent intent) {
        if(LogConfig.dLog) Log.d("TraceLog", TAG + ".onPasswordChanged():[IN]");
        // NOP.
    }
}
