package main.helper;

import main.model.Instrucao;
import main.model.enumerador.Operacao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InstructionHelper {

	public static Instrucao mapeiaInstrucao(String linha) {
		final Instrucao instrucao = new Instrucao();
		final int opCodeEnd = linha.indexOf(' ');
		instrucao.setOpCode(Operacao.valueOf(linha.substring(0, opCodeEnd).toUpperCase()));

		final List<String> parametros = new ArrayList<>();
		final Matcher matcher = Pattern.compile("-?[0-9]").matcher(linha.substring(opCodeEnd, linha.indexOf('#')));
		while (matcher.find()) {
			parametros.add(matcher.group());
		}

		instrucao.setOp1(parametros.get(0));
		if (parametros.size() > 1) {
			instrucao.setOp2(parametros.get(1));
			instrucao.setOp3(parametros.get(2));
		}

		return instrucao;
	}

}
