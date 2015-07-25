package com.hk.mp3;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Resources res = getResources();
		//�õ�TabHost�����йص�TabActivity�Ĳ�����ͨ���ö���ʵ��
		TabHost tabHost = getTabHost();
		//����һ��Intent����ָ��һ��activity
		
		Intent localIntent  = new Intent();
		localIntent.setClass(this, LocalListActivity.class);
		TabHost.TabSpec localSpec = tabHost.newTabSpec("Local");
		localSpec.setIndicator("Local", res.getDrawable(android.R.drawable.stat_sys_upload_done));
		localSpec.setContent(localIntent);
		tabHost.addTab(localSpec);	
		
		
		Intent remoteIntent = new Intent();
		remoteIntent.setClass(this, RemoteListActivity.class);
		TabHost.TabSpec remoteSpec = tabHost.newTabSpec("Remote");
		remoteSpec.setIndicator("Remote", res.getDrawable(android.R.drawable.stat_sys_download));
		//�����ҳ�����������
		remoteSpec.setContent(remoteIntent);
		tabHost.addTab(remoteSpec);
		
		
		
	}
}
