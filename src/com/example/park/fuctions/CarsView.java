package com.example.park.fuctions;

import java.util.ArrayList;
import java.util.List;

import com.example.park.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

@SuppressLint("DrawAllocation")
public class CarsView extends View {
	Paint paint = new Paint();
	Bitmap car_ok;
	Bitmap car_selled;
	Bitmap car_select;
	Bitmap car_null;
	int WIDTH, HEIGHT;
	int BOX_MAX_SIZE = 50;
	int car_MAX_SIZE = 40;
	int box_size = BOX_MAX_SIZE;
	int car_size = car_MAX_SIZE;
	int hang = 0;
	int lie = 0;
	double zoom;
	double zoom_beilv;
	int zoom_level = 0;
	long mLastTime;
	long mCurTime;
	Canvas canvas;
	private List<Integer> mySeatList=null;// 保存选中车位
	private List<Integer> unavaliableSeatList;
	private int seat_num =0;//选择的车位数量


	Bitmap SeatOk, SeatSelled, SeatSelect, SeatNull;
	boolean isFirstLoad = true;

	public CarsView(Context context) {
		super(context);
		mySeatList = new ArrayList<Integer>();
		unavaliableSeatList = new ArrayList<Integer>();
		lie = InfoActivity.ROW;
		hang = InfoActivity.COL;

		SeatOk = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.car_empty);
		SeatSelled = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.car_selled);
		SeatSelect = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.car_select);
		SeatNull = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.car_null);
	}

	public CarsView(Context context, AttributeSet attr) {
		super(context, attr);
	}

	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(box_size * lie, box_size * hang);
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (isFirstLoad) {
			double zoom_temp1 = ((InfoActivity.width * 1.0 - 100)
					/ lie / box_size);
			double zoom_temp2 = ((InfoActivity.height * 1.0 /2.5)
					/ hang / box_size);
			if (zoom_temp1>zoom_temp2) {
				zoom_temp1=zoom_temp2;
			}
			zoom_beilv = Math.pow(1 / zoom_temp1, 1 / 4.0);
			box_size = (int) (box_size * zoom_temp1);
			car_size = (int) (car_size * zoom_temp1);
			OnZoomChangeListener.ZoomChange(box_size);
			isFirstLoad = false;
			zoom = 1.0;
			zoom_level = 1;
		}
		box_size = (int) (box_size * zoom);
		car_size = (int) (car_size * zoom);

		// 可购买车位
		car_ok = Bitmap.createScaledBitmap(SeatOk, car_size, car_size, true);
		// 红色 已售车位
		car_selled = Bitmap.createScaledBitmap(SeatSelled, car_size,
				car_size, true);
		// 蓝色 我的选择
		car_select = Bitmap.createScaledBitmap(SeatSelect, car_size,
				car_size, true);
		// 没车位 替换成透明图
		car_null = Bitmap.createScaledBitmap(SeatNull, car_size, car_size,
				true);
		// 画车位
		for (int i = 0; i < hang; i++) {
			for (int j = 0; j < lie; j++) {
				// 我自己是根据服务器数据排车位的 这里我就随便改了 以便demo等跑起来
				canvas.drawBitmap(car_ok, j * (box_size), i * (box_size), null);
				if (i == 7) {
					canvas.drawBitmap(car_selled, j * (box_size), i
							* (box_size), null);
					unavaliableSeatList.add(i * lie + j);
				}
				if (j == 3) {
					canvas.drawBitmap(car_selled, j * (box_size), i
							* (box_size), null);
					unavaliableSeatList.add(i * lie + j);
				}
				if (j== 14) {
					canvas.drawBitmap(car_selled, j * (box_size), i
							* (box_size), null);
					unavaliableSeatList.add(i * lie + j);
				}
				// 过道
				if (i == 5) {
					canvas.drawBitmap(car_null, j * (box_size),
							i * (box_size), null);
					unavaliableSeatList.add(i * lie + j);
				}
				// 过道
				if (i == 9) {
					canvas.drawBitmap(car_null, j * (box_size),
							i * (box_size), null);
					unavaliableSeatList.add(i * lie + j);
				}
			}
		}
		// 我的车位 变成蓝色
		for (int i = 0; i < mySeatList.size(); i++) {
			canvas.drawBitmap(car_select,
					(mySeatList.get(i) % lie) * box_size,
					(mySeatList.get(i) / lie) * box_size, null);
		}
		this.setLayoutParams(new LinearLayout.LayoutParams(box_size * lie,
				box_size * hang));
		zoom = 1.0;
	}

	int mode = 0;
	float oldDist;
	float newDist;
	int oldClick;
	int newClick;
	boolean zoomType;
	int count = 0;
	float currentXPosition;
	float currentYPosition;

	public boolean onTouchEvent(MotionEvent event) {

			switch (event.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN:
				count++;
				if (count == 1) {
					mLastTime = getClickTime(event);
				} else if (count == 2) {
					mCurTime = getClickTime(event);
					if (zoom_level == 1 && mCurTime - mLastTime < 400) {
						box_size = BOX_MAX_SIZE;
						car_size = car_MAX_SIZE;
						zoom = 1.0;
						zoom_level = 5;
						OnZoomChangeListener.ZoomChange(box_size);
						invalidate();
					}
					count = 0;
					mCurTime = 0;
					mLastTime = 0;
				}
				mode = 1;
				oldClick = getClickPoint(event);
				break;
			case MotionEvent.ACTION_UP:
				mode = 0;
				newClick = getClickPoint(event);
				if (newClick == oldClick) {
					if (mySeatList.contains(newClick)) {
						mySeatList.remove(mySeatList.indexOf(newClick));
						invalidate();
					} else if (!unavaliableSeatList.contains(newClick)) {
						mySeatList.add(newClick);
						invalidate();
					}
				}
				zoomType = false;
				
				if (!mySeatList.isEmpty()) {
					seat_num = mySeatList.size();
//					Toast.makeText(getContext(),String.valueOf(seat_num), 0).show();
				} else {
					seat_num = 0;
//					Toast.makeText(getContext(),String.valueOf(0), 0).show();
				}
				break;
			case MotionEvent.ACTION_POINTER_UP:
				mode -= 1;
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				count = 0;
				mode += 1;
				zoomType = true;
				oldDist = spacing(event);// 两点按下时的距离
				break;
			case MotionEvent.ACTION_MOVE:
				if (mode >= 2) {
					newDist = spacing(event);
					if (zoomType && newDist - oldDist > 10) {
						if (zoom_level == 4) {
							box_size = BOX_MAX_SIZE;
							car_size = car_MAX_SIZE;
							OnZoomChangeListener.ZoomChange(box_size);
							zoom = 1.0;
							zoom_level = 5;
							invalidate();
						} else if (zoom_level < 5) {
							zoom = zoom_beilv;
							OnZoomChangeListener
									.ZoomChange((int) (box_size * zoom));
							zoom_level++;
							invalidate();
						}
						zoomType = false;
					} else if (zoomType && oldDist - newDist > 10) {
						if (zoom_level > 1) {
							zoom = 1 / zoom_beilv;
							OnZoomChangeListener
									.ZoomChange((int) (box_size * zoom));
							zoom_level--;
							invalidate();
						}
						zoomType = false;
					}
				}
				break;
			}

			return true;

	}

	public List<Integer> getMySeatList() {
		return mySeatList;
	}


	private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

	private int getClickPoint(MotionEvent event) {
		float currentXPosition = event.getX();
		float currentYPosition = event.getY();
		for (int i = 0; i < hang; i++) {
			for (int j = 0; j < lie; j++) {
				if ((j * box_size) < currentXPosition
						&& currentXPosition < j * box_size + box_size
						&& (i * box_size) < currentYPosition
						&& currentYPosition < i * box_size + box_size) {
					return i * lie + j;
				}
			}
		}
		return 0;
	}

	private long getClickTime(MotionEvent event) {
		return System.currentTimeMillis();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		WIDTH = w;
		HEIGHT = h;
	}

	public interface ZoomChangeListener {
		public void ZoomChange(int box_size);
	}

	private ZoomChangeListener OnZoomChangeListener = null;

	public void setZoomChangeListener(ZoomChangeListener listener) {
		OnZoomChangeListener = listener;
	}

	public int getSeat_num() {
		return seat_num;
	}
}
