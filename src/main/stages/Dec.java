package main.stages;

import main.Processador;
import main.model.Instrucao;

public final class Dec implements Stage {
	
	private static Instrucao instrucao = new Instrucao();
	
	public void rodar(Instrucao instrucaoARodar) {
        Processador.liberarEstagio("DECOD");
		if (!instrucaoARodar.isValida())
			return;
        Processador.op1 = Processador.registradores[Processador.stringToInt(instrucaoARodar.getOp1())];//FIXME
        Processador.op2 = Processador.registradores[Processador.stringToInt(instrucaoARodar.getOp2())];
        Processador.op3 = Processador.registradores[Processador.stringToInt(instrucaoARodar.getOp3())];
		this.instrucao = instrucaoARodar;
	}

	public Instrucao getInstrucao() {
		return instrucao;
	}

	public void setInstrucao(Instrucao instrucao) {
		this.instrucao = instrucao;
	}

}
