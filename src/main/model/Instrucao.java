package main.model;

import main.model.enumerador.Operacao;

public class Instrucao {

	private Operacao opCode;

	private String op1;
	
	private String op2;
	
	private String op3;
	
	private boolean valida;
	
	public Instrucao() {}
	
	public Instrucao(Operacao opCode, String op1, String op2, String op3, boolean valida) {
		this.opCode = opCode;
		this.op1 = op1;
		this.op2 = op2;
		this.op3 = op3;
		this.valida = valida;
	}

	public Operacao getOpCode() {
		return opCode;
	}

	public void setOpCode(Operacao opCode) {
		this.opCode = opCode;
	}

	public String getOp1() {
		return op1;
	}

	public void setOp1(String op1) {
		this.op1 = op1;
	}

	public String getOp2() {
		return op2;
	}

	public void setOp2(String op2) {
		this.op2 = op2;
	}

	public String getOp3() {
		return op3;
	}

	public void setOp3(String op3) {
		this.op3 = op3;
	}

	public boolean isValida() {
		return valida;
	}

	public void setValida(boolean valida) {
		this.valida = valida;
	}
	
}
