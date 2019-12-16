package com.omagrahari.weatherapptrell;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.omagrahari.weatherapptrell.databinding.ActivityMainBinding;
import com.omagrahari.weatherapptrell.entity.WeatherResponse;
import com.omagrahari.weatherapptrell.util.GPSTracker;
import com.omagrahari.weatherapptrell.viewmodel.MainActivityViewModel;
import com.omagrahari.weatherapptrell.viewmodel.MainActivityViewModelFactory;

import javax.inject.Inject;

import static com.omagrahari.weatherapptrell.WeatherApplication.getWeatherApplication;
import static com.omagrahari.weatherapptrell.util.Constants.MY_PERMISSIONS_REQUEST_LOCATION;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    GPSTracker gpsTracker;

    @Inject
    MainActivityViewModelFactory mainActivityViewModelFactory;
    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        getWeatherApplication().getComponent().inject(this);

        mainActivityViewModel = new ViewModelProvider(this, mainActivityViewModelFactory).get(MainActivityViewModel.class);

        checkLocationPermission();
    }

    private void getWeather() {
        gpsTracker = new GPSTracker(this);
        observeWeather(gpsTracker.getLatitude(), gpsTracker.getLongitude());
    }

    private void observeWeather(double latitude, double longitude) {
        mainActivityViewModel.getWeather(latitude, longitude).observe(this, new Observer<WeatherResponse>() {
            @Override
            public void onChanged(WeatherResponse weatherResponse) {
                activityMainBinding.progress.setVisibility(View.GONE);
                activityMainBinding.setWeather(weatherResponse.getMain());
                activityMainBinding.executePendingBindings();
            }
        });
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                showDialog();
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            getWeather();
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        getWeather();
                    }
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    showDialog();
                }
                return;
            }

        }
    }

    public void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.msg_request_permission)
                .setMessage(R.string.msg_request_permission_)
                .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Prompt the user once explanation has been shown
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSIONS_REQUEST_LOCATION);
                    }
                })
                .create()
                .show();
    }
}
