package main;

public class Main {

	private static final boolean USAR_PREDICAO = false;
	private static Processador processador = new Processador(USAR_PREDICAO);

	public static void main(String[] args) {
		processador.runPipeline();
	}

}
