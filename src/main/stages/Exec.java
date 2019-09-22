package main.stages;

import main.Processador;
import main.model.Instrucao;

public final class Exec implements Stage {
	
	private static Instrucao instrucao = new Instrucao();
	
	public void rodar(Instrucao instrucaoARodar) {
        Processador.liberarEstagio("EXEC");
		if (!instrucaoARodar.isValida())
			return;
		switch (instrucaoARodar.getOpCode()) {
		case ADD:
			Processador.bufferExMem.setTemp1(Processador.bufferDecodEx.getTemp2() + Processador.bufferDecodEx.getTemp3());
			break;
			
		case ADDI:
			Processador.bufferExMem.setTemp1(Processador.bufferDecodEx.getTemp2() + Processador.bufferDecodEx.getTemp3());
			break;

		case SUB:
			Processador.bufferExMem.setTemp1(Processador.bufferDecodEx.getTemp2() - Processador.bufferDecodEx.getTemp3());
			break;

		case SUBI:
			Processador.bufferExMem.setTemp1(Processador.bufferDecodEx.getTemp2() - Processador.bufferDecodEx.getTemp3());
			break;

		case B:
			Processador.PC = Processador.PC + Processador.bufferDecodEx.getTemp1();
			break;

		case BEQ:
            boolean validade = Processador.bufferDecodEx.getTemp1() == Processador.bufferDecodEx.getTemp2();
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