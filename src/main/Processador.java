package main;

import java.util.Scanner;

import main.helper.LogHelper;
import main.model.Instrucao;
import main.stages.Busca;
import main.stages.Dec;
import main.stages.Exec;
import main.stages.Mem;
import main.stages.Wb;

public final class Processador {
	public static int[] registradores = new int[32];
	private static boolean[] predicoes = new boolean[32];
	private static Wb wb = new Wb();
	private static Mem mem = new Mem();
	private static Exec exec = new Exec();
	private static Dec dec = new Dec();
	private static Busca busca = new Busca();

	public static int PC = 0;
	
	public static int op1 = 0;
	public static int op2 = 0;
	public static int op3 = 0;
	public static int bufferExMem = 0;

	public static int cicleCount = 1;
	public static int validCount = 0;
	public static int invalidCount = 0;

	public void runPipeline() {
		while (true) { //Clock
	        Processador.PC++;
			wb.rodar(mem.getInstrucao());
			mem.rodar(exec.getInstrucao());
			exec.rodar(dec.getInstrucao());
			dec.rodar(busca.getInstrucao());
			busca.rodar(new Instrucao());
			LogHelper.log("-FINAL- validas " + validCount + " invalidas " + invalidCount + " ciclos " + cicleCount++);		
		}
	}

	public static void liberarEstagio(final String estagio) {
        final Scanner keyboard = new Scanner(System.in);
        LogHelper.log("Pressione para executar " + estagio);
        keyboard.nextLine();
    }
    //PREDICTION
    public static boolean buscarPredicao(Instrucao instrucao) {
        return predicoes[getEnderecoPredicao(stringToInt(instrucao.getOp3()))];
	}

	public static void atualizarPredicao(Instrucao instrucao, boolean validade) {
	    int endereco = getEnderecoPredicao(stringToInt(instrucao.getOp3()));
        predicoes[endereco] = validade;
        if (!validade && instrucao.isValida()) {
            PC = PC - stringToInt(instrucao.getOp3());
            busca.rodar(new Instrucao());
        }
    }

	public static int getEnderecoPredicao(int op) {
	    if (op > 32) {
	        if (op > 100) {
	            op = op % 100;
            }
            op = op % 10;
        }
	    return op;
    }

    public static int stringToInt(String string) {
	    return Integer.parseInt(string == null ? "0" : string);
    }
	
}
