public class NoDebeTenerConsumiciones extends Exception {

	public static void method(double consumiciones)
			throws NoDebeTenerConsumiciones {
		if (consumiciones != 0) {
			throw new NoDebeTenerConsumiciones();
		}
	}
}
