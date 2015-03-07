package com.example.me.fuctions;

import com.example.park.R;
import com.example.park.R.id;
import com.example.park.R.layout;

import android.app.Activity;
import android.app.SearchManager.OnCancelListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class SettingActivity extends Activity implements OnClickListener {
	// 定义
	private LinearLayout liv_push, liv_clean, liv_advice, liv_update,
			liv_about;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		// 初始化
		liv_push = (LinearLayout) findViewById(R.id.liv_push);
		liv_clean = (LinearLayout) findViewById(R.id.liv_clean);
		liv_advice = (LinearLayout) findViewById(R.id.liv_advice);
		liv_update = (LinearLayout) findViewById(R.id.liv_update);
		liv_about = (LinearLayout) findViewById(R.id.liv_about);

		liv_about.setOnClickListener(this);
		liv_update.setOnClickListener(this);
		liv_advice.setOnClickListener(this);
		liv_clean.setOnClickListener(this);
		liv_push.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.liv_push:
			
			break;
		case R.id.liv_clean:
			
			break;
		case R.id.liv_advice:
			
			break;
		case R.id.liv_update:
			
			break;
		case R.id.liv_about:
			Toast.makeText(getApplicationContext(), "BATE1.0", 0).show();
			break;

		default:
			break;
		}
	}

}
