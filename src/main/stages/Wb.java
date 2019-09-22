package main.stages;

import main.Processador;
import main.model.Instrucao;
import main.model.enumerador.Operacao;

import static main.helper.StringUtils.stringToInt;

public final class Wb implements Stage {

	private Instrucao instrucao = new Instrucao();
	
	public void rodar(Instrucao instrucaoARodar) {
		if (!instrucaoARodar.isValida())
			return;
		if (instrucaoARodar.getOpCode() != Operacao.B) {
			Processador.registradores[stringToInt(instrucaoARodar.getOp1())] = Processador.bufferMemWb.getTemp1();
		}
		if (instrucaoARodar.isValida()) {Processador.validCount++;} else {Processador.invalidCount++;}
		this.instrucao = instrucaoARodar;
	}

	public Instrucao getInstrucao() {
		return instrucao;
	}

	public void setInstrucao(Instrucao instrucao) {
		this.instrucao = instrucao;
	}

}
