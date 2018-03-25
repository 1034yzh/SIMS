package com.example.asus_pc.sqldemo;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by ASUS-PC on 2017/12/11.
 */

public class find extends Activity {
   private Button namefind_bt;
    private Button idfind_bt;
    private EditText fn_edit;
    private TextView tx;
    private Handler myhandler;
    private  Message message;
    private  Boolean stopThread = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findlayout);
        fn_edit = findViewById(R.id.fn_edit);
        namefind_bt = findViewById(R.id.namefind_bt);
        idfind_bt = findViewById(R.id.idfind_bt);
        tx = findViewById(R.id.text);
        myhandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                String text = (String) msg.obj;
                tx.setText(text);
                Log.e("tag","222222222222");
            }
        };

        Thread  receive = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DatagramSocket ds = new DatagramSocket(10001);
                    while (!stopThread) {
                        byte[] buf = new byte[1024];
                        DatagramPacket dp = new DatagramPacket(buf, buf.length);
                        try {
                            ds.receive(dp);
                            String text = new String(dp.getData(), 0, dp.getLength());
                            Log.e("tag",text);
                            message = myhandler.obtainMessage();
                            message.obj = text;
                            myhandler.sendMessage(message);
                        } catch (SocketException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    ds.close();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
            }
        });
        receive.start();

        namefind_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread sendmsg = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            DatagramSocket ds = new DatagramSocket();
                            String msg = "name_find,"+fn_edit.getText().toString();
                            byte[] buf = msg.getBytes();
                            DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.43.237"), 10001);
                            ds.send(dp);
                            Log.e("tag","发送完成！");
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
        idfind_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread sendmsg = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            DatagramSocket ds = new DatagramSocket();
                            String msg = "studyid_find,"+fn_edit.getText().toString();
                            byte[] buf = msg.getBytes();
                            DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.43.237"), 10001);
                            ds.send(dp);
                            Log.e("tag","发送完成！");
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
    @Override
    public void onDestroy()
    {
        Log.e("tag","----------onDestroy-----------");
        stopThread = true;
        Thread send_destroy = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DatagramSocket ds = new DatagramSocket();
                    byte[] buf = "destroy".getBytes();
                    DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), 10001);
                    ds.send(dp);
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        send_destroy.start();
        super.onDestroy();

    }
}
