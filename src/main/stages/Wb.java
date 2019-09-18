package main.stages;

import main.Processador;
import main.model.Instrucao;
import main.model.enumerador.Operacao;

public final class Wb implements Stage {
	
	private static Instrucao instrucao = new Instrucao();
	
	public void rodar(Instrucao instrucaoARodar) {
        Processador.liberarEstagio("WB");
		if (!instrucaoARodar.isValida())
			return;
		if (instrucaoARodar.getOpCode() != Operacao.B) {
			Processador.registradores[Processador.op1] = Processador.bufferExMem;
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
