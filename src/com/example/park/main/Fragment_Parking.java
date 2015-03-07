package com.example.park.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.park.R;
import com.example.park.fuctions.InfoActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Fragment_Parking extends Fragment implements OnItemClickListener {

	// 定义
	SimpleAdapter mSimpleAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	public Fragment_Parking() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_parking, container,
				false);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		// 利用simple adapter动态填充数据 注：下一步升级从服务器动态更新
		ListView lv_Parking = null;
		lv_Parking = (ListView) getActivity().findViewById(R.id.lv_parking);
		int[] imgParking = { R.drawable.ic_launcher, R.drawable.ic_launcher,
				R.drawable.ic_launcher, R.drawable.ic_launcher,
				R.drawable.ic_launcher, R.drawable.ic_launcher,
				R.drawable.ic_launcher, R.drawable.ic_launcher,
				R.drawable.ic_launcher };
		String[] strParkingName = { "停车场名字", "test2", "test13", "test12",
				"st1", "t1", "test3", "test3", };
		String[] strDistance = { "距离", "100", "100", "100", "100", "100",
				"100", "100" };
		String[] strLeft = { "剩余位", "12", "14", "40", "0", "10", "10", "10" };
		String[] strSum = { "总位数", "40", "40", "40", "40", "40", "40", "40" };
		String[] strPayTool = { "支持的支付方式", "palpay", "palpay", "palpay",
				"palpay", "palpay", "palpay", "palpay" };
		int[] to = { R.id.imgParking, R.id.strParkingName, R.id.strDistance,
				R.id.tx_left, R.id.tx_sum, R.id.strPayTool };
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < strParkingName.length; i++) {
			Map map = new HashMap<>();
			map.put("imgParking", imgParking[i]);
			map.put("strParkingName", strParkingName[i]);
			map.put("strDistance", strDistance[i]);
			map.put("strLeft", strLeft[i]);
			map.put("strSum", strSum[i]);
			map.put("strPayTool", strPayTool[i]);
			list.add(map);
		}
		String[] from = { "imgParking", "strParkingName", "strDistance",
				"strLeft", "strSum", "strPayTool" };
		mSimpleAdapter = new SimpleAdapter(getActivity(), list,
				R.layout.parking_list_item, from, to);
		lv_Parking.setAdapter(mSimpleAdapter);
		lv_Parking.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
        //跳转到相应的信息页 并传递停车场信息
		Intent intent;
		intent = new Intent();
		intent.setClass(getActivity(), InfoActivity.class);
		TextView tx_name = (TextView) view.findViewById(R.id.strParkingName);
		TextView tx_left = (TextView) view.findViewById(R.id.tx_left);
		TextView tx_sum = (TextView) view.findViewById(R.id.tx_sum);
		String str_name = (String) tx_name.getText();
		String str_left =  (String) tx_left.getText();
		String str_sum =  (String) tx_sum.getText();
		intent.putExtra("port", str_name);
		intent.putExtra("left_num", str_left);
		intent.putExtra("sum_num",str_sum);
		// Toast.makeText(getActivity(), "这是第"+position+"个", 0).show();
		startActivity(intent);
	}

}
