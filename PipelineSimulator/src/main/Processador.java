package main;

import main.helper.FileHelper;
import main.helper.InstructionHelper;
import main.model.Instrucao;
import main.model.enumerador.Operacao;

import java.util.Scanner;

public class Processador {
	private int[] registradores = new int[32];
	
	private static int PC = 0;
	
	private static int op1 = 0;
	private static int op2 = 0;
	private static int op3 = 0;

	private static int bufferExMem = 0;

	private static int cicleCount = 0;

	public void runPipeline() {
		while (true) { //Clock
			wb(
					mem(
							exec(
									decod(
											busca()
									)
							)
					)
			);
            System.out.println(cicleCount++);
		}
	}
	
	private Instrucao busca() {
        capturarKey("BUSCA");
        PC++;
		final String linha = FileHelper.lerLinha(PC);
		return InstructionHelper.mapeiaInstrucao(linha);
	}
	
	private Instrucao decod(Instrucao instrucao) {
        capturarKey("DECOD");
		op1 = Integer.parseInt(instrucao.getOp1());
		op2 = Integer.parseInt(instrucao.getOp2() == null ? "0" : instrucao.getOp2());
		op3 = Integer.parseInt(instrucao.getOp3() == null ? "0" : instrucao.getOp3());
		return instrucao;
	}
	
	private Instrucao exec(Instrucao instrucao) {
        capturarKey("EXEC");
		switch (instrucao.getOpCode()) {
		case ADD:
			bufferExMem = registradores[op2] + registradores[op3];
			break;
			
		case ADDI:
			bufferExMem = registradores[op2] + op3;
			break;

		case SUB:
			bufferExMem = registradores[op2] - registradores[op3];
			break;
			
		case SUBI:
			bufferExMem = registradores[op2] - op3;
			break;
			
		case B:
			PC = PC + op1;
			break;

		case BEQ:
			if (op1 == op2) {
				PC = PC + op3;
			}
			break;

		default:
			System.exit(-1);
			break;
		}
		return instrucao;
	}
	
	private Instrucao mem(Instrucao instrucao) {
        capturarKey("MEM");
		//salva em tal posicao
		return instrucao;
	}
	
	private void wb(Instrucao instrucao) {
        capturarKey("WB");
		if (instrucao.getOpCode() != Operacao.B) {
			registradores[op1] = bufferExMem;
		}
	}

	private void capturarKey(String estagio) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Pressione para executar " + estagio);
        keyboard.nextLine();
    }
	
}
