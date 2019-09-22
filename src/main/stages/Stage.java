package main.stages;

import main.model.Instrucao;

public interface Stage {
	
	Instrucao instrucao = new Instrucao();
	
	public void rodar(Instrucao instrucaoARodar);

}
