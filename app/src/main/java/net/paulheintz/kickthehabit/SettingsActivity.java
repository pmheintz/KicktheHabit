package net.paulheintz.kickthehabit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    // Temporary
    Smoker mySmoker = new Smoker();
    private LinearLayout parentLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //parentLinearLayout = (LinearLayout)findViewById(R.id.reasons_layout);

        // Set default TextView values
        final NumberFormat NF = NumberFormat.getCurrencyInstance();
        final TextView cigsPerPackTV = (TextView)findViewById(R.id.cigs_per_pack_value);
        cigsPerPackTV.setText(Integer.toString(mySmoker.getCigsPerPack()));
        final TextView pricePerPackTV = (TextView)findViewById(R.id.price_per_pack_value);
        pricePerPackTV.setText(NF.format(mySmoker.getPricePerPack()));
        final TextView pricePerCigTV = (TextView)findViewById(R.id.price_per_cig_value);
        pricePerCigTV.setText(NF.format(mySmoker.getPricePerCig()));

        // On focus changed listeners to update price per cig
        cigsPerPackTV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // Update object variable
                    mySmoker.setCigsPerPack(Integer.parseInt(cigsPerPackTV.getText().toString()));
                    mySmoker.setPricePerCig();
                    // Update field
                    pricePerCigTV.setText(NF.format(mySmoker.getPricePerCig()));
                }
            }
        });

        pricePerPackTV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    try {
                        mySmoker.setPricePerPack(Double.parseDouble(pricePerPackTV.getText().toString()));
                    } catch (NumberFormatException e) {
                        try {
                            Number tempPricePerPack = NF.parse(pricePerPackTV.getText().toString());
                            mySmoker.setPricePerPack((Double)tempPricePerPack);
                        } catch (ParseException ex) {
                            e.printStackTrace();
                        }
                    }

                    // Update object variable
                    mySmoker.setPricePerCig();
                    // Update fields
                    pricePerPackTV.setText(NF.format(mySmoker.getPricePerPack()));
                    pricePerCigTV.setText(NF.format(mySmoker.getPricePerCig()));
                }
            }
        });

        // On check changed listener to determine if data should be entered or collected
        CheckBox gatherData = (CheckBox)findViewById(R.id.gather_data_chk);
        gatherData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                View smokerInfo = (View)findViewById(R.id.smoker_info);
                if (isChecked) {
                    smokerInfo.setVisibility(View.GONE);
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                } else {
                    smokerInfo.setVisibility(View.VISIBLE);
                }
            }
        });

        TextView reasonsBtn = (TextView)findViewById(R.id.reasons_button);
        reasonsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ReasonsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArray("reasonsArray", mySmoker.getReasonsToQuit());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
