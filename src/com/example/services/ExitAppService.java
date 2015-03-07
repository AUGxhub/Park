package com.example.services;

import java.util.LinkedList;
import java.util.List;
import android.app.Activity;
import android.app.Application;
//�˳�Ӧ�ó���, ����ģʽ����Activity
//��ÿ����Activity ��onCreate()�����е���ExitAppService.getInstance().addActivity(this)����,����
//��ʱ����ExitAppService.getInstance().exit()�������Ϳ�����ȫ�˳�Ӧ�ó����ˡ�

public class ExitAppService extends Application {
	private List<Activity> activityList = new LinkedList<Activity>();

	private static ExitAppService instance;

	private ExitAppService() {
	}

	// ����ģʽ�л�ȡΨһ��ExitApplication ʵ��
	public static ExitAppService getInstance() {
		if (null == instance) {
			instance = new ExitAppService();
		}
		return instance;

	}

	// ���Activity ��������
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// ��������Activity ��finish

	public void exit() {

		for (Activity activity : activityList) {
			activity.finish();
		}

		System.exit(0);
	}
	
}
