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
	// 定义

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
		// 初始化
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
	 * 用于初始化界面展示的view
	 */
	private void initView() {

		btnArrayCodeButton = (Button) getView().findViewById(R.id.btn_code);
		imgCodeImageView = (ImageView) getView().findViewById(R.id.code_img);
	}
	/**
	 * 生成并显示二维码
	 */
	protected void getDCode() {
		String str = strInfo;
		Bitmap bmp = null;
		try {
			if (str != null && !"".equals(str)) {
				bmp = CreateTwoDCode(str);
			} else {
				Toast.makeText(getActivity(), "无效的操作", Toast.LENGTH_SHORT)
						.show();
			}
		} catch (WriterException e) {
		}
		if (bmp != null) {
			imgCodeImageView.setImageBitmap(bmp);
		}
	}

	/**
	 * 设置生成二维码和扫描二维码的事件
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
						Toast.makeText(getActivity(), "无效的操作", Toast.LENGTH_SHORT)
								.show();
					}
				} catch (WriterException e) {
				}
				if (bmp != null) {
					imgCodeImageView.setImageBitmap(bmp);
				}
			}
		});
		// 生成一维码的实例
		// btnLineCodeButton.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View view) {
		// String str = etCode.getText().toString().trim();
		// int size = str.length();
		// for (int i = 0; i < size; i++) {
		// int c = str.charAt(i);
		// if ((19968 <= c && c < 40623)) {
		// Toast.makeText(getActivity(), "生成条形码的时刻不能是中文",
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
	 * 将指定的内容生成成二维码
	 *
	 * @param content
	 *            将要生成二维码的内容
	 * @return 返回生成好的二维码事件
	 * @throws WriterException
	 *             WriterException异常
	 */
	public Bitmap CreateTwoDCode(String content) throws WriterException {
		// 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
		BitMatrix matrix = new MultiFormatWriter().encode(content,
				BarcodeFormat.QR_CODE, 300, 300);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		// 二维矩阵转为一维像素数组,也就是一直横着排了
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
		// 通过像素数组生成bitmap,具体参考api
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}

	/**
	 * 用于将给定的内容生成成一维码 注：目前生成内容为中文的话将直接报错，要修改底层jar包的内容
	 *
	 * @param content
	 *            将要生成一维码的内容
	 * @return 返回生成好的一维码bitmap
	 * @throws WriterException
	 *             WriterException异常
	 */
	public Bitmap CreateOneDCode(String content) throws WriterException {
		// 生成一维条码,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
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
		// 通过像素数组生成bitmap,具体参考api
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
