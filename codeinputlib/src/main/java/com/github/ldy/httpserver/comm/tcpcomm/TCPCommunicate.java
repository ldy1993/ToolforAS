package com.github.ldy.httpserver.comm.tcpcomm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import me.jerry.framework.comm.CommEntity;
import me.jerry.framework.comm.INetProcess;
import me.jerry.framework.comm.NetException;

public class TCPCommunicate implements INetProcess {

	@Override
	public boolean innerCache() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean innerRetry() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public byte[] process(CommEntity commEntity) throws NetException {
		final int PORT = 7788;  
        Socket s = null;  
        OutputStream out = null;  
        InputStream in = null;  
        DataOutputStream dos = null;  
        DataInputStream dis = null;  
        String serverHost = "192.168.1.130";  
        byte[] b = new byte[1024];  
//        StringBuilder sb = new StringBuilder();
//        sb.append("?");
//        Map<String, Object> params = commEntity.getRequestBean().params;
//        if(params != null) {
//            Set<String> keys = params.keySet();
//            for(String key : keys) {
//                try {
//                    sb.append(URLEncoder.encode(key, "UTF-8")).append("=").append(URLEncoder.encode(params.get(key).toString(), "UTF-8")).append("&");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        try {
            /** 
             * 客户端通过IP和端口和服务器连接，连接上服务端后 
             * 就可以像服务端输出消息和接受服务端返回的消息 
             * 通过IP就能找到一台独一无二的电脑终端，通过PORT找到 
             * 终端的某一个独立的应用程序 
             */
            s = new Socket(serverHost, PORT);  
            out = s.getOutputStream();  
            dos = new DataOutputStream(out);  
            dos.write("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890".getBytes());  
            in = s.getInputStream();  
            dis = new DataInputStream(in);  
            dis.read(b);  
            String serverToClient = new String(b);  
                System.out.println("服务端返回到客户端的信息:"+serverToClient);  
        } catch (UnknownHostException e) {
            System.out.println("Host未知。。。");  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {
            try {
                out.close();  
                in.close();  
                dos.close();  
                dos.close();  
                s.close();  
            } catch (IOException e) {
                e.printStackTrace();  
            }  
        }  
     

		return b;
	}

}
