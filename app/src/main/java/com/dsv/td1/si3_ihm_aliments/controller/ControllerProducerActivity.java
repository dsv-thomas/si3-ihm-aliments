package com.dsv.td1.si3_ihm_aliments.controller;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ControllerProducerActivity extends AppCompatActivity{


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


    /*
    public void onClickProduct(int position) {
        Log.d("CONTROLLER", "position=" + position);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.nav_host_fragment, new ProducerDescriptionFragment(Model_Producer.getInstance().getProducerList().get(position)));
        ft.addToBackStack(null);
        ft.commit();
    }*/

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
    /*@Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }*/

}
