package main;

import main.model.Instrucao;

import static main.helper.StringUtils.stringToInt;

public final class Predicao {
    private static boolean[] predicoes = new boolean[32];

    public static boolean buscarPredicao(Instrucao instrucao) {
        if (Processador.incluirPredicao)
            return predicoes[getEnderecoPredicao(stringToInt(instrucao.getOp3()))];
        else
            return true;
    }

    public static void atualizarPredicao(Instrucao instrucao, boolean validade) {
        if (!validade && instrucao.isValida()) {
            int endereco = getEnderecoPredicao(stringToInt(instrucao.getOp3()));
            predicoes[endereco] = false;
            Processador.PC = Processador.PC - stringToInt(instrucao.getOp3());
        }
    }

    private static int getEnderecoPredicao(int op) {
        if (op > 32) {
            if (op > 100) {
                op = op % 100;
            }
            op = op % 10;
        }
        return op;
    }
}
