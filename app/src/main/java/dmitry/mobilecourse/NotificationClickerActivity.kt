package dmitry.mobilecourse

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationClickerActivity : AppCompatActivity() {

    companion object {
        private var count = 0

        private fun showNotification(context: Context) {
            val increasePendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                Intent(context, NotificationReceiver::class.java).apply {
                    action = "increase"
                },
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )

            val decreasePendingIntent = PendingIntent.getBroadcast(
                context,
                1,
                Intent(context, NotificationReceiver::class.java).apply {
                    action = "decrease"
                },
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )

            val notification = NotificationCompat.Builder(context, "counter_channel")
                .setContentTitle("Counter")
                .setContentText("Current count: $count")
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .addAction(R.drawable.ic_notifications_black_24dp, "Increase", increasePendingIntent)
                .addAction(R.drawable.ic_notifications_black_24dp, "Decrease", decreasePendingIntent)
                .build()

            NotificationManagerCompat.from(context).apply {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    notify(1, notification)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()
        showNotification(this)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Counter Channel"
            val descriptionText = "Channel for Counter Notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("counter_channel", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    class NotificationReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (action == "increase") {
                count++
            } else if (action == "decrease") {
                if (count > 0) {
                    count--
                }
            }
            showNotification(context)
        }
    }
}
