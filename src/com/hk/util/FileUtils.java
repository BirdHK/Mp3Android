package com.hk.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.hk.entity.Mp3Info;

import android.os.Environment;

/*�ļ����صĹ����࣬��װ��һЩ��صĳ���*/
public class FileUtils {
	private String SDPATH;
	public String getSDPATH(){
		return SDPATH;
	}
	//��ȡSD��λ��
	public FileUtils() {
		SDPATH = Environment.getExternalStorageDirectory()+"/";
	}
	
	//��SD�������ļ�
	public File CreateSDFile(String ddr,String fileName) throws IOException{
		File file = new File(SDPATH+ddr+"/"+fileName);
		file.createNewFile();
		return file;
	}
	
	//��SD������Ŀ¼
	public File CreateSDDir(String dirName){
		File dir = new File(SDPATH+dirName);
		dir.mkdir();
		return dir;
	}
	
	//�ж��ļ��Ƿ����
	public boolean isFileExist(String ddr,String fileName){
		File file = new File(SDPATH+ddr+"/"+fileName);
		return file.exists();
	}
	
	//��һ��inputStream�е�����д�뵽SD��
	public File write2SDFromInput(String path, String fileName, InputStream input){
		File file = null;
		OutputStream out = null;
		try 
		{
			CreateSDDir(path);
			file = CreateSDFile(path,fileName);
			out = new FileOutputStream(file);
			byte buffer[] = new byte[4*1024];
			int temp=0;
			while((temp=input.read(buffer)) != -1){
				out.write(buffer,0,temp);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		return file;
	}
	
	//��ȡsdcard��mp3�ļ�
	public List<Mp3Info> getMp3Files(String path){
		List<Mp3Info> mp3Infos = new ArrayList<Mp3Info>();
		
		File file = new File(SDPATH+File.separator+path);
		File []files = file.listFiles();
		for(int i=0; i<files.length; i++){
			if(files[i].getName().endsWith("mp3")){
				Mp3Info mp3Info = new Mp3Info();
				mp3Info.setMp3Name(files[i].getName());
				mp3Info.setMp3Size(files[i].length()+"");
				mp3Infos.add(mp3Info);
			}
		}
		return mp3Infos;
	}
}
