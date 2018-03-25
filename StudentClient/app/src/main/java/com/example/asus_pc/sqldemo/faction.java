package com.example.asus_pc.sqldemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by ASUS-PC on 2017/12/11.
 */

public class faction extends Activity{
    private Button a_bt;
    private Button d_bt;
    private Button u_bt;
    private Button f_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.factionlayout);
        a_bt = findViewById(R.id.a_bt);
        d_bt = findViewById(R.id.d_bt);
        u_bt = findViewById(R.id.u_bt);
        f_bt = findViewById(R.id.f_bt);
        f_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent find =new Intent(faction.this,find.class);
                startActivity(find);
            }
        });
        a_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(faction.this,add.class);
                startActivity(add);
            }
        });
        u_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent update = new Intent(faction.this, update.class);
                startActivity(update);
            }
        });
        d_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent delete = new Intent(faction.this,deletestu.class);
                startActivity(delete);
            }
        });
    }

}
