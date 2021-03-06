package com.dsv.td1.si3_ihm_aliments.controller;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.adapter.IAdapterListener;
import com.dsv.td1.si3_ihm_aliments.adapter.IConsumerListener;
import com.dsv.td1.si3_ihm_aliments.consumer.Consumer;
import com.dsv.td1.si3_ihm_aliments.consumer.PickupPoint;
import com.dsv.td1.si3_ihm_aliments.consumer.Reservation;
import com.dsv.td1.si3_ihm_aliments.helpers.CalendarHelper;
import com.dsv.td1.si3_ihm_aliments.model.Model_Consumer;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Product;
import com.dsv.td1.si3_ihm_aliments.ui_consumer.producer.ProducerDescriptionFragmentConsumer;
import com.dsv.td1.si3_ihm_aliments.ui_consumer.profile.ProfileEditFragmentConsumer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;

import static com.dsv.td1.si3_ihm_aliments.MainActivity.CHANNEL_ID;


public class ControllerConsumerActivity extends AppCompatActivity implements IConsumerListener, IAdapterListener, AdapterView.OnItemSelectedListener, IPermissionRequest {

    private Bitmap picture;
    private ProfileEditFragmentConsumer profileEditFragmentConsumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_consumer);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_producer, R.id.navigation_map, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_consumer_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }


    @Override
    public void onClickItemListView(int position, int action) {
        FragmentTransaction ft;
        switch (action) {
            case ACTION_CLICK_PRODUCER:
                ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.nav_host_consumer_fragment, new ProducerDescriptionFragmentConsumer(Model_Producer.getInstance().getProducerList().get(position)));
                ft.addToBackStack(null);
                ft.commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onButtonShowPopupWindowClick(View view, Product product, Producer producer) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.product_popup, null);


        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        TextView textView = popupView.findViewById(R.id.productName);

        NumberPicker numberPicker = popupView.findViewById(R.id.quantityReservation);
        numberPicker.setMaxValue(20);
        numberPicker.setMinValue(1);
        numberPicker.setValue(1);

        Spinner spinner = popupView.findViewById(R.id.pickupPoints);
        ArrayAdapter<PickupPoint> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, producer.getPickupPoints());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        textView.setText(product.getName());

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        Button confirmReservation = popupView.findViewById(R.id.confirmReservation);
        confirmReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Consumer consumer = Model_Consumer.getInstance().getConsumerList().get(0);

                PickupPoint pickupPoint = (PickupPoint) spinner.getSelectedItem();
                Model_Consumer.getInstance().addProductForReservation(consumer, new Reservation(consumer, producer, product, numberPicker.getValue(), pickupPoint));

                startActivity(CalendarHelper.addEventToCalendar(
                        ControllerConsumerActivity.this,
                        pickupPoint.getPlace(),
                        pickupPoint.getStartDate(), pickupPoint.getEndDate(),
                        numberPicker.getValue() + "Kg de" + product.getName() + " a r??cup??rer."));

                String title = spinner.getSelectedItem().toString();
                String message = "Nouvelle r??servation enregistr??e";
                sendNotificationOnChannel(title,
                        message,
                        CHANNEL_ID,
                        NotificationCompat.PRIORITY_DEFAULT);

                popupWindow.dismiss();
            }
        });

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    @Override
    public void onSettingsClicked() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        profileEditFragmentConsumer = new ProfileEditFragmentConsumer(Model_Consumer.getInstance().getConsumerList().get(0));
        ft.add(R.id.nav_host_consumer_fragment, profileEditFragmentConsumer);
        ft.addToBackStack("profileEdit");
        ft.commit();
    }

    @Override
    public void onSubmitSettingsClicked(Consumer consumer, Bundle bundle) {
        //Model_Consumer.getInstance().modifyName(consumer, bundle.get("name").toString());
        Model_Consumer.getInstance().modifyProfile(consumer, bundle);
        getSupportFragmentManager().popBackStack("profileEdit", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(this, "This app requires permission to be granted in order to work properly", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CAMERA:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast toast = Toast.makeText(getApplicationContext(), "CAMERA authorization granted", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "CAMERA authorization NOT granted", Toast.LENGTH_LONG);
                    toast.show();
                }
                break;
            case REQUEST_MEDIA_WRITE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    profileEditFragmentConsumer.saveToInternalStorage(picture);
                    Toast.makeText(getApplicationContext(), "Write permission GRANTED", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Write permission NOT GRANTED", Toast.LENGTH_LONG).show();
                }
                break;

            case REQUEST_MEDIA_READ:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(getApplicationContext(), "Read permission GRANTED", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Read permission NOT GRANTED", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA) {
            if (resultCode == RESULT_OK) {
                picture = (Bitmap) data.getExtras().get("data");
                profileEditFragmentConsumer.setImage(picture);
            } else if (resultCode == RESULT_CANCELED) {
                Toast toast = Toast.makeText(getApplicationContext(), "picture canceled", Toast.LENGTH_LONG);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "action failed", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    @Override
    public void onPictureLoad(Bitmap bitmap) {
        profileEditFragmentConsumer.setImage(bitmap);
    }

    @Override
    public Bitmap getPictureToSave() {
        return picture;
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        Model_Consumer.getInstance().removeProductFromReservation(Model_Consumer.getInstance().getConsumerList().get(0), reservation);
    }

    private void sendNotificationOnChannel(String title, String message, String channelId, int priority) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(R.drawable.bonuspack_bubble)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(priority);
        notification.setSmallIcon(R.drawable.bonuspack_bubble);
        NotificationManagerCompat.from(this).notify(1, notification.build());
    }
}