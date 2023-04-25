
public class Rotater {
	
	public static void main(String args[]) {
		int[] f={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		f=rotate(f,-7);
		for (int x=0; x<f.length; x++) {
			System.out.print(f[x]+" ");
		}
	}
	public static int[] rotate(int[] a, int r) {
		int[] n=new int[Math.abs(r)];
		if (r==0) return a;
		else if (r>0) {
			for (int x=0; x<r; x++) {
				n[r-x-1]=a[a.length-x-1];
			}
			for (int x=a.length-1; x>=r; x--) {
				a[x]=a[x-r];
			}
			for (int x=0; x<r; x++) {
				a[x]=n[x];
			}
		}
		else {
			for (int x=0; x<-r; x++) {
				n[x]=a[x];
			}
			for (int x=0; x<a.length+r; x++) {
				a[x]=a[x-r];
			}
			for (int x=a.length+r; x<a.length; x++) {
				a[x]=n[x-a.length-r];
			}
		}
		return a;
	}
}
