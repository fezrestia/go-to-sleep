package com.fezrestia.android.application.gotosleep.service;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.fezrestia.android.application.gotosleep.R;
import com.fezrestia.android.application.gotosleep.GoToSleepActivity;
import com.fezrestia.android.application.gotosleep.widget.GoToSleepWidget;


public class GoToSleepWidgetService extends Service {
    private static final String ON_TOUCH_INTENT_ACTION
            = "com.fezrestia.android.application.gotosleep.WIDGET_ON_TOUCH";

    @Override
    public IBinder onBind(Intent intent) {
        // NOP.
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        // Check caller state.
        if (ON_TOUCH_INTENT_ACTION.equals(intent.getAction())) {
            // This is onTouch action.
            Intent launchIntent = new Intent(Intent.ACTION_MAIN);
            launchIntent.addCategory(Intent.CATEGORY_DEFAULT);
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            launchIntent.setClass(getApplicationContext(), GoToSleepActivity.class);
            this.startActivity(launchIntent);
        }

        // Get RemoteView.
        RemoteViews view = new RemoteViews(
                this.getPackageName(),
                R.layout.layout_go_to_sleep_widget);

        // Create on touch Intent.
        Intent onTouchIntent = new Intent();
        onTouchIntent.setAction(ON_TOUCH_INTENT_ACTION);

        // Create pending Intent.
        PendingIntent pendingIntent = PendingIntent.getService(
                this,
                0,
                onTouchIntent,
                0);

        // Set pending Intent to RemoteViews.
        view.setOnClickPendingIntent(R.id.button, pendingIntent);

        // Update widget view.
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        ComponentName widget = new ComponentName(this, GoToSleepWidget.class);
        manager.updateAppWidget(widget,view);
    }

    @Override
    public void onDestroy() {
        // NOP.
    }
}
