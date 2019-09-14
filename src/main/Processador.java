package main;

import main.helper.FileHelper;
import main.helper.InstructionHelper;
import main.model.Instrucao;
import main.model.enumerador.Operacao;

import java.util.Scanner;

public class Processador {
	private int[] registradores = new int[32];
	private boolean[] predicoes = new boolean[32];

	private static int PC = 0;
	
	private static int op1 = 0;
	private static int op2 = 0;
	private static int op3 = 0;

	private static int bufferExMem = 0;

	private static int cicleCount = 1;
	private static int validCount = 0;
	private static int invalidCount = 0;

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
            log("ciclos: " + cicleCount++);
		}
	}
	
	private Instrucao busca() {
        liberarEstagio("BUSCA");
        PC++;
		final String linha = FileHelper.lerLinha(PC);
		log("fetched: " + linha);
		Instrucao instrucao = InstructionHelper.mapeiaInstrucao(linha);
		if (instrucao.getOpCode() == Operacao.BEQ && buscarPredicao(instrucao)) {
		    instrucao.setValida(true);
            PC = PC + stringToInt(instrucao.getOp3());
        }
		return instrucao;
	}

    private Instrucao decod(Instrucao instrucao) {
        liberarEstagio("DECOD");
		op1 = stringToInt(instrucao.getOp1());
		op2 = stringToInt(instrucao.getOp2());
		op3 = stringToInt(instrucao.getOp3());
		return instrucao;
	}
	
	private Instrucao exec(Instrucao instrucao) {
        liberarEstagio("EXEC");
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
            boolean validade = op1 == op2;
            if (validade) {validCount++;} else {invalidCount++;}
            atualizarPredicao(instrucao, validade);
			break;

		default:
			System.exit(-1);
			break;
		}
		return instrucao;
	}
	
	private Instrucao mem(Instrucao instrucao) {
        liberarEstagio("MEM");
		//salva em mem
		return instrucao;
	}
	
	private void wb(Instrucao instrucao) {
        liberarEstagio("WB");
		if (instrucao.getOpCode() != Operacao.B) {
			registradores[op1] = bufferExMem;
			log("resultado " + bufferExMem + " validas " + validCount + " invalidas " + invalidCount);
		}
	}

	private void liberarEstagio(String estagio) {
        Scanner keyboard = new Scanner(System.in);
        log("Pressione para executar " + estagio);
        keyboard.nextLine();
    }

    private boolean buscarPredicao(Instrucao instrucao) {
        return predicoes[getEnderecoPredicao(stringToInt(instrucao.getOp3()))];
	}

	private void atualizarPredicao(Instrucao instrucao, boolean validade) {
	    int endereco = getEnderecoPredicao(stringToInt(instrucao.getOp3()));
        predicoes[endereco] = validade;
        if (!validade && instrucao.isValida()) {
            PC = PC - stringToInt(instrucao.getOp3());
            busca();
        }
    }

	private int getEnderecoPredicao(int op) {
	    if (op > 32) {
	        if (op > 100) {
	            op = op % 100;
            }
            op = op % 10;
        }
	    return op;
    }

    private int stringToInt(String string) {
	    return Integer.parseInt(string == null ? "0" : string);
    }

    private void log(String msg) {
		System.out.println(msg);
	}
	
}
