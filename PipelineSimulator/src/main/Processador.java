package main;

import helper.FileHelper;
import helper.InstructionHelper;
import model.Instrucao;

public class Processador {
	
	private String[] memInstrucoes;
	private String[] memDados;
	private String[] registradores;
	
	private static int PC = 0;
	
	private static int op1 = 0;
	private static int op2 = 0;
	private static int op3 = 0;

	public void pipeline() {
		while (true) {
		}
	}
	
	private Instrucao busca() {
		final String linha = FileHelper.lerLinha(); 
		return InstructionHelper.mapeiaInstrucao(linha);
	}
	
	private Instrucao decod(Instrucao instrucao) {
		return new Instrucao();
	}
	
	private void exec(Instrucao instrucao) {
		switch (instrucao.getOpCode()) {
		case ADD:
			
			break;
			
		case ADDI:
			op1 = op3 + op2;
			break;
			
		case SUBI:
			op1 = op3 - op2;
			break;
			
		case B:
			PC = op1;
			break;

		default:
			break;
		}
	}
	
	private void mem(Instrucao instrucao) {
		//salva em tal posicao
	}
	
	private void wb(Instrucao instrucao) {
		
	}

	public String[] getMemInstrucoes() {
		return memInstrucoes;
	}

	public void setMemInstrucoes(String[] memInstrucoes) {
		this.memInstrucoes = memInstrucoes;
	}
	
}
