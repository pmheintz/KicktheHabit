package net.paulheintz.kickthehabit;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ReasonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reasons);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final Button addReasonBtn = (Button)findViewById(R.id.add_reason_button);

        final ArrayList<String> reasons = convertBundleArrayToArrayList(bundle);

        if (reasons.size() < 10) {
            addReasonBtn.setVisibility(View.VISIBLE);
        }

        final ReasonsListAdapter adapter = new ReasonsListAdapter(reasons, this);
        final ListView listView = (ListView)findViewById(R.id.reasons_layout);
        listView.setAdapter(adapter);

        // Cancel button on click listener
        Button cancelBtn = (Button)findViewById(R.id.cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Add reason on click listener
        addReasonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reasons.add("");
                if (reasons.size() >= 10) {addReasonBtn.setVisibility(View.GONE);}
                listView.setAdapter(adapter);
            }
        });
    }

    private ArrayList<String> convertBundleArrayToArrayList(Bundle bundle) {
        String[] arr = bundle.getStringArray("reasonsArray");
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && !arr[i].trim().isEmpty()) {
                list.add(arr[i]);
            }
        }
        return list;
    }
}