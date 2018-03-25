package com.example.asus_pc.sqldemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    private Button bt;
    private EditText n_edit;
    private EditText pw_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button)findViewById(R.id.denglu_bt);
        n_edit = (EditText)findViewById(R.id.name_edit);
        pw_edit = (EditText)findViewById(R.id.pw_edit);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(n_edit.getText().toString().equals("B15070429")&&pw_edit.getText().toString().equals("B15070429"))
                {
                    Intent i = new Intent(MainActivity.this,faction.class);
                    startActivity(i);
                }

            }
        });
    }
}
