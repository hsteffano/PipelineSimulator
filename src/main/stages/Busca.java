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

		switch (instrucaoARodar.getOpCode()) {
			case B:
				bToBeq(instrucaoARodar);
				instrucaoARodar.setValida(true);
				jump(instrucaoARodar.getOp3());
				break;

			case BEQ:
				if (Processador.buscarPredicao(instrucaoARodar)) {
					instrucaoARodar.setValida(true);
					jump(instrucaoARodar.getOp3());
				}
				break;

			default:
				this.instrucao = instrucaoARodar;
				break;
		}

	}
	
	private void jump(String offset) {
		Processador.PC = Processador.PC + Processador.stringToInt(offset);
	}

	private void bToBeq(final Instrucao b) {
		b.setOpCode(Operacao.BEQ);
		b.setOp3(b.getOp1());
		b.setOp1("0");
		b.setOp2("0");
	}

	public Instrucao getInstrucao() {
		return instrucao;
	}

	public void setInstrucao(Instrucao instrucao) {
		this.instrucao = instrucao;
	}

}
