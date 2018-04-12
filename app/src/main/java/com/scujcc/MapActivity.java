package com.scujcc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.scujcc.dada.R;

public class MapActivity extends AppCompatActivity {

    private TextView mTextView;

    private AMapLocationClient locationClient;
    private AMapLocationClientOption locationOption;

    private AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                mTextView.setText(aMapLocation.getCity());
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mTextView = findViewById(R.id.text_location);

        locationOption = new AMapLocationClientOption();
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locationOption.setOnceLocation(true);
        locationOption.setOnceLocationLatest(true);
        locationOption.setInterval(1000);
        locationOption.setNeedAddress(true);

        locationClient = new  AMapLocationClient(getApplicationContext());
        locationClient.setLocationOption(locationOption);
        locationClient.setLocationListener(locationListener);
    }

    public void button(View view) {
        Toast.makeText(MapActivity.this,"哈哈哈",Toast.LENGTH_SHORT).show();
        startLocation();

    }

    private void startLocation() {
        Log.d("MAP","开始定位");
        locationClient.setLocationOption(locationOption);
        locationClient.startLocation();

    }

    private void destoryLocation() {
        if (locationClient != null) {
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }

    }
}
