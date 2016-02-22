package com.fezrestia.android.application.gotosleep.widget;

import com.fezrestia.android.application.gotosleep.service.GoToSleepWidgetService;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class GoToSleepWidget extends AppWidgetProvider {
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        // NOP.
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager manager, int[] widgetIds) {
        super.onUpdate(context, manager, widgetIds);

        // Start service.
        Intent intent = new Intent(context, GoToSleepWidgetService.class);
        context.startService(intent);
    }

    @Override
    public void onDeleted(Context context, int[] widgetIds) {
        super.onDeleted(context, widgetIds);
        // NOP.
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);

        // Stop service.
        Intent intent = new Intent(context, GoToSleepWidgetService.class);
        context.stopService(intent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        // NOP.
    }
}
