
public class MidField extends Field {
	private int distance;
	
	public MidField(String name, int distance) {
		super.setName(name);
		this.setDistance(distance);
	}
	
	public int getDistance() {
		return this.distance;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
}
