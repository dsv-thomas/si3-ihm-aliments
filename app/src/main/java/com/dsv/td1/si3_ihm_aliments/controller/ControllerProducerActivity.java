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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.adapter.IAdapterListener;
import com.dsv.td1.si3_ihm_aliments.adapter.IProducerListener;
import com.dsv.td1.si3_ihm_aliments.consumer.PickupPoint;
import com.dsv.td1.si3_ihm_aliments.consumer.Reservation;
import com.dsv.td1.si3_ihm_aliments.factory.MaraicheFactory;
import com.dsv.td1.si3_ihm_aliments.model.Model_Consumer;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Product;
import com.dsv.td1.si3_ihm_aliments.ui_consumer.producer.ProducerDescriptionFragmentConsumer;
import com.dsv.td1.si3_ihm_aliments.ui_producer.profile.ProfileEditFragmentProducer;
import com.dsv.td1.si3_ihm_aliments.ui_producer.profile.ProfileFragmentProducer;
import com.dsv.td1.si3_ihm_aliments.ui_producer.stock.StockAddProductFragmentProducer;
import com.dsv.td1.si3_ihm_aliments.ui_producer.stock.StockFragmentProducer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerProducerActivity extends AppCompatActivity implements IProducerListener, IAdapterListener, IPermissionRequest {

    private Bitmap picture;
    private StockAddProductFragmentProducer stockAddProductFragmentProducer;
    private ProfileEditFragmentProducer profileEditFragmentProducer;
    private String lacurrentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_producer);
        try {
            Log.d("CONTROLLERACTIVITY", "nb observers=" + Model_Producer.getInstance().countObservers());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        BottomNavigationView navView = findViewById(R.id.nav_view_producer);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_reservation_producer, R.id.navigation_stock_producer, R.id.navigation_profile_producer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_producer_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        lacurrentLayout = navController.getCurrentBackStackEntry().toString();
    }


    @Override
    public void onClickItemListView(int position, int action) {
        FragmentTransaction ft;
        switch (action) {
            case ACTION_CLICK_PRODUCT:
                Log.d("CONTROLLER", "position=" + position);
                ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.nav_host_consumer_fragment, new ProducerDescriptionFragmentConsumer(Model_Producer.getInstance().getProducerList().get(position)));
                ft.addToBackStack(null);
                ft.commit();
                break;
            case ACTION_CLICK_PRODUCER:
                ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.nav_host_consumer_fragment, new ProducerDescriptionFragmentConsumer(Model_Producer.getInstance().getProducerList().get(position)));
                ft.addToBackStack(null);
                ft.commit();
                break;
        }
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        Model_Consumer.getInstance().removeProductFromReservation(Model_Consumer.getInstance().getConsumerList().get(0),reservation);
    }


    @Override
    public void onSettingsClicked() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        profileEditFragmentProducer = new ProfileEditFragmentProducer(Model_Producer.getInstance().getProducerList().get(0));
        ft.add(R.id.nav_host_producer_fragment, profileEditFragmentProducer);
        ft.addToBackStack("profileEdit");
        ft.commit();
    }

    @Override
    public void onAddProductClicked() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        stockAddProductFragmentProducer = new StockAddProductFragmentProducer(Model_Producer.getInstance().getProducerList().get(0));
        ft.add(R.id.nav_host_producer_fragment, stockAddProductFragmentProducer);
        ft.addToBackStack("stockAddProduct");
        ft.commit();
    }

    @Override
    public void onSubmitaddProductClicked(Producer producer, Bundle bundle) {
        MaraicheFactory maraicheFactory = new MaraicheFactory();
        Model_Producer.getInstance().addProduct(producer, maraicheFactory.buildProduct(bundle.get("editTextProductName").toString(), bundle.get("editTextProductQuantity").toString(), bundle.get("editTextProductPrice").toString(), bundle.get("productImageName").toString()));
        getSupportFragmentManager().popBackStack("stockAddProduct", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onSubmitSettingsClicked(Producer producer, Bundle bundle) {
        //Model_Consumer.getInstance().modifyName(consumer, bundle.get("name").toString());
        Model_Producer.getInstance().modifyProfile(producer, bundle);
        getSupportFragmentManager().popBackStack("profileEdit", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        //getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_producer_fragment, new ProfileFragmentProducer()).addToBackStack(null).commit();
    }

    @Override
    public void onButtonShowPopupWindowClick(View view, Product product, Producer producer) {
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
                    stockAddProductFragmentProducer.saveToInternalStorage(picture);
                    if (stockAddProductFragmentProducer != null) {
                        stockAddProductFragmentProducer.saveToInternalStorage(picture);
                        ;
                    } else if (profileEditFragmentProducer != null) {
                        profileEditFragmentProducer.saveToInternalStorage(picture);
                    }
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
                if (stockAddProductFragmentProducer != null) {
                    stockAddProductFragmentProducer.setImage(picture);
                } else if (profileEditFragmentProducer != null) {
                    profileEditFragmentProducer.setImage(picture);
                }
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
    public void onButtonShowPopupAddPickupPointClick(View view) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pickuppoint_popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm");

        //TODO: Exemple
        EditText place = popupView.findViewById(R.id.editTextMultiLinePlacePickupPoint);
        EditText date = popupView.findViewById(R.id.editTextDatePickupPoint);
        EditText time = popupView.findViewById(R.id.editTextTimePickupPoint);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        Button confirmPickupPoint = popupView.findViewById(R.id.confirmPickupPoint);
        confirmPickupPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Producer producer = Model_Producer.getInstance().getProducerList().get(0);
                Date dateFormat = null;
                try {
                    dateFormat = simpleDateFormat.parse(date.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date timeFormat = null;
                try {
                    timeFormat = simpleTimeFormat.parse(time.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Model_Producer.getInstance().addPickupPoint(producer, new PickupPoint(place.getText().toString(), dateFormat, timeFormat));
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
    public void onProfilPictureLoad(Bitmap bitmap) {
        profileEditFragmentProducer.setImage(bitmap);
    }

    @Override
    public void onProductPictureLoad(Bitmap bitmap) {
        stockAddProductFragmentProducer.setImage(bitmap);
    }

    @Override
    public Bitmap getPictureToSave() {
        return picture;
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        Log.d("BACK", String.valueOf(count));
        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            if(getSupportFragmentManager().findFragmentByTag("profileEdit") != null) {
                getSupportFragmentManager().popBackStackImmediate(lacurrentLayout,0);
            }
            getSupportFragmentManager().popBackStack();
        }
    }
}
