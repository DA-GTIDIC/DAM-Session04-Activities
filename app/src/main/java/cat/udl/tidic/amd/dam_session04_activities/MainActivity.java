package cat.udl.tidic.amd.dam_session04_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import cat.udl.tidic.amd.dam_session04_activities.models.UserModel;

public class MainActivity extends AppCompatActivity {

    String TAG = this.getClass().getName();

    private TextView bioTextView;
    private EditText fullNameEditText;
    private DatePicker birthdayDatePicker;
    private Button updateButton;
    private Button resetButton;
    private UserModel u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Associating the activity with the view");
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Linking views to the activity");
        bioTextView = findViewById(R.id.textView_bio);
        fullNameEditText = findViewById(R.id.editText_fullName);
        birthdayDatePicker = findViewById(R.id.datePicker_birthday);
        updateButton = findViewById(R.id.button_update);
        resetButton = findViewById(R.id.button_clear);

        reset();
    }

    private void reset(){
        bioTextView.setText("");
        fullNameEditText.setText("");

        Calendar c = Calendar.getInstance();
        birthdayDatePicker.updateDate(c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Log.d(TAG, "Here we need to launch settings...");
            return true;
        }
        if (id == R.id.about) {
            Log.d(TAG, "Here we need to launch about us...");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fullNameEditText.getText().toString();

                Calendar c = Calendar.getInstance();
                c.set(birthdayDatePicker.getYear(), birthdayDatePicker.getMonth(),
                        birthdayDatePicker.getDayOfMonth());
                Date birthday = new Date(c.getTimeInMillis());
                u = new UserModel(name,birthday);

                Log.d(TAG, "Starting a new activity");
                Intent intent = new Intent(getApplicationContext(), VerificationActivity.class);
                intent.putExtra("name", name);
                startActivityForResult(intent, 1234);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });



    }

    @Override protected void onActivityResult(int requestCode, int resultCode,
                                              Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1234) {
            assert data.getExtras() != null;
            String decision = data.getExtras().getString("decision");
            assert decision != null;
            Log.d(TAG,decision);
            if (decision.equals("accepted")){
                bioTextView.setText(u.toString());
            }else{
                reset();
            }
        }
    }

}
