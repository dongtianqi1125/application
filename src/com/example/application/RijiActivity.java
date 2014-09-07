package com.example.application;



import java.util.List;

import com.example.dao.DairynoteDao;
import com.example.domin.Dnote;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
public class RijiActivity extends Activity {
 private DairynoteDao dbDao = new DairynoteDao(RijiActivity.this);
  private ListView lv;
  private List<Dnote> Dnots;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riji);
        dbDisplay();
    }

    public void dbDisplay(){
    	Dnots = dbDao.findAll();
        lv = (ListView) findViewById(R.id.diarieslist);
        lv.setAdapter(new MyAdapter());
        lv.setOnItemClickListener(new ItemClickListener());
    }
    private final class ItemClickListener implements OnItemClickListener{
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {	
			ListView iView = (ListView)parent;
			Dnote dnote = (Dnote)iView.getItemAtPosition(position);		
			Intent it = new Intent(RijiActivity.this,Displaynote.class);
			 it.putExtra("name", dnote.getTitle());
			 startActivity(it);	 
		}
    	
    }
    private class MyAdapter extends BaseAdapter{

		public int getCount() {
			// TODO Auto-generated method stub
			return Dnots.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return Dnots.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return Dnots.size();
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Dnote dnote = Dnots.get(position);
			View view = View.inflate(RijiActivity.this, R.layout.item, null);
			TextView nameDairy = (TextView) view.findViewById(R.id.nameDairy);
			nameDairy.setText(dnote.getTitle());
			
			TextView timeDairy = (TextView) view.findViewById(R.id.timeDairy);
			timeDairy.setText("Ê±¼ä"+dnote.getTime());
			return view;
		}
    	
    }
    public void write(View view){
    Intent it = new Intent(RijiActivity.this,Write.class);
   	   startActivity(it);
   	   finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
