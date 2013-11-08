package org.cerberus.client.runner;

import java.util.List;

import org.cerberus.client.R;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;

import com.capricorn.ArcMenu;

public class AlwaysOnTopService extends Service {

    private static final int[] ITEM_DRAWABLES = { R.drawable.composer_camera, R.drawable.composer_music,
        R.drawable.composer_place, R.drawable.composer_sleep, R.drawable.composer_thought, R.drawable.composer_with };
	
	private ArcMenu menu;
	
	private boolean stopTask = false;
	private Handler mHandler = new Handler();
	private boolean _isShow = false;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		menu = new ArcMenu(AlwaysOnTopService.this);

		ImageView item1 = new ImageView(AlwaysOnTopService.this);
		item1.setImageResource(ITEM_DRAWABLES[2]);
		menu.addItem(item1, new OnClickListener() {
			
			public void onClick(View v) {

				Log.i("ArcMenu", "do onClick...1");
				
			}
		});
		
		ImageView item2 = new ImageView(AlwaysOnTopService.this);
		item2.setImageResource(ITEM_DRAWABLES[3]);
		menu.addItem(item2, new OnClickListener() {
			
			public void onClick(View v) {

				Log.i("ArcMenu", "do onClick...2");
				
			}
		});
		
		menu.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				System.out.println("--");
				switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					menu.onTouchEvent(event);
				}
				
				return true;
			}
		});
		startService();
	}

	private void startService() {
		
		final Runnable r = new Runnable() {
			
			public void run() {

				boolean isShow = false;
				
				String strPackage = "";
				ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
				List<RunningAppProcessInfo> processes = am.getRunningAppProcesses();
				
				for(RunningAppProcessInfo process : processes) {
					if(process.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
						
						strPackage = process.processName;
						
						if(strPackage.equals(getApplication().getPackageName())) {
							
							if(_isShow) {
								isShow = true;
								_isShow = true;
								break;
							}
							
							WindowManager.LayoutParams params = new WindowManager.LayoutParams(
									WindowManager.LayoutParams.WRAP_CONTENT,
									WindowManager.LayoutParams.WRAP_CONTENT,
									WindowManager.LayoutParams.TYPE_PHONE,
									WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
									PixelFormat.TRANSLUCENT);

							params.gravity = Gravity.LEFT | Gravity.BOTTOM;
							
							WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
							wm.addView(menu, params);
							
							isShow = true;
							_isShow = true;
							break;
						}
						
					}
				}
				
				if(!isShow) {
					if(_isShow) {
						WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
						wm.removeView(menu);	
					}
					_isShow = false;
				}
				
				if(!stopTask) 
					mHandler.postDelayed(this, 100);
				
			}
		};
		
		r.run();
	}
	
	public void onDestroy() {
		super.onDestroy();
		
		stopTask = true;
		
		if(menu != null) {
			((WindowManager)getSystemService(WINDOW_SERVICE)).removeView(menu);
			menu = null;
		}
		
	}

}
