package main.stages;

import main.Processador;
import main.model.Instrucao;

public final class Dec implements Stage {
	
	private static Instrucao instrucao = new Instrucao();
	
	public void rodar(Instrucao instrucaoARodar) {
        Processador.liberarEstagio("DECOD");

		if (!instrucaoARodar.isValida())
			return;

        Processador.bufferDecodEx.setTemp1(Processador.registradores[Processador.stringToInt(instrucaoARodar.getOp1())]);//FIXME
        Processador.bufferDecodEx.setTemp2(Processador.registradores[Processador.stringToInt(instrucaoARodar.getOp2())]);
        Processador.bufferDecodEx.setTemp3(Processador.stringToInt(instrucaoARodar.getOp3()));

        instrucao = instrucaoARodar;
	}

	public Instrucao getInstrucao() {
		return instrucao;
	}

	public void setInstrucao(Instrucao instrucao) {
		this.instrucao = instrucao;
	}

}
