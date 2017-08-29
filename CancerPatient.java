public class CancerPatient {
	public int id;
	public int a1;
	public int a2;
	public int a3;
	public int a4;
	public int a5;
	public int a6;
	public int a7;
	public int a8;
	public int a9;

	public CancerPatient(String id, String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8, String a9) {
		this.id = Integer.parseInt(id);
		this.a1 = Integer.parseInt(a1);
		this.a2 = Integer.parseInt(a2);
		this.a3 = Integer.parseInt(a3);
		this.a4 = Integer.parseInt(a4);
		this.a5 = Integer.parseInt(a5);
		this.a6 = Integer.parseInt(a6);
		this.a7 = Integer.parseInt(a7);
		this.a8 = Integer.parseInt(a8);
		this.a9 = Integer.parseInt(a9);
	}

	@Override
	public String toString() {
		return String.format("%id,%d,%d,%d,%d,%d,%d,%d,%d,%d", id, a1, a2, a3, a4, a5, a6, a7, a8, a9);
	}

}
