package com.example.asus_pc.sqldemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by ASUS-PC on 2017/12/17.
 */

public class add extends Activity{

    private EditText edit_name;
    private EditText edit_studyid;
    private EditText edit_age;
    private EditText edit_sex;
    private EditText edit_address;
    private EditText edit_fromwhere;
    private EditText edit_class;
    private EditText edit_grade;
    private EditText edit_fdyname;
    private EditText edit_email;
    private EditText edit_phonenum;
    private EditText edit_birthday;
    private EditText edit_college;
    private Button bt_add;
    private DatagramSocket ds;

    private Handler myhandler;
    private Message message;
    private TextView tx;

    @Override
    protected  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addlayout);
        edit_name = findViewById(R.id.edit_name);
        edit_studyid = findViewById(R.id.edit_studyid);
        edit_address = findViewById(R.id.edit_address);
        edit_age = findViewById(R.id.edit_age);
        edit_birthday = findViewById(R.id.edit_birthday);
        edit_class = findViewById(R.id.edit_class);
        edit_college = findViewById(R.id.edit_college);
        edit_email = findViewById(R.id.edit_email);
        edit_fdyname = findViewById(R.id.edit_fdyname);
        edit_fromwhere = findViewById(R.id.edit_fromwhere);
        edit_grade = findViewById(R.id.edit_grade);
        edit_phonenum = findViewById(R.id.edit_phonenum);
        edit_sex = findViewById(R.id.edit_sex);
        bt_add = findViewById(R.id.bt_addstu);
        tx = findViewById(R.id.text_getmsg);

        myhandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                String text = (String) msg.obj;
                tx.setText(text);
            }
        };

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread sendmsg = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ds = new DatagramSocket();
                            String msg = "addstu,"+
                                    edit_name.getText().toString()+","+
                                    edit_studyid.getText().toString()+","+
                                    edit_age.getText().toString()+","+
                                    edit_sex.getText().toString()+","+
                                    edit_address.getText().toString()+","+
                                    edit_fromwhere.getText().toString()+","+
                                    edit_class.getText().toString()+","+
                                    edit_grade.getText().toString()+","+
                                    edit_fdyname.getText().toString()+","+
                                    edit_email.getText().toString()+","+
                                    edit_phonenum.getText().toString()+","+
                                    edit_birthday.getText().toString()+","+
                                    edit_college.getText().toString();
                            byte[] buf = msg.getBytes();
                            DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.43.237"), 10001);

                            ds.send(dp);
                            Log.e("tag","发送完成！");
                            ds.close();

                            ds = new DatagramSocket(10001);
                            buf = new byte[1024];
                            dp = new DatagramPacket(buf,buf.length);
                            ds.receive(dp);
                            String text = new String(dp.getData(),0,dp.getLength());
                            message = myhandler.obtainMessage();
                            message.obj = text;
                            myhandler.sendMessage(message);
                            ds.close();
                        } catch (SocketException e) {
                            e.printStackTrace();
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                sendmsg.start();
            }
        });

    }


}
