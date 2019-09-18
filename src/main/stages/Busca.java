package main.stages;

import main.Processador;
import main.helper.FileHelper;
import main.helper.InstructionHelper;
import main.helper.LogHelper;
import main.model.Instrucao;
import main.model.enumerador.Operacao;

public final class Busca implements Stage {
	
	private static Instrucao instrucao = new Instrucao();
	
	public void rodar(Instrucao instrucaoARodar) {
        Processador.liberarEstagio("BUSCA");
		final String linha = FileHelper.lerLinha(Processador.PC);
		LogHelper.log("fetched: " + linha);
		instrucaoARodar = InstructionHelper.mapeiaInstrucao(linha);
		if (isJump(instrucaoARodar) && Processador.buscarPredicao(instrucaoARodar)) {
		    instrucaoARodar.setValida(true);
		    Processador.PC = Processador.PC + Processador.stringToInt(instrucaoARodar.getOp3());
        }
		this.instrucao = instrucaoARodar;
	}
	
	private boolean isJump(Instrucao instrucao) {
		return instrucao.getOpCode() == Operacao.BEQ || instrucao.getOpCode() == Operacao.B;
	}

	public Instrucao getInstrucao() {
		return instrucao;
	}

	public void setInstrucao(Instrucao instrucao) {
		this.instrucao = instrucao;
	}

}
