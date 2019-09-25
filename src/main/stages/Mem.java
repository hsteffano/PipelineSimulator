package main.stages;

import main.Processador;
import main.model.Instrucao;

public final class Mem implements Stage {
	
	private Instrucao instrucao = new Instrucao();
	
	public void rodar(Instrucao instrucaoARodar) {
		Processador.bufferMemWb = Processador.bufferExMem;
		this.instrucao = instrucaoARodar;
		if (!instrucaoARodar.isValida())
			return;
		//salva em mem
	}

	public Instrucao getInstrucao() {
		return instrucao;
	}

	public void setInstrucao(Instrucao instrucao) {
		this.instrucao = instrucao;
	}

}
