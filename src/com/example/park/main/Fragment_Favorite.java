package com.example.park.main;

import com.example.me.fuctions.SettingActivity;
import com.example.park.R;
import com.example.park.R.id;

import android.R.integer;
import android.R.raw;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Fragment_Favorite extends Fragment implements OnClickListener {

	// 定义
	SimpleAdapter mSimpleAdapter;
	private int i = 0;
	private LinearLayout liv_login,liv_favorite,liv_last_parking,liv_coupon,liv_setting;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	public Fragment_Favorite() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_favorite, container,
				false);
		
		//实例化
		liv_coupon= (LinearLayout) rootView.findViewById(R.id.liv_coupon);
		liv_favorite= (LinearLayout) rootView.findViewById(R.id.liv_favorite);
		liv_last_parking = (LinearLayout) rootView.findViewById(R.id.liv_last_parking);
		liv_login = (LinearLayout) rootView.findViewById(R.id.liv_login);
		liv_setting = (LinearLayout) rootView.findViewById(R.id.liv_setting);
		
		
		liv_coupon.setOnClickListener(this);
		liv_favorite.setOnClickListener(this);
		liv_last_parking.setOnClickListener(this);
		liv_login.setOnClickListener(this);
		liv_setting .setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.liv_login:
			Toast.makeText(getActivity(), "login", 0).show();
			break;
		case R.id.liv_favorite:
			
			Toast.makeText(getActivity(), "请先登录", 0).show();
			break;
		case R.id.liv_last_parking:
			
			Toast.makeText(getActivity(), "请先登录", 0).show();
			break;
		case R.id.liv_coupon:
			
			Toast.makeText(getActivity(), "请先登录", 0).show();
			break;
		case R.id.liv_setting:
			
			//Toast.makeText(getActivity(), "请先登录", 0).show();
			Intent intent = new Intent();
			intent.setClass(getActivity(), SettingActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}


		
	

}
