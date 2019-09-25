package main.stages;

import main.Predicao;
import main.Processador;
import main.helper.FileHelper;
import main.helper.InstructionHelper;
import main.model.Instrucao;
import main.model.enumerador.Operacao;

import static main.helper.StringUtils.stringToInt;

public final class Busca implements Stage {
	
	private Instrucao instrucao = new Instrucao();
	
	public void rodar(Instrucao instrucaoARodar) {
		final String linha = FileHelper.lerLinha(Processador.PC);
		instrucaoARodar = InstructionHelper.mapeiaInstrucao(linha);

		if (instrucaoARodar.getOpCode() != null) {	
			switch (instrucaoARodar.getOpCode()) {
				case B:
					bToBeq(instrucaoARodar);
					instrucaoARodar.setValida(true);
					Processador.jump(instrucaoARodar.getOp3());
					break;
	
				case BEQ:
					if (Predicao.buscarPredicao(instrucaoARodar)) {
						instrucaoARodar.setValida(true);
						Processador.jump(instrucaoARodar.getOp3());
					}
					break;
	
				default:
					instrucaoARodar.setValida(true);
					break;
			}
		}
		this.instrucao = instrucaoARodar;
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
