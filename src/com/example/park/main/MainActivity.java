package com.example.park.main;

import java.util.ArrayList;
import java.util.List;

import com.example.actionbar.LocateActivity;
import com.example.actionbar.SearchActivity;
import com.example.park.R;
import com.example.services.ExitAppService;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.TabActivity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;
import android.os.Bundle;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	//定义
	private long mExitTime;
	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;
	private TabHost tabHost;
	private Intent mIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ExitAppService.getInstance().addActivity(this);
		mIntent = getIntent();
		// 左右翻页
		List<Fragment> fragments = new ArrayList<Fragment>();
		fragments.add(new Fragment_Parking());
		fragments.add(new Fragment_GetMeIn());
		fragments.add(new Fragment_Favorite());
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager(),
				fragments);
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// 获取TabHost对象
		tabHost = getTabHost();
		// tabHost.setup();
		// 新建一个newTabSpec,设置标签和图标(setIndicator),设置内容(setContent)
		tabHost.addTab(tabHost
				.newTabSpec("Parking")
				.setIndicator(
						"",
						getResources().getDrawable(
								android.R.drawable.ic_menu_search))
				.setContent(R.id.tab1));
		tabHost.addTab(tabHost
				.newTabSpec("GetMeIn")
				.setIndicator(
						"",
						getResources().getDrawable(
								android.R.drawable.ic_menu_mylocation))
				.setContent(R.id.tab2));
		tabHost.addTab(tabHost
				.newTabSpec("Me")
				.setIndicator(
						"",
						getResources().getDrawable(
								android.R.drawable.ic_menu_add))
				.setContent(R.id.tab3));
		// 设置TabHost的背景颜色
		// tabHost.setBackgroundColor(Color.argb(150,22,70,150));
		// 设置TabHost的背景图片资源
		// tabHost.setBackgroundResource(R.drawable.bg);
		
		// 监听器
		// 标签切换处理，用setOnTabChangedListener
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			public void onTabChanged(String tabId) {
				// Toast.makeText(MainActivity.this,
				// "This is a Test!" + tabHost.getCurrentTab(),
				// Toast.LENGTH_SHORT).show();
				mViewPager.setCurrentItem(tabHost.getCurrentTab());
			}

		});
		
		// 设置当前现实哪一个标签
		tabHost.setCurrentTab(mIntent.getIntExtra("page", 0)); // 0为标签ID

		// 监听器
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				tabHost.setCurrentTab(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}

		});
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		private List<Fragment> fragments;

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public SectionsPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;

		}

		@Override
		public Fragment getItem(int position) {
			return fragments.get(position);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// 添加标题右侧按钮
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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Object mHelperUtils;
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();

			} else {
				ExitAppService.getInstance().exit();
				// finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
