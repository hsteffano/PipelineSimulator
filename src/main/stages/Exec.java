package main.stages;

import main.Predicao;
import main.Processador;
import main.model.Instrucao;

public final class Exec implements Stage {
	
	private Instrucao instrucao = new Instrucao();
	
	public void rodar(Instrucao instrucaoARodar) {
		if (instrucaoARodar.getOpCode() != null) {
			switch (instrucaoARodar.getOpCode()) {
				case ADD:
					Processador.bufferExMem.setTemp1(Processador.bufferDecodEx.getTemp2() + Processador.bufferDecodEx.getTemp3());
					break;
					
				case ADDI:
					Processador.bufferExMem.setTemp1(Processador.bufferDecodEx.getTemp2() + Processador.bufferDecodEx.getTemp3());
					break;
		
				case SUB:
					Processador.bufferExMem.setTemp1(Processador.bufferDecodEx.getTemp2() - Processador.bufferDecodEx.getTemp3());
					break;
		
				case SUBI:
					Processador.bufferExMem.setTemp1(Processador.bufferDecodEx.getTemp2() - Processador.bufferDecodEx.getTemp3());
					break;
		
				case BEQ:
		            boolean validade = Processador.bufferDecodEx.getTemp1() == Processador.bufferDecodEx.getTemp2();
					Predicao.atualizarPredicao(instrucaoARodar, validade); 
			        if (!Processador.incluirPredicao && instrucao.isValida()) {
			        	Processador.jump(instrucao.getOp3());
			        	Processador.invalidarPipeline();
			        }
					break;
		
				default:
					break;
			}
		}
		this.instrucao = instrucaoARodar;
	}

	public Instrucao getInstrucao() {
		return instrucao;
	}

	public void setInstrucao(Instrucao instrucao) {
		this.instrucao = instrucao;
	}

}
