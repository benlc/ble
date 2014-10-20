package receiver.ble.read_on.ly.blereceiver;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ReceiverActivity extends Activity {

    private BluetoothLeScanner mBluetoothLeScanner;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        startReceiving();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        stopReceiving();
    }

    private ScanCallback mScanCallback = new ScanCallback() {
        @Override public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            Toast.makeText(ReceiverActivity.this, "onScanResult: " + result.getDevice().getName(), Toast.LENGTH_SHORT).show();
        }

        @Override public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
            Toast.makeText(ReceiverActivity.this, "onBatchScanResults", Toast.LENGTH_SHORT).show();
        }

        @Override public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            Toast.makeText(ReceiverActivity.this, "onScanFailed", Toast.LENGTH_SHORT).show();
        }
    };

    private void startReceiving() {
        mBluetoothLeScanner = BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();

        mBluetoothLeScanner.startScan(mScanCallback);
    }

    private void stopReceiving() {
        mBluetoothLeScanner.stopScan(mScanCallback);
    }
}
