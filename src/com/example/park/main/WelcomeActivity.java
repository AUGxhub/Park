package com.example.park.main;



import com.example.park.R;
import com.example.services.ExitAppService;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		ExitAppService.getInstance().addActivity(this);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		} 
	}

 
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_welcome,
					container, false);
			//ÑÓ³ÙÁ½ÃëÌø×ª
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					 Intent intent = new Intent();
					 intent.setClass(getActivity(), MainActivity.class);
					 startActivity(intent);
				}
			}, 1200);
			

			return rootView;
		}
	}

}
