/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 1.0
 */
package TrabalhoPratico.AnSemantico.Retrocorreção;

import TrabalhoPratico.AnSemantico.Rotulo;

public class dados {
    private byte instrucao;
    private char reg1;
    private char reg2;
    private boolean x;
    private int imed;
    private Rotulo rot;

    public dados (int instrucao) {
        
    }
    
    public dados (int instrucao, String regD, String regO) {
        
    }
    
    public dados (int instrucao, String regD, int Imed) {
        
    }
    
    public dados (int instrucao, String regD, int Imed, int Imed2) {
        
    }
    
    public dados (int instrucao, String regD, Rotulo rot) {
        
    }
    
    public dados (int instrucao, Rotulo rot) {
        
    }
    
    public dados (int instrucao, String regD) {
        
    }
    
    public byte getInstrucao() {
        return instrucao;
    }

    public char getReg1() {
        return reg1;
    }

    public char getReg2() {
        return reg2;
    }

    public boolean isX() {
        return x;
    }

    public int getImed() {
        return imed;
    }

    public Rotulo getRot() {
        return rot;
    }
    
    
}
