package power_log_parser;

public class PowerObject {

	private String CPU = "N/V";
	private String threeG = "N/V";
	private String WIFI = "N/V";
	private String GPS = "N/V";
	private String begin = "N/V";
	
	
	public PowerObject() {
		
	}
	public PowerObject(String _cpu, String _threeG, String _wifi, String _gps,
			String _begin) {
		this.CPU = _cpu;
		this.threeG = _threeG;
		this.WIFI = _wifi;
		this.GPS = _gps;
		this.begin = _begin;
	}
	public String getCPU() {
		return CPU;
	}
	public void setCPU(String cPU) {
		CPU = cPU;
	}
	public String getThreeG() {
		return threeG;
	}
	public void setThreeG(String threeG) {
		this.threeG = threeG;
	}
	public String getWIFI() {
		return WIFI;
	}
	public void setWIFI(String wIFI) {
		WIFI = wIFI;
	}
	public String getGPS() {
		return GPS;
	}
	public void setGPS(String gPS) {
		GPS = gPS;
	}
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	@Override
	public String toString() {
		return "PowerObject [CPU=" + CPU + ", threeG=" + threeG + ", WIFI="
				+ WIFI + ", GPS=" + GPS + ", begin=" + begin + "]";
	}
	
	
	
	
	
}
