package main.stages;

import main.Processador;
import main.model.Instrucao;
import main.model.enumerador.Operacao;

public final class Exec implements Stage {
	
	private static Instrucao instrucao = new Instrucao();
	
	public void rodar(Instrucao instrucaoARodar) {
        Processador.liberarEstagio("EXEC");
		if (!instrucaoARodar.isValida())
			return;
		switch (instrucaoARodar.getOpCode()) {
		case ADD:
			Processador.bufferExMem = Processador.op2 + Processador.op3;
			break;
			
		case ADDI:
			Processador.bufferExMem = Processador.op2 + Processador.op3;
			break;

		case SUB:
			Processador.bufferExMem = Processador.op2 - Processador.op3;
			break;
			
		case SUBI:
			Processador.bufferExMem = Processador.op2 - Processador.op3;
			break;
			
		case B:
			Processador.PC = Processador.PC + Processador.op1;
			break;

		case BEQ:
            boolean validade = Processador.op1 == Processador.op2;
            if (validade) {Processador.validCount++;} else {Processador.invalidCount++;}
            Processador.atualizarPredicao(instrucaoARodar, validade);
			break;

		default:
			break;
		}
		this.instrucao = instrucaoARodar;
	}

	public Instrucao getInstrucao() {
		return instrucao;
	}

	public void setInstrucao(Instrucao instrucao) {
		this.instrucao = instrucao;
	}

}
