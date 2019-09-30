package main.stages;

import main.Processador;
import main.model.Instrucao;

public class Mem implements Stage {
	
	private Instrucao instrucao = new Instrucao();
	
	public void rodar(Instrucao instrucaoARodar) {
		Processador.bufferMemWb.setTemp1(Processador.bufferExMem.getTemp1());
		Processador.bufferMemWb.setTemp2(Processador.bufferExMem.getTemp2());
		Processador.bufferMemWb.setTemp3(Processador.bufferExMem.getTemp3());
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
