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

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by ASUS-PC on 2017/12/18.
 */

public class deletestu extends Activity{
   private Button bt_delete;
    private EditText edit_studyid ;
    private DatagramSocket ds;
    private  DatagramPacket dp;
    private TextView del_text;
    private  Handler myhandler;
    private  Message message;
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletelayout);
        bt_delete = findViewById(R.id.bt_delete);
        edit_studyid = findViewById(R.id.edit_studyid);
        del_text = findViewById(R.id.del_text);

        myhandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                String text = (String) msg.obj;
                del_text.setText(text);
                Log.e("tag","222222222222");
            }
        };
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Thread net_thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //发送请求
                                ds = new DatagramSocket();
                                String msg = "delete,"+edit_studyid.getText().toString();
                                byte[] buf = msg.getBytes();
                                dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.43.237"), 10001);
                                Log.e("tag","准备发送");
                                ds.send(dp);
                                Log.e("tag","发送完成！");
                                ds.close();

                                //接收响应
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
                net_thread.start();
            }
        });

    }
}
