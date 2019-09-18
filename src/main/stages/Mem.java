package main.stages;

import main.Processador;
import main.model.Instrucao;

public final class Mem implements Stage {
	
	private static Instrucao instrucao = new Instrucao();
	
	public void rodar(Instrucao instrucaoARodar) {
        Processador.liberarEstagio("MEMORIA");
		if (!instrucaoARodar.isValida())
			return;
		//salva em mem		
		this.instrucao = instrucaoARodar;
	}

	public Instrucao getInstrucao() {
		return instrucao;
	}

	public void setInstrucao(Instrucao instrucao) {
		this.instrucao = instrucao;
	}

}
