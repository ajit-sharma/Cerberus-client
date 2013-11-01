package sw.gson.trace;

import java.util.ArrayList;
import java.util.Vector;

public class MainClass {
	
	private int cycles;
	private double total;
	private double self;
	private String calls_recal;
	private String name;
	private ArrayList<SubMethod> methods;
	
	public MainClass() {
		
		this.cycles = 0;
		this.total = 0.0;
		this.self = 0.0;
		this.calls_recal = "N/V";
		this.name = "N/V";
		this.methods = new ArrayList<SubMethod>();
	}
	
	public MainClass(int cycles, double total, double self,
			String calls_recal, String name, ArrayList<SubMethod> methods) {
		super();
		this.cycles = cycles;
		this.total = total;
		this.self = self;
		this.calls_recal = calls_recal;
		this.name = name;
		this.methods  = new ArrayList<SubMethod>();
	}
	
	public void setClass(int cycles, double total, double self, 
			String calls_recal, String name) {
		
		this.cycles = cycles;
		this.total = total;
		this.self = self;
		this.calls_recal = calls_recal;
		this.name = name;
	}
	
	public void setMethod(SubMethod _method)
	{
		this.methods.add(_method);
	}
	
	public String getClsName()
	{
		return this.name;
	}
	
}
