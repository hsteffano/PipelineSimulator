package main;

import java.util.Arrays;
import java.util.Scanner;

import main.helper.LogHelper;
import main.model.Buffer;
import main.model.Instrucao;
import main.stages.Busca;
import main.stages.Dec;
import main.stages.Exec;
import main.stages.Mem;
import main.stages.Wb;

import static main.helper.StringUtils.stringToInt;

public final class Processador {

    public Processador(boolean incluirPredicao) {
        this.incluirPredicao = incluirPredicao;
    }

    public static boolean incluirPredicao;

    public static int[] registradores = new int[32];
    public static Wb wb = new Wb();
    public static Mem mem = new Mem();
    public static Exec exec = new Exec();
    public static Dec dec = new Dec();
    public static Busca busca = new Busca();

	public static int PC = 0;

	public static Buffer bufferBuscaDecod = new Buffer();
	public static Buffer bufferDecodEx = new Buffer();
	public static Buffer bufferExMem = new Buffer();
	public static Buffer bufferMemWb = new Buffer();

	//STATS
	public static int cicleCount = 0;
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
            cicleCount++;
			liberarPipeline();
		}
	}

	private static void liberarPipeline() {
        final Scanner keyboard = new Scanner(System.in);
        LogHelper.log("BUSCA" + busca.getInstrucao().toString());
        LogHelper.log("DEC  " + dec.getInstrucao().toString());
        LogHelper.log("EXEC " + exec.getInstrucao().toString());
        LogHelper.log("MEM  " + mem.getInstrucao().toString());
        LogHelper.log("WB   " + wb.getInstrucao().toString());
        LogHelper.log("ciclos " + cicleCount + " validas " + validCount + " invalidas " + invalidCount);
        LogHelper.log("Pressione para avancar um ciclo");
        LogHelper.log(Arrays.toString(registradores));
        keyboard.nextLine();
    }

    public static void invalidarPipeline() {
        busca.getInstrucao().setValida(false);
        dec.getInstrucao().setValida(false);
        exec.getInstrucao().setValida(false);
    }
	
	public static void jump(String offset) {
		Processador.PC = Processador.PC + stringToInt(offset);
	}
	
}
