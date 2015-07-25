package com.hk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


//使用HTTP协议下载文件
public class HttpDownloader {
	private URL url=null;
	
	/*
	 * 根据HTTP协议下载文件，该文件对应一切文本文件
	 * 1、创建一个URL对象，通过new URL(String);
	 * 2、通过URL对象得到HttpURLConnection对象
	 * 3、通过HttpURLConnection对象得带一个inputStream
	 * 4、通过将inputStream对象转为BufferedReader
	 * 5、使用BufferedReader的readLine方法获取每一行的数据
	 * */
	public String download(String urlStr)
	{
		System.out.println("进入文本下载函数");
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		try {
					
			url = new URL(urlStr);
			System.out.println(url.toString());
			
			//此处可能出错的原因是 没有使用 主程序中使用了main线程,而不是自定义的线程
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
	
	/*下载任意文件到SD卡的程序
	 * 该函数返回整型值：
	 * 返回-1，说明文件下载失败
	 * 返回0，说明文件下载成功
	 * 返回1，说明文件已经存在
	 *  */
	
	//urlStr 链接地址 path 目录名字 name 文件名字
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
