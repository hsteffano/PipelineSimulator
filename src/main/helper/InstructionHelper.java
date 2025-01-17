package main.helper;

import main.model.Instrucao;
import main.model.enumerador.Operacao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionHelper {

	public static Instrucao mapeiaInstrucao(String linha) {
		Instrucao instrucao = new Instrucao();
		if (linha.isEmpty())
			return new Instrucao();
		int opCodeEnd = linha.indexOf(' ');
		instrucao.setOpCode(Operacao.valueOf(linha.substring(0, opCodeEnd).toUpperCase()));
		
		if (instrucao.getOpCode() != Operacao.NOP) {
			List<String> parametros = new ArrayList<>();
			Matcher matcher = Pattern.compile("-?[0-9]+").matcher(linha.substring(opCodeEnd, linha.length() - 1));
			while (matcher.find()) {
				parametros.add(matcher.group());
			}
	
			instrucao.setOp1(parametros.get(0));
			if (parametros.size() > 1) {
				instrucao.setOp2(parametros.get(1));
				instrucao.setOp3(parametros.get(2));
			}
		}

		return instrucao;
	}

}
