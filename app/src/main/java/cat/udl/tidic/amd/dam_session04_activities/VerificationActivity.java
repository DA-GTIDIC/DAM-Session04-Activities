package cat.udl.tidic.amd.dam_session04_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VerificationActivity extends AppCompatActivity {

    private String decision="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        Button acceptButton = findViewById(R.id.button_accept);
        Button rejectButton = findViewById(R.id.button_reject);
        TextView termsLabel = findViewById(R.id.TextView_label_terms);

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        String name = extras.getString("name");

        String terms = "Dear " +  name + " do you accept the terms and conditions of the app: " +
                getString(R.string.app_name) + "?";

        termsLabel.setText(terms);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decision="accepted";
                _finish();
            }
        });

        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decision="rejected";
                _finish();
            }
        });

    }

    private void _finish(){
        Intent i = new Intent();
        i.putExtra("decision",decision);
        setResult(1234,i);
        finish();
    }


}
