/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 2.0
 */
package TrabalhoPratico.AnSemantico;

import TrabalhoPratico.Principal;
import TrabalhoPratico.Registros.ApontadorRegistro;
import static TrabalhoPratico.Registros.ApontadorRegistro.getApontador;
import TrabalhoPratico.Registros.RegistroLexico;
import java.util.ArrayList;

public class Instrucoes {

    public static ApontadorRegistro rl = getApontador();
    public static RegistroLexico rlAntigo = new RegistroLexico();
    public static int contTemp = 0;          //contador de temporario
    public static int contRot = 0;           //contador de rotulos
    public static int PC = 0;
    private static ArrayList<Rotulo> dadoRotulo;

    public static int DS = 0;           //armazena o endereço da área de dados da memória principal


    //ADD	1	RegD <- RegD + RegO	ADD A,B
    public void ADD(String regD, String regO) {
        Principal.arquivo.gravarAsm("ADD " + regD + "," + regO);
        Principal.arquivo.gravarExe(1);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(getReg(regO));
        PC += 3;
    }
    
    //ADDF	2	RegD <- RegD + RegO	ADDF A,B
    public void ADDF(String regD, String regO) {
        Principal.arquivo.gravarAsm("ADDF " + regD + "," + regO);
        Principal.arquivo.gravarExe(2);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(getReg(regO));
        PC += 3;
    }
    
    //ADI	3	RegD <- RegD + Imed	ADI A,#1
    public void ADI(String regD, int Imed) {
        Principal.arquivo.gravarAsm("ADI " + regD + ",#" + Imed);
        Principal.arquivo.gravarExe(2);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(Imed);
        PC += 3;
    }
    
    //ADIF	4	RegD <- RegD + Imed	ADIF A,#1.0
    public void ADIF(String regD, String Imed) {
        Principal.arquivo.gravarAsm("ADI " + regD + ",#" + Imed);
        Principal.arquivo.gravarExe(2);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(Imed);/// Aqui tem que separar parte inteira de fração *************************************************************************************
        PC += 4;
    }
    
    //BNG	5	se (Reg<0) PC <- CS+Desl	BNG A,10(CS)
    public void BNG() {
        //????????
    }

    //BNGF	6	se (Reg<0) PC <- CS+Desl	BNGF A,10(CS)
BNN	7	se (Reg≥0) PC <- CS+Desl	BNN A,10(CS)
BNNF	8	se (Reg≥0) PC <- CS+Desl 	BNNF A,10(CS)
BNP	9	se (Reg≤0) PC <- CS+Desl	BNP A,10(CS)
BNPF	10	se (Reg≤0) PC <- CS+Desl	BNPF A,10(CS)
BNZ	11	se (Reg≠0) PC <- CS+Desl	BNZ A,10(CS)
BNZF	12	se (Reg≠0) PC <- CS+Desl 	BNZF A,10(CS)
BPS	13	se (Reg>0) PC <- CS+Desl	BPS A,10(CS)
BPSF	14	se (Reg>0) PC <- CS+Desl	BPSF A,10(CS)
BZR	15	se (Reg=0) PC <- CS+Desl	BZR A,10(CS)
BZRF	16	se (Reg=0) PC <- CS+Desl	BZRF A,10(CS)



    public void STIF() {
        String[] print = stif(rlAntigo.getInteiro(), rlAntigo.getDecimal());
        Principal.arquivo.gravarAsm(print[0]);
        Principal.arquivo.gravarExe(35);
        //Principal.arquivo.gravarExe(print[1]);
        for (int i = 1; i < print.length; i++) {
            Principal.arquivo.gravarExe(print[i]);
        }
    }

    public String[] stif(int inte, int deci) {
        String[] print = new String[4];

        String s_int = "" + inte;
        rlAntigo.setEndMen(DS);
        String s_dec = paraString(deci);
        print[0] = "STIF  #" + s_int + "." + s_dec + " , " + rlAntigo.getEndMen() + "(DS)";
        print[1] = s_int;
        print[2] = s_dec;
        print[3] = "" + rlAntigo.getEndMen();
        DS += 2;
        PC += 3;
        return print;
    }
    

    public void STI() {
        String[] print = sti(rlAntigo.getInteiro());
        Principal.arquivo.gravarAsm(print[0]);
        Principal.arquivo.gravarExe(34);
        Principal.arquivo.gravarExe(print[1]);
        Principal.arquivo.gravarExe(print[2]);
    }

    public String[] sti(int num) {
        String[] print = new String[3];

        String s_int = "" + num;
        print[0] = "STI #" + s_int + " , " + DS + "(DS)";
        rlAntigo.setEndMen(DS);
        print[1] = s_int;
        print[2] = "" + DS;
        DS += 1;
        PC += 3;
        return print;
    }

    private String paraString(int num) {
        String temp = "" + num;
        while (temp.length() < 4) {
            temp = "0" + temp;
        }

        return temp;
    }

    public int novoTemp(int tamanho) {
        DS += tamanho;
        return (DS - tamanho);
    }

    public Rotulo novoRot() {
        ++contRot;
        Rotulo resp = new Rotulo("ROT" + contRot, PC, -1);
        dadoRotulo.add(resp);
        return resp;
    }

    private int getReg(String regX) {
        switch (regX) {
            case "A":
                return 1;
            case "AX":
                return 1;
            case "B":
                return 2;
            case "BX":
                return 2;
            case "C":
                return 3;
            case "CX":
                return 3;
            case "D":
                return 4;
            case "DX":
                return 4;
            case "E":
                return 5;
            case "EX":
                return 5;
            case "F":
                return 6;
            case "FX":
                return 6;
            default:
                return 0;
        }
    }
}