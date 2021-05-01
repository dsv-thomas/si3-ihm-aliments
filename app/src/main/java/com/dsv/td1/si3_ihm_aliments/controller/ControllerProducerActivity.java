package com.dsv.td1.si3_ihm_aliments.controller;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.adapter.IConsumerAdapterListener;
import com.dsv.td1.si3_ihm_aliments.adapter.IProducerAdapterListener;
import com.dsv.td1.si3_ihm_aliments.consumer.Consumer;
import com.dsv.td1.si3_ihm_aliments.factory.MaraicheFactory;
import com.dsv.td1.si3_ihm_aliments.model.Model_Consumer;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Product;
import com.dsv.td1.si3_ihm_aliments.ui_consumer.producer.ProducerDescriptionFragmentConsumer;
import com.dsv.td1.si3_ihm_aliments.ui_consumer.profile.ProfileEditFragmentConsumer;
import com.dsv.td1.si3_ihm_aliments.ui_producer.profile.ProfileFragmentProducer;
import com.dsv.td1.si3_ihm_aliments.ui_producer.stock.StockAddProductFragmentProducer;
import com.dsv.td1.si3_ihm_aliments.ui_producer.stock.StockFragmentProducer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ControllerProducerActivity extends AppCompatActivity implements IConsumerAdapterListener, IProducerAdapterListener, AdapterView.OnItemSelectedListener, IPermissionRequest{

    private Bitmap picture;
    private StockAddProductFragmentProducer stockAddProductFragmentProducer;



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
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_reservation_producer, R.id.navigation_stock_producer, R.id.navigation_profile_producer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_producer_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


    }

    public void onClickProduct(int position) {
        Log.d("CONTROLLER", "position=" + position);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.nav_host_producer_fragment, new StockAddProductFragmentProducer(Model_Producer.getInstance().getProducerList().get(position)));
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onButtonShowPopupWindowClick(View view, Product product, Producer producer) {

    }

    @Override
    public void onClickProducer(int position) {


    }

    @Override
    public void onSettingsClicked() {

    }

    @Override
    public void onSubmitSettingsClicked(Producer producer, Bundle bundle) {

    }

    @Override
    public void onSubmitSettingsClicked(Consumer consumer, Bundle bundle) {

    }

    @Override
    public void onAddProductClicked() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        stockAddProductFragmentProducer = new StockAddProductFragmentProducer(Model_Producer.getInstance().getProducerList().get(0));
        ft.add(R.id.nav_host_producer_fragment, stockAddProductFragmentProducer);
        ft.addToBackStack("add");
        ft.commit();

    }

    @Override
    public void onSubmitaddProductClicked(Producer producer, Bundle bundle) {
        MaraicheFactory maraicheFactory = new MaraicheFactory();
        producer.addProducts(maraicheFactory.buildProduct(bundle.get("editTextProductName").toString(), bundle.get("editTextProductQuantity").toString(), bundle.get("editTextProductPrice").toString(),bundle.get("productImageName").toString()));

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_producer_fragment, new StockFragmentProducer()).addToBackStack(null).commit();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

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
                    stockAddProductFragmentProducer.saveToInternalStorage(picture);
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

                stockAddProductFragmentProducer.setImage(picture);
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
        stockAddProductFragmentProducer.setImage(bitmap);
    }

    @Override
    public Bitmap getPictureToSave() {
        return picture;
    }


}
