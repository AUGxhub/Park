package com.example.getmein.fuctions;

import android.content.Context;
import android.telephony.TelephonyManager;


public class SIMCardInfo {
	/**
	 * TelephonyManager�ṩ�豸�ϻ�ȡͨѶ������Ϣ����ڡ� Ӧ�ó������ʹ������෽��ȷ���ĵ��ŷ����̺͹��� �Լ�ĳЩ���͵��û�������Ϣ��
	 * Ӧ�ó���Ҳ����ע��һ�����������绰��״̬�ı仯������Ҫֱ��ʵ���������
	 * ʹ��Context.getSystemService(Context.TELEPHONY_SERVICE)����ȡ������ʵ����
	 */
	private TelephonyManager telephonyManager;
	/**
	 * �����ƶ��û�ʶ����
	 */
	private String IMSI;

	public SIMCardInfo(Context context) {
		telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
	}

	/**
	 * Role:��ȡ��ǰ���õĵ绰���� <BR>
	 */
	public String getNativePhoneNumber() {
		String NativePhoneNumber = null;
		NativePhoneNumber = telephonyManager.getLine1Number();
		return NativePhoneNumber;
	}
	/**
	 * Role:��ȡ��ǰ���õĵ绰id <BR>
	 */
	public String getPhoneDeviceId() {
		String PhoneDeviceId = null;
		PhoneDeviceId = telephonyManager.getDeviceId();
		return PhoneDeviceId;
	}

	/**
	 * Role:Telecom service providers��ȡ�ֻ���������Ϣ <BR>
	 * ��Ҫ����Ȩ��<uses-permission android:name="android.permission.READ_PHONE_STATE"/> <BR>
	 *
	 */
	public String getProvidersName() {
		String ProvidersName = null;
		// ����Ψһ���û�ID;�������ſ��ı�������
		IMSI = telephonyManager.getSubscriberId();
		// IMSI��ǰ��3λ460�ǹ��ң������ź���2λ00 02���й��ƶ���01���й���ͨ��03���й����š�
		System.out.println(IMSI);
		if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
			ProvidersName = "CMCC";
		} else if (IMSI.startsWith("46001")) {
			ProvidersName = "CUCC";
		} else if (IMSI.startsWith("46003")) {
			ProvidersName = "CTCC";
		}
		return ProvidersName;
	}
}