package advertiser.ble.read_on.ly.bleadvertiser;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.os.Bundle;
import android.widget.Toast;

public class AdvertiserActivity extends Activity {

    private BluetoothLeAdvertiser mBluetoothAdvertiser;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertiser);

        startAdvertising();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        stopAdvertising();
    }

    private AdvertiseCallback mAdvertiseCallback = new AdvertiseCallback() {
        @Override public void onStartSuccess(AdvertiseSettings settingsInEffect) {
            super.onStartSuccess(settingsInEffect);
            Toast.makeText(AdvertiserActivity.this, "BluetoothAdvertiser started successfully", Toast.LENGTH_SHORT).show();
        }

        @Override public void onStartFailure(int errorCode) {
            super.onStartFailure(errorCode);
            Toast.makeText(AdvertiserActivity.this, "BluetoothAdvertiser failed to start", Toast.LENGTH_SHORT).show();
        }
    };


    private void startAdvertising() {
        mBluetoothAdvertiser = BluetoothAdapter.getDefaultAdapter().getBluetoothLeAdvertiser();

        // Not needed for raw advertising
        AdvertiseSettings advertiseSettings = new AdvertiseSettings.Builder().build();
        AdvertiseData advertiseData = new AdvertiseData.Builder().build();

        mBluetoothAdvertiser.startAdvertising(advertiseSettings, advertiseData, mAdvertiseCallback);
    }

    private void stopAdvertising() {
        mBluetoothAdvertiser.stopAdvertising(mAdvertiseCallback);
    }
}
