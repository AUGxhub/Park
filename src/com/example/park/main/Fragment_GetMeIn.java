package com.example.park.main;

import com.example.park.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Fragment_GetMeIn extends Fragment implements OnItemClickListener {
	// ����

	private String strInfo = null;
	private ImageView imgCodeImageView;
	private Button btnArrayCodeButton;
	SimpleAdapter mSimpleAdapter;
	private int i = 0;
	private Intent mintent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_get_me_in,
				container, false);
		// ��ʼ��
		mintent = getActivity().getIntent();
		strInfo = mintent.getStringExtra("strTime")
				+ mintent.getStringExtra("strDeviceNum")
				+ mintent.getStringExtra("strProvider");
		// Toast.makeText(getActivity(), strInfo, Toast.LENGTH_LONG).show();
		btnArrayCodeButton = (Button) rootView.findViewById(R.id.btn_code);
		imgCodeImageView = (ImageView) rootView.findViewById(R.id.code_img);
		getDCode();
		setListener();
		return rootView;
	}

	/**
	 * ���ڳ�ʼ������չʾ��view
	 */
	private void initView() {

		btnArrayCodeButton = (Button) getView().findViewById(R.id.btn_code);
		imgCodeImageView = (ImageView) getView().findViewById(R.id.code_img);
	}
	/**
	 * ���ɲ���ʾ��ά��
	 */
	protected void getDCode() {
		String str = strInfo;
		Bitmap bmp = null;
		try {
			if (str != null && !"".equals(str)) {
				bmp = CreateTwoDCode(str);
			} else {
				Toast.makeText(getActivity(), "��Ч�Ĳ���", Toast.LENGTH_SHORT)
						.show();
			}
		} catch (WriterException e) {
		}
		if (bmp != null) {
			imgCodeImageView.setImageBitmap(bmp);
		}
	}

	/**
	 * �������ɶ�ά���ɨ���ά����¼�
	 */
	private void setListener() {
		btnArrayCodeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = strInfo;
				Bitmap bmp = null;
				try {
					if ((strInfo != null) && (!"".equals(strInfo))) {
						Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
						bmp = CreateTwoDCode(str);
					} else {
						bmp = null;
						Toast.makeText(getActivity(), "��Ч�Ĳ���", Toast.LENGTH_SHORT)
								.show();
					}
				} catch (WriterException e) {
				}
				if (bmp != null) {
					imgCodeImageView.setImageBitmap(bmp);
				}
			}
		});
		// ����һά���ʵ��
		// btnLineCodeButton.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View view) {
		// String str = etCode.getText().toString().trim();
		// int size = str.length();
		// for (int i = 0; i < size; i++) {
		// int c = str.charAt(i);
		// if ((19968 <= c && c < 40623)) {
		// Toast.makeText(getActivity(), "�����������ʱ�̲���������",
		// Toast.LENGTH_SHORT).show();
		// return;
		// }
		// }
		// Bitmap bmp = null;
		// try {
		// if (str != null && !"".equals(str)) {
		// bmp = CreateOneDCode(str);
		// }
		// } catch (WriterException e) {
		// e.printStackTrace();
		// }
		// if (bmp != null) {
		// imgCodeImageView.setImageBitmap(bmp);
		// }
		// }
		// });
	}

	/**
	 * ��ָ�����������ɳɶ�ά��
	 *
	 * @param content
	 *            ��Ҫ���ɶ�ά�������
	 * @return �������ɺõĶ�ά���¼�
	 * @throws WriterException
	 *             WriterException�쳣
	 */
	public Bitmap CreateTwoDCode(String content) throws WriterException {
		// ���ɶ�ά����,����ʱָ����С,��Ҫ������ͼƬ�Ժ��ٽ�������,������ģ������ʶ��ʧ��
		BitMatrix matrix = new MultiFormatWriter().encode(content,
				BarcodeFormat.QR_CODE, 300, 300);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		// ��ά����תΪһά��������,Ҳ����һֱ��������
		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (matrix.get(x, y)) {
					pixels[y * width + x] = 0xff000000;
				}
			}
		}

		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		// ͨ��������������bitmap,����ο�api
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	/**
	 * ���ڽ��������������ɳ�һά�� ע��Ŀǰ��������Ϊ���ĵĻ���ֱ�ӱ���Ҫ�޸ĵײ�jar��������
	 *
	 * @param content
	 *            ��Ҫ����һά�������
	 * @return �������ɺõ�һά��bitmap
	 * @throws WriterException
	 *             WriterException�쳣
	 */
	public Bitmap CreateOneDCode(String content) throws WriterException {
		// ����һά����,����ʱָ����С,��Ҫ������ͼƬ�Ժ��ٽ�������,������ģ������ʶ��ʧ��
		BitMatrix matrix = new MultiFormatWriter().encode(content,
				BarcodeFormat.CODE_128, 500, 200);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		int[] pixels = new int[width * height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (matrix.get(x, y)) {
					pixels[y * width + x] = 0xff000000;
				}
			}
		}
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		// ͨ��������������bitmap,����ο�api
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	public Fragment_GetMeIn() {
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

	}

}
