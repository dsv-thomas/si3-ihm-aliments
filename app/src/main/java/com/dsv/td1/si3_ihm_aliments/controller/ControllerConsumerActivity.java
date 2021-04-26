package com.dsv.td1.si3_ihm_aliments.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.TextView;

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
import com.dsv.td1.si3_ihm_aliments.consumer.Reservation;
import com.dsv.td1.si3_ihm_aliments.model.Model_Consumer;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Poisson;
import com.dsv.td1.si3_ihm_aliments.product.Product;
import com.dsv.td1.si3_ihm_aliments.ui_consumer.producer.ProducerDescriptionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ControllerConsumerActivity extends AppCompatActivity implements IConsumerAdapterListener, IProducerAdapterListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_consumer);
        try {
            Log.d("CONTROLLERACTIVITY", "nb observers=" + Model_Producer.getInstance().countObservers());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
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


    /**
     * ACTIONS
     *
     * @Override public void onButtonClicked(int action) {
     * Log.d("CONTROLLER","callback() -->  action="+action);
     * switch (action) {
     * case BACK :  Log.d("CONTROLLER","TEST"); break;
     * }
     * }
     */
    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        Log.d("BACK", String.valueOf(count));
        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }


    @Override
    public void onClickProduct(int position) {
        Log.d("CONTROLLER", "position=" + position);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.nav_host_consumer_fragment, new ProducerDescriptionFragment(Model_Producer.getInstance().getProducerList().get(position)));
        ft.addToBackStack(null);
        ft.commit();
    }


    @Override
    public void onButtonShowPopupWindowClick(View view, Product product) {
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
        numberPicker.setMinValue(0);
        numberPicker.setValue(0);

        textView.setText(product.getName());

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        Button confirmReservation = popupView.findViewById(R.id.confirmReservation);
        confirmReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("RESERVATION", product.getName()+ " "+ Model_Consumer.getInstance().getConsumerList().get(0).getName());
                Consumer consumer = Model_Consumer.getInstance().getConsumerList().get(0);
                Model_Consumer.getInstance().addProductForReservation(consumer, new Reservation(consumer, product, 4, "Antibes"));
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
    public void onClickProducer(int position) {
        Log.d("CONTROLLER", "position=" + position);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.nav_host_consumer_fragment, new ProducerDescriptionFragment(Model_Producer.getInstance().getProducerList().get(position)));
        ft.addToBackStack(null);
        ft.commit();
    }
}