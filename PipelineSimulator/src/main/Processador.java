package main;

import main.helper.FileHelper;
import main.helper.InstructionHelper;
import main.model.Instrucao;

public class Processador {

	private String[] memDados;
	private int[] registradores = new int[32];
	
	private static int PC = 0;
	
	private static int op1 = 0;
	private static int op2 = 0;
	private static int op3 = 0;

	private static int buffer = 0;

	public void runPipeline() {
		while (true) {
			wb(
					mem(
							exec(
									decod(
											busca()
									)
							)
					)
			);
		}
	}
	
	private Instrucao busca() {
		System.out.println("BUSCA");
		PC++;
		final String linha = FileHelper.lerLinha(PC);
		return InstructionHelper.mapeiaInstrucao(linha);
	}
	
	private Instrucao decod(Instrucao instrucao) {
		System.out.println("DECOD");
		op1 = Integer.parseInt(instrucao.getOp1());
		op2 = Integer.parseInt(instrucao.getOp2() == null ? "0" : instrucao.getOp2());
		op3 = Integer.parseInt(instrucao.getOp3() == null ? "0" : instrucao.getOp3());
		return instrucao;
	}
	
	private Instrucao exec(Instrucao instrucao) {
		System.out.println("EXEC");
		switch (instrucao.getOpCode()) {
		case ADD:
			buffer = registradores[op2] + registradores[op3];
			break;
			
		case ADDI:
			buffer = registradores[op2] + op3;
			break;

		case SUB:
			buffer = registradores[op2] - registradores[op3];
			break;
			
		case SUBI:
			buffer = registradores[op2] - op3;
			break;
			
		case B:
			PC = op1;
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
		System.out.println("MEM");
		//salva em tal posicao
		return instrucao;
	}
	
	private void wb(Instrucao instrucao) {
		System.out.println("WB");
		registradores[op1] = buffer;
	}
	
}
