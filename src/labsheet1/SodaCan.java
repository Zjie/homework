package labsheet1;

public class SodaCan {
	private double height;
	private double radius;
	@SuppressWarnings("unused")
	private SodaCan() {}
	public SodaCan(double height, double radius) {
		this.height = height;
		this.radius = radius;
	}
	public double getSurfaceArea() {
		return 2 * Math.PI  * radius * radius + 2 * Math.PI  * radius * height; 
	}
	public double getVolume() {
		return Math.PI  * radius * radius * height;
	}
	
	public static void main(String[] args) {
		SodaCan sc = new SodaCan(2.0, 3.0);
		System.out.println("surface area : " + sc.getSurfaceArea());
		System.out.println("volume : " + sc.getVolume());
	}
}
