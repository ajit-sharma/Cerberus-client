package org.cerberus.client.scenario;

import java.lang.reflect.Field;
import java.util.Arrays;

import android.R;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

public aspect InjectEventAop {

	pointcut superCalled() : execution(* Activity.onCreate(..) );
	
	before() : superCalled() {
		System.out.println("called super....");
	}
	
//	pointcut thisCalled() : execution(* *.onCreate(..));
	pointcut thisCalled() : call(* setContentView());
	
	after() : thisCalled() {
		System.out.println("called this...");
		
		Class clz = R.id.class;
		
		System.out.println(Arrays.toString(clz.getDeclaredFields()));
		
		for(Field field : clz.getDeclaredFields()) {
			
			try {
				System.out.println(field.get(null));
				System.out.println(((Activity)thisJoinPoint.getThis()).findViewById(field.getInt(null)));
				
				View v = ((Activity)thisJoinPoint.getThis()).findViewById(field.getInt(null));
				
				//default listener onClick
				
				v.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
					}
				});
				
				if(v != null) {
					
					if(v.getClass().getName().endsWith("EditText")) {
						EditText e = (EditText)v;
						e.setOnClickListener(new OnClickListener() {
							
							public void onClick(View v) {
							
	//							System.out.println(v + " click");
	//							InputMethodManager imm = (InputMethodManager) ((Activity)thisJoinPoint.getThis()).getSystemService(Context.INPUT_METHOD_SERVICE);
	//							imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
							}
						});
						
						e.setOnKeyListener(new OnKeyListener() {
							
							public boolean onKey(View v, int keyCode, KeyEvent event) {
								// TODO Auto-generated method stub
								
								System.out.println("SetOnKeyListener   " + v + " " + keyCode + " " + event);
								return false;
							}
						});
						
						e.addTextChangedListener(new TextWatcher() {
							
							String beforeText = null;
							
							public void onTextChanged(CharSequence s, int start, int before, int count) {
								// TODO Auto-generated method stub
								System.out.println(s);
							}
							
							public void beforeTextChanged(CharSequence s, int start, int count,
									int after) {
								// TODO Auto-generated method stub
								
								beforeText = s.toString();
								
								System.out.println("beforeTextChanged");
							}
							
							public void afterTextChanged(Editable s) {
								// TODO Auto-generated method stub
								System.out.println("afterTextChanged");
							}
						});
	//					
	//					e.setOnKeyListener(new OnKeyListener() {
	//						
	//						@Override
	//						public boolean onKey(View v, int keyCode, KeyEvent event) {
	//							
	//							System.out.println(keyCode);
	//
	//							return false;
	//						}
	//					});
						
						
						System.out.println(e + " set listener");
					} else if(v.getClass().getName().endsWith("ListView")) {
						ListView l = (ListView)v;
						
						l.setOnItemClickListener(new OnItemClickListener() {

							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {

								
							}
							
						}
						);
					}
				}				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	pointcut dispatchTouch() : execution(* MainActivity.dispatchTouchEvent(..));
	
	before() : dispatchTouch() {
		System.out.println("Called dispatch");
	}
}
