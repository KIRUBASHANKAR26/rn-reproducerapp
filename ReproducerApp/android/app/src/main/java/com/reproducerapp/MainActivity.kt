package com.reproducerapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentResolver
import android.graphics.drawable.Drawable
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.widget.LinearLayout
import android.os.Bundle
import org.devio.rn.splashscreen.SplashScreen
import com.reactnativenavigation.NavigationActivity

class MainActivity : NavigationActivity() {
    @Override
    protected fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel("long_notification_sound", "Notification", NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.setShowBadge(true)
            notificationChannel.setDescription("")
            val att: AudioAttributes = Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build()
            notificationChannel.setSound(Uri.parse((ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getPackageName()).toString() + "/raw/iphoneringtone4"), att)
            notificationChannel.enableVibration(true)
            notificationChannel.setVibrationPattern(longArrayOf(400, 400))
            notificationChannel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            val manager: NotificationManager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(notificationChannel)
        }
        SplashScreen.show(this)
        super.onCreate(savedInstanceState)
    }
}
