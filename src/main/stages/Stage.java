package main.stages;

import main.model.Instrucao;

public interface Stage {
	
	static Instrucao instrucao = new Instrucao();
	
	public void rodar(Instrucao instrucaoARodar);

}
