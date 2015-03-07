package com.example.actionbar;

import com.example.park.R;
import com.example.park.R.id;
import com.example.park.R.layout;
import com.example.park.R.menu;
import com.example.services.ExitAppService;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class LocateActivity extends Activity {
	private static TextView tx_city;
	private static MyHandler mHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locate);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		mHandler = new MyHandler();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.locate, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		//定义
		private Button btn_locate;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_locate,
					container, false);
			
			//初始化
			btn_locate = (Button) rootView.findViewById(R.id.btn_locate);
			tx_city = (TextView) rootView.findViewById(R.id.tx_city);
			btn_locate.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getActivity(), "btn is ok ", 0).show();
				}
			});
			return rootView;
		}
	}
//动态更新
	class MyHandler extends Handler{
		
		public MyHandler() {
			
		}
		public  MyHandler(Looper l) {
			super();
		}
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			Bundle mBundle = msg.getData();
			String city = mBundle.getString("city");
			LocateActivity.this.tx_city.setText(city);
			super.handleMessage(msg);
		}
		
		
	}
	
}
