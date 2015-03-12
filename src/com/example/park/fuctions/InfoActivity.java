package com.example.park.fuctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.actionbar.LocateActivity;
import com.example.actionbar.SearchActivity;
import com.example.getmein.fuctions.SIMCardInfo;
import com.example.park.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.park.fuctions.CarsView.ZoomChangeListener;
import com.example.park.main.MainActivity;
import com.example.services.ExitAppService;

public class InfoActivity extends Activity {
	//hi test for github cvs
	// ��������
	MyHandler mHandler;
	public static int ROW = 15;// �����������
	public static int COL = 20;// �����������
	private Button btn_submitcar;
	private TextView tx_port, tx_left_num, tx_sum_num, tx_cost;
	private ProgressDialog proDialog;
	protected static int width;
	protected static int height;
	StringBuilder car = new StringBuilder();
	List<Integer> buycarList = new ArrayList<Integer>();
	CarsView carView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		ExitAppService.getInstance().addActivity(this);
		initView();
		// �ύ��ť�Ĵ���
		btn_submitcar = (Button) findViewById(R.id.btn_submit);
		btn_submitcar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Toast.makeText(getApplicationContext(), "test ok", 0).show();
				// ��һ��������ע��Ҫ������ֹ�����ķ���
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), MainActivity.class);
				// ��ȡʱ��
				SimpleDateFormat mDateFormat = new SimpleDateFormat(
						"yyyyMMddhhmmss");
				String strTime = mDateFormat.format(new Date());
				// ��ȡ�ֻ���Ϣ
				SIMCardInfo siminfo = new SIMCardInfo(getApplicationContext());
				String strDeviceNum = siminfo.getPhoneDeviceId();
				String strProvider = siminfo.getProvidersName();
				
				intent.putExtra("strTime", strTime);
				intent.putExtra("strDeviceNum", strDeviceNum);
				intent.putExtra("strProvider", strProvider);
				intent.putExtra("page", 1);
				startActivity(intent);

			}
		});

		// ��̬�����ı�
		tx_port = (TextView) findViewById(R.id.tx_port);
		tx_left_num = (TextView) findViewById(R.id.tx_left_num);
		tx_sum_num = (TextView) findViewById(R.id.tx_sum_num);
		tx_cost = (TextView) findViewById(R.id.tx_cost);
		mHandler = new MyHandler();

		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		width = (displayMetrics.widthPixels);
		height = (displayMetrics.heightPixels);
		new Thread(runnable).start();

	}

	private void initView() {
		proDialog = ProgressDialog.show(InfoActivity.this, "����",
				"���������У����Ժ�...", true, true);
		proDialog.setCanceledOnTouchOutside(false);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.arg1) {
			case 1:
				proDialog.dismiss();
				updateUI();
				break;
			}
		}
	};

	/**
	 * ��ʾ������λ
	 */
	private void updateUI() {
		LinearLayout myView = (LinearLayout) findViewById(R.id.carviewcont);
		myView.removeAllViews();
		carView = new CarsView(this);
		myView.addView(carView);
		carView.setZoomChangeListener(new ZoomChangeListener() {
			public void ZoomChange(int box_size) {
				// TODO Auto-generated method stub
				LinearLayout myView2 = (LinearLayout) findViewById(R.id.carraw);
				myView2.removeAllViews();
				for (int i = 0; i < COL; i++) {
					TextView textView = new TextView(InfoActivity.this);
					textView.setText((i + 1) + "");
					textView.setTextSize(9.0f);
					textView.setGravity(Gravity.CENTER_VERTICAL);
					textView.setLayoutParams(new LayoutParams(
							LayoutParams.WRAP_CONTENT, box_size));
					myView2.addView(textView);
				}
			}
		});
		// ��ʼ����Ϣ
//		Updadte_Info();
	}

	Runnable runnable = new Runnable() {
		public void run() {
			Message msg = handler.obtainMessage();
			msg.arg1 = 1;
			handler.sendMessage(msg);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// ��ӱ����Ҳఴť
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.act_serach:
			// Toast.makeText(getApplicationContext(), "search", 0).show();
			Intent intent1 = new Intent();
			intent1.setClass(getApplicationContext(), SearchActivity.class);
			startActivity(intent1);
			break;
		case R.id.act_locating:
			// Toast.makeText(getApplicationContext(), "locate", 0).show();
			Intent intent2 = new Intent();
			intent2.setClass(getApplicationContext(), LocateActivity.class);
			startActivity(intent2);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	// ��̬�����ı���handler
	class MyHandler extends Handler {
		public MyHandler() {
		}

		public MyHandler(Looper l) {
			super();
		}

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			Bundle mbBundle = msg.getData();

			String port = mbBundle.getString("port");
			InfoActivity.this.tx_port.setText(port);

			String left_num = mbBundle.getString("left_num");
			InfoActivity.this.tx_left_num.setText(left_num);
			String sum_num = mbBundle.getString("sum_num");
			InfoActivity.this.tx_sum_num.setText(sum_num);
			String cost = mbBundle.getString("cost");
			InfoActivity.this.tx_cost.setText(cost);

			super.handleMessage(msg);

		}
	}

//	public void Updadte_Info() {
//		Intent mIntent = getIntent();
//		Message msg = new Message();
//		Bundle mBundle = new Bundle();// �������
//		mBundle.putString("port", mIntent.getStringExtra("port"));
//		mBundle.putString("left_num", mIntent.getStringExtra("left_num"));
//		mBundle.putString("sum_num", mIntent.getStringExtra("sum_num"));
//		mBundle.putString("cost", String.valueOf(carView.getSeat_num() * 10));
//		msg.setData(mBundle);
//		InfoActivity.this.mHandler.sendMessage(msg);
//	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// �ַ������¼� �ȴ���carsview
		carView.onTouchEvent(ev);
		this.onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// ��Ӧ�����¼���������Ϣ
//		Updadte_Info();
		// Toast.makeText(getApplicationContext(), "touch is ok", 0).show();
		return false;
	}

}
