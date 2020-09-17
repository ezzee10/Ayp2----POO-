public class NumeroMesInvalido extends Exception {
	public static void method(int mes) throws NumeroMesInvalido {
		if (mes > 12 || mes <= 0) {
			throw new NumeroMesInvalido();
		}
	}
}
