package algorithm.math;

/**
 * Pow(x, n)
 */
public class Pow {
	public double myPow(double x, int n) {
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return x;
		}
		if (n == -1) {
			return 1 / x;
		}
		double half = myPow(x, n / 2);
		double rest = myPow(x, n % 2);
		double total = rest * half * half;
		return total;
	}
	
	public static void main(String[] args) {
		Pow pow = new Pow();
		Double ret = pow.myPow(5, 5);
		System.err.println(ret);
	}
}
