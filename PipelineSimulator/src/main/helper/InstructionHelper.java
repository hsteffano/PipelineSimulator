package main.helper;

import main.model.Instrucao;
import main.model.enumerador.Operacao;

public final class InstructionHelper {

	public static Instrucao mapeiaInstrucao(String linha) {
		final Instrucao instrucao = new Instrucao();
		final int opCodeEnd = linha.indexOf(' ');
		instrucao.setOpCode(Operacao.valueOf(linha.substring(0, opCodeEnd).toUpperCase()));

		final int op1Start = linha.indexOf('t') + 1;
		final int op1End = linha.indexOf(',');
		instrucao.setOp1(linha.substring(op1Start, op1End));

		final int op2Start = linha.indexOf('t', op1End) + 1;
		final int op2End = linha.indexOf(',', op1End + 1);
		instrucao.setOp2(linha.substring(op2Start, op2End));

		final int op3Start = linha.indexOf(' ', op2End + 1);
		final int op3End = linha.indexOf(' ', op3Start + 1);
		instrucao.setOp3(linha.substring(op3Start + 1, op3End));
		
		return instrucao;
	}

}
