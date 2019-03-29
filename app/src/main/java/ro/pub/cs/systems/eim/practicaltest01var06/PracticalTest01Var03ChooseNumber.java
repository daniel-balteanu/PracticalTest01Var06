package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import general.Constants;

public class PracticalTest01Var03ChooseNumber extends AppCompatActivity {

    private class MyButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            Button button = (Button)view;

            if (view.getId() == R.id.play_button) {
                Log.d("DEBUG:", "Play button pressed");
                if (number_text.getText() != null && number_text.getText().toString().length() > 0) {
                    int value = Integer.parseInt(number_text.getText().toString());
                    if (value != -1) {
                        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02PlayActivity.class);
                        intent.putExtra("ro.pub.cs.systems.eim.practicaltest01.VALUE", value);
                        startActivityForResult(intent, Constants.SECOND_REQ_CODE);
                    }
                }



            }
        }
    }


    private EditText number_text = null;
    private Button play_button = null;
    private MyButtonListener listener = new MyButtonListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_choose_number);

        number_text = (EditText)findViewById(R.id.number_text);
        play_button = (Button)findViewById(R.id.play_button);
        play_button.setOnClickListener(listener);
    }
}
