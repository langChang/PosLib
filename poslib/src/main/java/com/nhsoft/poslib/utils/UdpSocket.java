package com.nhsoft.poslib.utils;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.nhsoft.poslib.libconfig.LibConfig;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class UdpSocket {
    static final String BROADCAST_IP = "192.168.4.177";

    private static       String          TAG           = "UdpSocket";
    private static final int             POOL_SIZE     = 5;
    private static final int             BUFFER_LENGTH = 1024*1024;
    private              byte[]          receiveByte   = new byte[BUFFER_LENGTH];
    private final        ExecutorService mThreadPool;
    Context mContent;
    private      DatagramSocket client;
    static final int            CLIENT_PORT = 4000;
    private      DatagramPacket receivePacket;
    boolean isThreadRunning = false;
    Thread  clientThread;

    public UdpSocket(Context mContent) {
        this.mContent = mContent;
        int cpuNumbers = Runtime.getRuntime().availableProcessors();
        // 根据CPU数目初始化线程池
        mThreadPool = Executors.newFixedThreadPool(cpuNumbers * POOL_SIZE);
        // 记录创建对象时的时间
    }

    public void startUDPSocket() {
        if (client != null) return;
        try {
            client = new DatagramSocket(CLIENT_PORT);
            if (receivePacket == null) {
                // 创建接受数据的 packet
                receivePacket = new DatagramPacket(receiveByte, BUFFER_LENGTH);
            }
            startSocketThread();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private void startSocketThread() {
        clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                printMessage("clientThread is running...");
                receiveMessage();
            }
        });
        isThreadRunning = true;
        clientThread.start();
    }

    /**
     * 处理接受到的消息
     */
    private void receiveMessage() {
        while (isThreadRunning) {
            try {
                if (client != null) {
                    client.receive(receivePacket);
                }
                printMessage("message success...");
            } catch (IOException e) {
                Log.e(TAG, "UDP数据包接收失败！stopUDPSocket");
                stopUDPSocket();
                e.printStackTrace();
                return;
            }

            if (receivePacket == null || receivePacket.getLength() == 0) {
                Log.e(TAG, "无法接收UDP数据或者接收到的UDP数据为空");
                continue;
            }

            String strReceive = new String(receivePacket.getData(), 0, receivePacket.getLength());
//            printMessage(" data:  " + strReceive + " from " + receivePacket.getAddress().getHostAddress() + ":" + receivePacket.getPort());
            printMessage( strReceive);

            //解析接收到的 json 信息

            // 每次接收完UDP数据后，重置长度。否则可能会导致下次收到数据包被截断。
            if (receivePacket != null) {
                receivePacket.setLength(BUFFER_LENGTH);
            }
        }
    }

    public void printMessage(String s) {
        Log.d(TAG, s);
        Intent intent = new Intent();
        intent.setAction(LibConfig.REFRESH_ACTION);
        intent.putExtra("Message", s);
        mContent.sendBroadcast(intent);
    }

    //发送消息
    public void sendMessage(final byte[] message) {
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    DatagramPacket sendPacket = new DatagramPacket(message, message.length,
                            InetAddress.getByName(BROADCAST_IP), CLIENT_PORT);
                    client.send(sendPacket);

                    // 数据发送事件
                    printMessage("数据发送成功");

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void stopUDPSocket() {
        isThreadRunning = false;
        receivePacket = null;
        if (clientThread != null) {
            clientThread.interrupt();
        }
        if (client != null) {
            client.close();
            client = null;
        }

    }

}
