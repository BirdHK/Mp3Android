package com.hk.service;

import com.hk.entity.Mp3Info;
import com.hk.util.DownFileToSDCARD;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class Mp3Service extends Service {

	private Mp3Info mp3Info = null;
	private String status = null;
	private int flag = 2;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		mp3Info = (Mp3Info) intent.getSerializableExtra("mp3Info");
		System.out.println("service------->"+mp3Info);
		String mp3Url = "http://10.4.102.10:8888/examples/"+mp3Info.getMp3Name();
		System.out.println(mp3Url);
	    try {
			 flag = new DownFileToSDCARD().execute(new String[]{mp3Url,"Mp3Player",mp3Info.getMp3Name()}).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    switch(flag){
	    case 0:
	    	status = "�ļ����سɹ�";
	    	break;
	    case 1:
	    	status ="�ļ��Ѵ���";
	    	break;
	    case -1:
	    	status ="�ļ�����ʧ��";
	    	break;
	    }
	    System.out.println("status---->"+status);
	    //notification��ʾ�û�����״̬
	    Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
		return super.onStartCommand(intent, flags, startId);
	}

}
