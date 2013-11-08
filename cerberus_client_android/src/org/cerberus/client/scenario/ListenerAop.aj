package org.cerberus.client.scenario;

import org.cerberus.client.scenario.MotionManager;
import org.cerberus.client.scenario.MotionVO;

import android.view.View;
import android.content.res.Resources;

public aspect ListenerAop {

	pointcut onClickEvent(View v) : execution(* *.onClick(..)) && args(v);
	
	before(View v) : onClickEvent(v) {
		
		if(MotionManager.isProfilingMode()) {
		
			System.out.println(v);
			
			Resources r = v.getContext().getResources();
			
			String typeName = v.getClass().getName();
			
			String entryName = r.getResourceEntryName(v.getId());
	
			System.out.println(typeName);
			System.out.println(entryName);
	
			MotionVO motion = new MotionVO();
			
			motion.setType(typeName);
			motion.setId(entryName);
	
			MotionManager.getInstance().add(motion);
		}
	}
	
	pointcut onKeyEvent(View v) : execution(* *.onClick(..)) && args(v);
	
	before(View v) : onClickEvent(v) {
		
		if(MotionManager.isProfilingMode()) {
		
			System.out.println(v);
			
			Resources r = v.getContext().getResources();
			
			String typeName = v.getClass().getName();
			
			String entryName = r.getResourceEntryName(v.getId());
	
			System.out.println(typeName);
			System.out.println(entryName);
	
			MotionVO motion = new MotionVO();
			
			motion.setType(typeName);
			motion.setId(entryName);
	
			MotionManager.getInstance().add(motion);
		}
	}
	
	pointcut onListViewClick(View v) : execution(* *.onClick(..)) && args(v);
	
	before(View v) : onClickEvent(v) {
		
		if(MotionManager.isProfilingMode()) {
		
			System.out.println(v);
			
			Resources r = v.getContext().getResources();
			
			String typeName = v.getClass().getName();
			
			String entryName = r.getResourceEntryName(v.getId());
	
			System.out.println(typeName);
			System.out.println(entryName);
	
			MotionVO motion = new MotionVO();
			
			motion.setType(typeName);
			motion.setId(entryName);
	
			MotionManager.getInstance().add(motion);
		}
	}
	
	pointcut onTextClick(View v) : execution(* *.onClick(..)) && args(v);
	
	before(View v) : onClickEvent(v) {
		
		if(MotionManager.isProfilingMode()) {
		
			System.out.println(v);
			
			Resources r = v.getContext().getResources();
			
			String typeName = v.getClass().getName();
			
			String entryName = r.getResourceEntryName(v.getId());
	
			System.out.println(typeName);
			System.out.println(entryName);
	
			MotionVO motion = new MotionVO();
			
			motion.setType(typeName);
			motion.setId(entryName);
	
			MotionManager.getInstance().add(motion);
		}
	}
	
}
