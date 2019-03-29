package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

import general.Constants;


public class PracticalTest01Var02PlayActivity extends AppCompatActivity {

    private class SomeButtonListener implements View.OnClickListener {

        public void onClick(View view) {

            if (view.getId() == R.id.generate_button) {
                int value = r.nextInt(5);
                Log.d("DEBUG:", Integer.toString(value));
                guess_text.setText(Integer.toString(value));
            }

            if (view.getId() == R.id.back_button) {
                setResult(1);
                finish();
            }
            if (view.getId() == R.id.check_button) {
                int guess = Integer.parseInt(guess_text.getText().toString());
                int received = received_number;
                if (guess == received) {
                    int last_value = Integer.parseInt(score_text.getText().toString());
                    last_value++;
                    score_text.setText(Integer.toString(last_value));
                }
            }
        }
    }


    private Button generate_button = null;
    private Button back_button = null;
    private Button check_button = null;
    private TextView guess_text = null;
    private TextView score_text = null;
    private int received_number = -1;
    Random r = new Random();
    private SomeButtonListener listener = new SomeButtonListener();
    private StartedServiceBroadcastReceiver startedServiceBroadcastReceiver;
    private IntentFilter startedServiceIntentFilter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_play);

        Intent receivedIntent = getIntent();

        generate_button = (Button)findViewById(R.id.generate_button);
        back_button = (Button)findViewById(R.id.back_button);
        guess_text = (TextView)findViewById(R.id.guess_text);
        check_button = (Button)findViewById(R.id.check_button);
        score_text = (TextView)findViewById(R.id.score_text);

        check_button.setOnClickListener(listener);
        generate_button.setOnClickListener(listener);
        back_button.setOnClickListener(listener);

        if (receivedIntent.getExtras().containsKey("ro.pub.cs.systems.eim.practicaltest01.VALUE")) {
            int value = receivedIntent.getIntExtra("ro.pub.cs.systems.eim.practicaltest01.VALUE", -1);
            Log.d("DEBUG:", Integer.toString(value));
            received_number = value;
        }


        startedServiceBroadcastReceiver = new StartedServiceBroadcastReceiver(guess_text);

        startedServiceIntentFilter = new IntentFilter();
        startedServiceIntentFilter.addAction("SERVICE_VALUE");


    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(startedServiceBroadcastReceiver);

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("ro.pub.cs.systems.eim.practicaltest01var06", "ro.pub.cs.systems.eim.practicaltest01var06.PracticalTest01Var06Service"));
        stopService(intent);

        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("VALUE.GUESS", guess_text.getText().toString());
        savedInstanceState.putString("VALUE.SCORE", score_text.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("VALUE.GUESS")) {
            guess_text.setText(savedInstanceState.getString("VALUE.GUESS"));
        }
        if (savedInstanceState.containsKey("VALUE.SCORE")) {
            score_text.setText(savedInstanceState.getString("VALUE.SCORE"));
        }

    }
}
