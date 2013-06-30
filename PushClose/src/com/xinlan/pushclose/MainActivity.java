package com.xinlan.pushclose;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	public PushClose mPushClose;
	private ListView mListView;
	private ArrayList<String> list;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPushClose = (PushClose)findViewById(R.id.pushClose);
        View bottomView = LayoutInflater.from(this).
        		inflate(R.layout.date, null);
        View topView = LayoutInflater.from(this).
        		inflate(R.layout.dialy, null);
        mPushClose.setContent(topView, bottomView);	
        initData();
        mListView = (ListView)topView.findViewById(R.id.date_list);
        mListView.setAdapter(new ContentAdapter());
    }
    
    private void initData(){
    	list = new ArrayList<String>();
    	list.add("1");
    	list.add("2");
    	list.add("3");
    	list.add("4");
    	list.add("5");
    	list.add("6");
    	list.add("7");
    	list.add("8");
    	list.add("9");
    	list.add("10");
    	list.add("11");
    	list.add("12");
    	list.add("13");
    	list.add("14");
    	list.add("15");
    	list.add("16");
    	list.add("17");
    	list.add("18");
    	list.add("19");
    	list.add("20");
    }
    
    
    private final class ContentAdapter extends BaseAdapter{
    	LayoutInflater mInflater = LayoutInflater.from(MainActivity.this);

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				convertView = mInflater.inflate(R.layout.item, null);
			}
			Button btn =(Button) convertView.findViewById(R.id.btn_content);
			btn.setText(list.get(position));
			return convertView;
		}
    	
    }//end  inner class
}//end class
