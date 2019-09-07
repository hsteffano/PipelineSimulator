package helper;

import model.Instrucao;
import model.enumerador.Operacao;

public final class InstructionHelper {

	public static Instrucao mapeiaInstrucao(String linha) {
		final Instrucao instrucao = new Instrucao();
		instrucao.setOpCode(Operacao.valueOf(linha.substring(0, linha.indexOf(' '))));
		instrucao.setOp1(linha.substring(linha.indexOf(instrucao.getOpCode().valor), linha.indexOf(' ')));
		instrucao.setOp2(linha.substring(linha.indexOf(instrucao.getOp1()), linha.indexOf(' ')));
		instrucao.setOp3(linha.substring(linha.indexOf(instrucao.getOp2()), linha.indexOf(' ')));
		
		return instrucao;
	}
	
}
