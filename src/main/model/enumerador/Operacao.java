package main.model.enumerador;

public enum Operacao {
	ADD("add"), ADDI("addi"), SUB("sub"), SUBI("subi"), BEQ("beq"), B("b"), NOP("nop");    
	
	public final String valor;
 
    private Operacao(String valor) {
        this.valor = valor;
    }
}
