package com.hk.util;

import android.os.AsyncTask;

public class DownTxtFile extends AsyncTask<String, Integer, String> {
	@Override
	public String doInBackground(String... params) {
		HttpDownloader loader = new HttpDownloader();
		String result = loader.download(params[0]);
		System.out.println("xml------>"+result);
		return result;
	}	
}
