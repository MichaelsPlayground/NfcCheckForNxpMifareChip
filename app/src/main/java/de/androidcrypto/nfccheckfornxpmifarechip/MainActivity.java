package de.androidcrypto.nfccheckfornxpmifarechip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.FeatureInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button checkButton = findViewById(R.id.checkButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean nxpChipAvailable = deviceSupportsMifareClassic(view.getContext());
                String messageTrue = "Congratulations ! Your device IS supporting Mifare Ultralight and/or Mifare Classic NFC tags.";
                String messageFalse = "I'm sorry, but your device is NOT supporting Mifare Ultralight and/or Mifare Classic NFC tags.";
                TextView textViewTrue = findViewById(R.id.textViewTrue);
                TextView textViewFalse = findViewById(R.id.textViewFalse);
                if (nxpChipAvailable) {
                    textViewTrue.setText(messageTrue);
                    textViewFalse.setVisibility(View.INVISIBLE);
                } else {
                    textViewTrue.setVisibility(View.INVISIBLE);
                    textViewFalse.setText(messageFalse);
                    textViewFalse.setVisibility(View.VISIBLE);
                }
                System.out.println("*** result: " + nxpChipAvailable);
            }
        });
    }

    public boolean deviceSupportsMifareClassic(Context context) {
        FeatureInfo[] info = context.getPackageManager().getSystemAvailableFeatures();
        for (FeatureInfo i : info) {
            String name = i.name;
            if (name != null && name.equals("com.nxp.mifare")) {
                return true;
            }
        }
        return false;
    }
}