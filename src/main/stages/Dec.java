package main.stages;

import main.Processador;
import main.model.Instrucao;

import static main.helper.StringUtils.stringToInt;

public final class Dec implements Stage {
	
	private Instrucao instrucao = new Instrucao();
	
	public void rodar(Instrucao instrucaoARodar) {
        Processador.bufferDecodEx.setTemp1(Processador.registradores[stringToInt(instrucaoARodar.getOp1())]);
        Processador.bufferDecodEx.setTemp2(Processador.registradores[stringToInt(instrucaoARodar.getOp2())]);
        Processador.bufferDecodEx.setTemp3(stringToInt(instrucaoARodar.getOp3()));
        this.instrucao = instrucaoARodar;
	}

	public Instrucao getInstrucao() {
		return instrucao;
	}

	public void setInstrucao(Instrucao instrucao) {
		this.instrucao = instrucao;
	}

}
