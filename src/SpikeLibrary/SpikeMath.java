package SpikeLibrary;

public class SpikeMath {
	public static double cap(double value, double min, double max) {
		if (value > max) {
			return max;
		} else if (value < min) {
			return min;
		}
		return value;
	}
}
