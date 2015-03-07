package com.example.services;

import java.util.LinkedList;
import java.util.List;
import android.app.Activity;
import android.app.Application;
//退出应用程序, 单例模式管理Activity
//在每个在Activity 的onCreate()方法中调用ExitAppService.getInstance().addActivity(this)方法,在退
//出时调用ExitAppService.getInstance().exit()方法，就可以完全退出应用程序了。

public class ExitAppService extends Application {
	private List<Activity> activityList = new LinkedList<Activity>();

	private static ExitAppService instance;

	private ExitAppService() {
	}

	// 单例模式中获取唯一的ExitApplication 实例
	public static ExitAppService getInstance() {
		if (null == instance) {
			instance = new ExitAppService();
		}
		return instance;

	}

	// 添加Activity 到容器中
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// 遍历所有Activity 并finish

	public void exit() {

		for (Activity activity : activityList) {
			activity.finish();
		}

		System.exit(0);
	}
	
}
