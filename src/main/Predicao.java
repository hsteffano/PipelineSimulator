package main;

import main.model.Instrucao;

import static main.helper.StringUtils.stringToInt;

public final class Predicao {
    private static boolean[] predicoes = new boolean[32];

    public static boolean buscarPredicao(Instrucao instrucao) {
        return predicoes[getEnderecoPredicao(stringToInt(instrucao.getOp3()))];
    }

    public static void atualizarPredicao(Instrucao instrucao, boolean validade) {
        int endereco = getEnderecoPredicao(stringToInt(instrucao.getOp3()));
        predicoes[endereco] = validade;
        if (!validade && instrucao.isValida()) {
            Processador.PC = Processador.PC - stringToInt(instrucao.getOp3());
            Processador.busca.rodar(new Instrucao());
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
