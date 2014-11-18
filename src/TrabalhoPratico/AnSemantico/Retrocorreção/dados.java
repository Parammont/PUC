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
    private char reg [];
    private int imed [];

    public dados(int instrucao) {
        this.instrucao = (byte)instrucao;
    }

    public dados(int instrucao, String regD, String regO) {
        this.instrucao = (byte)instrucao;
        this.reg = new char[2];
        this.reg [0] = regD.charAt(0);
        this.reg [1] = regO.charAt(0);
    }

    public dados(int instrucao, String regD, int Imed) {
        this.instrucao = (byte)instrucao;
        this.reg = new char[1];
        this.reg [0] = regD.charAt(0);
        this.imed = new int[1];
        this.imed [0] = Imed;
    }

    public dados(int instrucao, int Imed, int Imed2) {
        this.instrucao = (byte)instrucao;
        this.imed = new int[2];
        this.imed [0] = Imed;
        this.imed [1] = Imed2;
    }

    public dados(int instrucao, int Imed) {
        this.instrucao = (byte)instrucao;
        this.imed = new int[1];
        this.imed [0] = Imed;
    }

    public dados(int instrucao, String regD, int Imed, int Imed2) {
        this.instrucao = (byte)instrucao;
        this.reg = new char[1];
        this.reg [0] = regD.charAt(0);
        this.imed = new int[2];
        this.imed [0] = Imed;
        this.imed [1] = Imed2;
    }

    public dados(int instrucao, int Imed, int Imed1, int Imed2) {
        this.instrucao = (byte)instrucao;
        this.imed = new int[3];
        this.imed [0] = Imed;
        this.imed [1] = Imed1;
        this.imed [2] = Imed2;
    }

    public dados(int instrucao, String regD) {
        this.instrucao = (byte)instrucao;
        this.reg = new char[1];
        this.reg [0] = regD.charAt(0);
    }

    
    public byte getInstrucao() {
        return instrucao;
    }

    public String getReg1() {
        if (reg.length > 0) {
            return ""+ reg [0];
        }
        return "" + 168;
    }

    public String getReg2() {
        if (reg.length > 1) {
            return ""+ reg [1];
        }
        return "" + 168;
    }

    public int getImed() {
        return imed[0];
    }

    public int getImed1() {
        return imed[1];
    }

    public int getImed2() {
        return imed[2];
    }

}
