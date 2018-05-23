package com.lyd.lyd.amap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btMap;
    private Button btOfflineMap;
    private Button btLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        btMap = findViewById(R.id.bt_map);
        btOfflineMap = findViewById(R.id.bt_offline_map);
        btLocation = findViewById(R.id.bt_location);
    }

    private void initListener() {
        btMap.setOnClickListener(this);
        btOfflineMap.setOnClickListener(this);
        btLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_map:
                startActivity(new Intent(this.getApplicationContext(), MapActivity.class));
                break;
            case R.id.bt_offline_map:
                startActivity(new Intent(this.getApplicationContext(), com.amap.api.maps.offlinemap.OfflineMapActivity.class));
                break;
            case R.id.bt_location:
                startActivity(new Intent(this.getApplicationContext(), LocationActivity.class));
                break;
        }
    }
}

