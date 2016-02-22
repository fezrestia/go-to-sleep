package com.fezrestia.android.application.gotosleep;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fezrestia.android.common.log.LogConfig;

public class GoToSleepActivity extends Activity {
    private static final String TAG = "GoToSleepActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // NOP.
    }

    @Override
    protected void onResume() {
        super.onResume();

        // This component identifier.
        ComponentName cn = new ComponentName(
                this.getPackageName(),
                "com.fezrestia.android.application.gotosleep.receiver.DeviceSecurityReceiver");

        // Get DPM.
        DevicePolicyManager dpm;
        dpm = (DevicePolicyManager) this.getSystemService(Context.DEVICE_POLICY_SERVICE);

        // Check application get device administrator right or not.
        if (dpm.isAdminActive(cn)) {
            // Lock immediately.
            try {
                dpm.lockNow();
            } catch(SecurityException e) {
                if(LogConfig.dLog) Log.d("TraceLog",
                        TAG + ".onResume():[SecurityException:Application is not Device Admin.]");

                // Indicate.
                Toast.makeText(this, "Security Exception", Toast.LENGTH_SHORT).show();

                finish();
            }
        } else {
            // Not have device administrator right.

            // Indicate.
            Toast.makeText(this, "Not Device Administrator", Toast.LENGTH_SHORT).show();

            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // In either case, application is forced to finish.
        finish();
    }
}
