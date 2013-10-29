package sw.gson.trace;

import java.util.ArrayList;
import java.util.Vector;

public class MainClass {
	
	private int cycles;
	private double total;
	private double self;
	private String calls_recal;
	private String name;
	private ArrayList<MethodForClass> methods;
	
	public MainClass() {
		
		this.cycles = cycles;
		this.total = total;
		this.self = self;
		this.calls_recal = calls_recal;
		this.name = name;
		this.methods = new ArrayList<MethodForClass>();
	}
	
	public MainClass(int cycles, double total, double self,
			String calls_recal, String name, ArrayList<MethodForClass> methods) {
		super();
		this.cycles = cycles;
		this.total = total;
		this.self = self;
		this.calls_recal = calls_recal;
		this.name = name;
		this.methods  = new ArrayList<MethodForClass>();
	}
	
	public void setClass(int cycles, double total, double self, 
			String calls_recal, String name) {
		
		this.cycles = cycles;
		this.total = total;
		this.self = self;
		this.calls_recal = calls_recal;
		this.name = name;
	}
	
	public void setMethod(MethodForClass _method)
	{
		this.methods.add(_method);
	}
	
}
