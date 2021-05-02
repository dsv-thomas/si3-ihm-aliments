package com.dsv.td1.si3_ihm_aliments;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.dsv.td1.si3_ihm_aliments.controller.ControllerConsumerActivity;
import com.dsv.td1.si3_ihm_aliments.controller.ControllerProducerActivity;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public static final String CHANNEL_ID = "channel1";
    public static NotificationManager notificationManager;

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel Panier Gourmand",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Channel pour l'application des producteurs de sa région");
            notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findViewById(R.id.start_client).setOnClickListener(click-> {
            Intent intent = new Intent(getApplicationContext(), ControllerConsumerActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.start_producteur).setOnClickListener(click-> {
            Intent intent = new Intent(getApplicationContext(), ControllerProducerActivity.class);
            startActivity(intent);
        });
        createNotificationChannel();
    }

}


