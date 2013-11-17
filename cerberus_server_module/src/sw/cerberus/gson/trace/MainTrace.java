package sw.cerberus.gson.trace;

public class MainTrace {

	private String method_name;
	private int sec;
	private double self;
	private double total;

	
	public MainTrace(){
		this.sec = 0;
		this.self = 0;
		this.total = 0;
		this.method_name = "null";
	}
	
	public void setAll(int _sec, double _self, double _total){
		this.sec = _sec;
		this.self = _self;
		this.total = _total;
	}
	
	public int getSec() {
		return sec;
	}
	public void setSec(int sec) {
		this.sec = sec;
	}
	public double getSelf() {
		return self;
	}
	public void setSelf(double self) {
		this.self = self;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}	
	
	public void setName(String _name) {
		this.method_name = _name;
	}	
	
	
}
