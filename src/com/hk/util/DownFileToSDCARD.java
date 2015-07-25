package com.hk.util;

import android.os.AsyncTask;

public class DownFileToSDCARD extends AsyncTask<String, Integer, Integer>{

	@Override
	protected Integer doInBackground(String... params) {
		HttpDownloader loader = new HttpDownloader();
		int flag = loader.downFile(params[0], params[1], params[2]);
		System.out.println(flag);
		return flag;
	}
}
