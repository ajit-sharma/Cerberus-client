package sw.gson.trace;

public class MethodForClass {
	
	private int cycles;
	private int total;
	private double self;
	private double acc_self;
	private String calls_recal;
	private String name;
	
	public MethodForClass() {
		this.cycles = 0;
		this.total = 0;
		this.self = 0;
		this.acc_self = 0;
		this.calls_recal = "N/V";
		this.name = "NoName";
	}
	
	public MethodForClass(int _cycles, int _total, double _self, double _acc_self,
			String _calls_recal, String _name) {
		super();
		
		this.cycles = cycles;
		this.total = total;
		this.self = self;
		this.acc_self = acc_self;
		this.calls_recal = calls_recal;
		this.name = name;
	}
	
	public void setMethod(int _cycles, int _total, double _self, double _acc_self,
			String _calls_recal, String _name) {
		
		this.cycles = _cycles;
		this.total = _total;
		this.self = _self;
		this.acc_self = _acc_self;
		this.calls_recal = _calls_recal;
		this.name = _name;
	}
	
	
	
}
