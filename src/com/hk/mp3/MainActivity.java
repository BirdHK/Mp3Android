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
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Resources res = getResources();
		//得到TabHost对象，有关的TabActivity的操作都通过该对象实现
		TabHost tabHost = getTabHost();
		//生成一个Intent对象，指向一个activity
		
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
		//向这个页里面添加内容
		remoteSpec.setContent(remoteIntent);
		tabHost.addTab(remoteSpec);
		
		
		
	}
}
