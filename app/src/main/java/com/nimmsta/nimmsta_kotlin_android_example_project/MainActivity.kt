package com.nimmsta.nimmsta_kotlin_android_example_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nimmsta.barcode.Barcode
import com.nimmsta.core.android.framework.NIMMSTAServiceConnection
import com.nimmsta.core.shared.device.NIMMSTADevice
import com.nimmsta.core.shared.device.NIMMSTAEventHandler
import com.nimmsta.core.shared.textprotocol.event.ScanEvent
import com.nimmsta.core.shared.textprotocol.event.TouchEvent

class MainActivity : AppCompatActivity(), NIMMSTAEventHandler {

    /* ... */
    private lateinit var serviceConnection: NIMMSTAServiceConnection
    /* ... */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serviceConnection = NIMMSTAServiceConnection.bindServiceToActivity(this).onComplete {
            try {
                // this is the point in time when the (background) task completes and the result throws if an error occurred.
                it.result

                it.result.enableBackgroundAndNotifications()

                // Check if connected, if not show connection Activity
                if (!serviceConnection.hasActiveConnections) {
                    serviceConnection.displayConnectionActivity()
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()

                Toast.makeText(
                    this@MainActivity,
                    throwable.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun didConnectAndInit(device: NIMMSTADevice) {
        // this is the point in time when you can start interacting with your device.
    }

    override fun didScanBarcode(device: NIMMSTADevice, barcode: Barcode, event: ScanEvent) {
        super.didScanBarcode(device, barcode, event)
    }

    override fun didTouch(
        device: NIMMSTADevice,
        x: Double,
        y: Double,
        screen: Int,
        event: TouchEvent
    ) {
        super.didTouch(device, x, y, screen, event)
    }

    override fun batteryLevelChanged(device: NIMMSTADevice, newBatteryLevel: Int) {
        super.batteryLevelChanged(device, newBatteryLevel)
    }

    override fun onDestroy() {
        super.onDestroy()

        serviceConnection.close()
    }
}
