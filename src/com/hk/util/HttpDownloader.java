package com.hk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


//ʹ��HTTPЭ�������ļ�
public class HttpDownloader {
	private URL url=null;
	
	/*
	 * ����HTTPЭ�������ļ������ļ���Ӧһ���ı��ļ�
	 * 1������һ��URL����ͨ��new URL(String);
	 * 2��ͨ��URL����õ�HttpURLConnection����
	 * 3��ͨ��HttpURLConnection����ô�һ��inputStream
	 * 4��ͨ����inputStream����תΪBufferedReader
	 * 5��ʹ��BufferedReader��readLine������ȡÿһ�е�����
	 * */
	public String download(String urlStr)
	{
		System.out.println("�����ı����غ���");
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		try {
					
			url = new URL(urlStr);
			System.out.println(url.toString());
			
			//�˴����ܳ����ԭ���� û��ʹ�� ��������ʹ����main�߳�,�������Զ�����߳�
			HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
		
			buffer = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));

			while((line=buffer.readLine())!=null){
				sb.append(line);
			}	
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	/*���������ļ���SD���ĳ���
	 * �ú�����������ֵ��
	 * ����-1��˵���ļ�����ʧ��
	 * ����0��˵���ļ����سɹ�
	 * ����1��˵���ļ��Ѿ�����
	 *  */
	
	//urlStr ���ӵ�ַ path Ŀ¼���� name �ļ�����
	public int downFile(String urlStr, String path, String name){
		InputStream inputStream = null;
		try {
			FileUtils fileUtils = new FileUtils();
			if(fileUtils.isFileExist(path,name)){
				return 1;
			}else{
				inputStream = getInputStreamFromURL(urlStr);
				File resultFile = fileUtils.write2SDFromInput(path, name, inputStream);
				if(resultFile==null){
					return -1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
	
	public InputStream getInputStreamFromURL(String urlStr) throws Exception{
		url = new URL(urlStr);
		HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
		InputStream inputStream = urlCon.getInputStream();
		return inputStream;
	}
}
