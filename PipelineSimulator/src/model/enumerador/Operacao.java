package model.enumerador;

public enum Operacao {
	ADD("add"), ADDI("addi"), SUB("sub"), SUBI("subi"), BEQ("beq"), B("b");    
	
	public final String valor;
 
    private Operacao(String valor) {
        this.valor = valor;
    }
}
