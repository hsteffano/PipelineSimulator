package main.stages;

import main.Processador;
import main.model.Instrucao;
import main.model.enumerador.Operacao;

import static main.helper.StringUtils.stringToInt;

public class Wb {

	private Instrucao instrucao = new Instrucao();
	
	public void rodar(Instrucao instrucaoARodar) {
		this.instrucao = instrucaoARodar;
		if (instrucaoARodar.isValida()) {
			Processador.validCount++;
		} else {
			if (instrucaoARodar.getOpCode() != null)
				Processador.invalidCount++; 
			return;
		}
		
		if (instrucaoARodar.getOpCode() != Operacao.B
				&& instrucaoARodar.getOpCode() != Operacao.NOP
				&& instrucaoARodar.getOpCode() != Operacao.BEQ) {
			Processador.registradores[stringToInt(instrucaoARodar.getOp1())] = Processador.bufferMemWb.getTemp1();
		}
	}

	public Instrucao getInstrucao() {
		return instrucao;
	}

	public void setInstrucao(Instrucao instrucao) {
		this.instrucao = instrucao;
	}

}
