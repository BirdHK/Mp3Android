package com.hk.mp3;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.hk.entity.Mp3Info;
import com.hk.service.Mp3Service;
import com.hk.util.DownTxtFile;
import com.hk.xml.Mp3ListContentHandler;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class RemoteListActivity extends ListActivity{

	private final static int UPDATA = 1;
	private final static int ABOUT = 2;
	private String xml = null;
	private List<Mp3Info> mp3Infos = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.remote);
		updataListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, UPDATA, 1, "更新列表");
		menu.add(0, ABOUT, 2, "关于");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		
		if (id == UPDATA) {
			
			updataListView();
			
		}else if(id==ABOUT){
			
			Toast.makeText(RemoteListActivity.this,"关于本软件的那些事", Toast.LENGTH_LONG).show();
		}
		return super.onOptionsItemSelected(item);
	}

	private List<Mp3Info> parse(String xmlStr){
		SAXParserFactory factory = SAXParserFactory.newInstance();
		List<Mp3Info> infos = null;
		try {
			XMLReader reader = factory.newSAXParser().getXMLReader();
			infos = new ArrayList<Mp3Info>();
			Mp3ListContentHandler handler = new Mp3ListContentHandler(infos);
			reader.setContentHandler(handler);
			reader.parse(new InputSource(new StringReader(xmlStr)));
			for (Iterator<Mp3Info> iterator = infos.iterator(); iterator.hasNext();) {
				Mp3Info mp3Info = (Mp3Info) iterator.next();
				System.out.println(mp3Info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return infos;
	}

	private SimpleAdapter  buildSimpleAdapter(List<Mp3Info> mp3Infos){
		//生成一个list对象，并实现simpleAdapter,将MP3Infos的对象放入list
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		for (Iterator<Mp3Info> iterator = mp3Infos.iterator(); iterator.hasNext();) 
		{
			Mp3Info mp3Info = (Mp3Info) iterator.next();
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("mp3_name",mp3Info.getMp3Name());
			map.put("mp3_size",mp3Info.getMp3Size());
			list.add(map);
		}
		
		/*创建一个simpleAdpter对象，将这个对象设置到ListActivity中
		 * context:上下文
		 * data：数据源,List<Map<String,*>>一个Map组成的List集合,一个map对应一行listview
		 * resource: item对应的xml布局文件
		 * from:Map中的键名
		 * to:绑定数据视图的中的id，与from成对应关系
		 */		
		
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.mp3info_item,
			                   new String[]{"mp3_name","mp3_size"},	new int[]{R.id.mp3_name,R.id.mp3_size});
		return simpleAdapter;
	}
	
	private void updataListView(){
		try {
			//通过 下载包文件 下载xml文件
			xml = new DownTxtFile().execute("http://10.4.102.10:8888/examples/resource.xml").get();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		//将解析xml的结果放入MP3Info对象中
		mp3Infos = parse(xml);
		SimpleAdapter simpleAdapter = buildSimpleAdapter(mp3Infos);
		setListAdapter(simpleAdapter);	
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//根据点击的位置得到相应的MP3info对象
		Mp3Info mp3Info = mp3Infos.get(position);
		System.out.println("mp3Info--------->"+mp3Info);
		Intent intent = new Intent();
		//将mp3Info对象存入到intent中;
		intent.putExtra("mp3Info", mp3Info);
		intent.setClass(this, Mp3Service.class);
		startService(intent);
		super.onListItemClick(l, v, position, id);
	}

}
