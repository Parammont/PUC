/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 1.17
 * @status updated to 3.0
 */
package TrabalhoPratico;

//import TrabalhoPratico.Registros.ApontadorRegistro;
import TrabalhoPratico.Registros.*;
import static TrabalhoPratico.Registros.ApontadorRegistro.getApontador;

public class AnLexico {

    private short i;
    private char c;
    private String Token;
    private String linha;
    private ApontadorRegistro rl;
    private boolean devolver;
    private boolean isIdentificador;
    private boolean isNumero;

    public AnLexico(String s) {
        Token = "";
        linha = s;
        i = 0;
        rl = getApontador();
    }

    public boolean executarProximo() {
        rl.setEndereco(0);
        isIdentificador = false;
        devolver = false;
        isNumero = false;
        Token = "";
        proximoChar();

        if (linha.length() == 0) {
            rl.setEndereco(-1);
            return true;
        }

        procurar();

        return i >= linha.length();
    }

    // Iniciar a procura Considerando o estado 0
    private void procurar() {
        if (c == '0') {
            estado1();
        } else if (c == '.') {
            estado4();
        } else if (c >= '1' && c <= '9') {
            estado5();
        } else if ('(' == c || ')' == c || '*' == c || '+' == c || '-' == c || ';' == c || ',' == c) {
            estado11();
        } else if (c == '&' || c == '|') {
            estado13();
        } else if (c == '>' || c == '=' || c == '!' || c == '<') {
            estado14();
        } else if (c == '/') {
            estado15();
        } else if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
            estado16();
        } else if (c == ' ') {
            //Loop no estado 0
            rl.setEndereco(-1);
            proximoChar();
            procurar();
        } else if (c == '\n') {

        } else {
            erro(c);
        }
    }

    private void estado1() {
        proximoChar();
        isNumero = true;
        if (c == 'x' || c == 'X') {
            estado2();
        } else if (c == '.') {
            estado4();
        } else {    // numero 0 portanto aceito
            devolver = true;
            estado11();
            i--;
        }
    }

    private void estado2() {
        proximoChar();
        if ((c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f') || (c >= '0' && c <= '9')) {
            estado3();
        } else {
            erro(c);
        }
    }

    private void estado3() {
        proximoChar();
        if ((c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f') || (c >= '0' && c <= '9')) {
            estado11();
        } else {
            erro(c);
        }
    }

    private void estado4() {
        proximoChar();
        isNumero = true;
        if (c >= '0' && c <= '9') {
            estado6();
        } else {
            erro(c);
        }
    }

    private void estado5() {
        proximoChar();
        isNumero = true;
        if (c == '.') {
            estado4();
        } else if (c >= '0' && c <= '9') {
            estado7();
        } else if (c == ' ' || c == '\n') {
            estado11();
        } else {
            devolver = true;
            estado11(); // Aceitação
            i--;
        }
    }

    private void estado6() {
        proximoChar();
        if (c >= '0' && c <= '9') {
            estado8();
        } else if (c == ' ' || c == '\n') {
            estado11();
        } else {
            devolver = true;
            estado11(); // Aceitação
            i--;
        }
    }

    private void estado7() {
        proximoChar();
        if (c == '.') {
            estado4();
        } else if (c >= '0' && c <= '9') {
            estado9();
        } else if (c == ' ' || c == '\n') {
            estado11();
        } else {
            devolver = true;
            estado11(); // Aceitação
            i--;
        }
    }

    private void estado8() {
        proximoChar();
        if (c >= '0' && c <= '9') {
            estado10();
        } else if (c == ' ' || c == '\n') {
            estado11();
        } else {
            devolver = true;
            estado11(); // Aceitação
            i--;
        }
    }

    private void estado9() {
        proximoChar();
        if (c == ' ' || c == '\n') {
            estado11();
        } else if (c == '.') {
            estado4();
        } else if (c >= '0' && c <= '9') {
            estado12();
        } else {
            devolver = true;
            estado11(); // Aceitação
            i--;
        }
    }

    private void estado10() {
        proximoChar();
        if (c == ' ' || c == '\n') {
            estado11();
        } else if (c >= '0' && c <= '9') {
            estado12();
        } else {
            devolver = true;
            estado11(); // Aceitação
            i--;
        }
    }

    private void estado12() {
        proximoChar();
        if (c >= '0' && c <= '9') {
            // Erro mais de 5 dígitos
            Principal.parar = true;
            System.out.println("Sequencia invalida: " + Token + c);
            erro(c);
        } else {
            if (!(c == ' ' || c == '\n')) {
                i--;
                devolver = true;
            }
            estado11();
        }
    }

    private void estado13() {
        proximoChar();
        if (Token.equals("&&")) {
            estado11(); // Aceitação
        } else if (Token.equals("||")) {
            estado11(); // Aceitação
        } else {
            erro(c);
        }
    }

    private void estado14() {
        proximoChar();
        if (!(c == '=' || c == ' ')) {  // aceita "=" senão "=="
            i--;
            devolver = true;
        }
        estado11(); // Aceitação
    }

    private void estado15() {
        proximoChar();
        if (c != '/') {
            devolver = true;
            estado11();
            i--;
        } else if (c == '/') {
            estado17();
        }
    }

    private void estado16() {
        proximoChar();
        isIdentificador = true;
        if (c == 32 || c == '\n') {
            estado11(); // Aceitação
        } else if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
            estado16();
        } else {
            devolver = true;
            estado11(); // Aceitação
            i--;
        }

    }

    private void estado17() {
        proximoChar();
        if (c == '\n') {
            procurar();
        } else {
            estado17();
        }

    }

    private void estado11() {
        if (Token != " ") {
            if (devolver) {
                String lex = Token.substring(0, Token.length() - 1);
                inseritTabela(lex);
            } else {
                inseritTabela(Token);
            }
        }
        devolver = false;
    }

    private void proximoChar() {
        if (i < linha.length()) {
            try {
                c = linha.charAt(i);
            } catch (StringIndexOutOfBoundsException ex) {
                Principal.fimArquivo = true;
                Principal.parar = true;
            }
            if (!(c == ' ' || c == '\n')) {
                Token += c;
            }
        } else {
            c = '\n';
        }
        i++;
    }

    private void erro(char e) {
        System.out.println(LeituraArquivo.linha + ":caractere invalido. [" + e + "]");
        Principal.parar = true;
        inseritTabela(e + "");
    }

    private void inseritTabela(String lex) {
        if (Principal.tb.pesquisa(lex) < 0) {  // se não tem na tabela
            Principal.tb.inserir(-1, lex);
        }
        rl.setEndereco(Principal.tb.pesquisa(lex));
        rl.setLinha(LeituraArquivo.linha);
        if (isIdentificador && rl.getEndereco() > 36) {
            Principal.tb.setToken(rl.getEndereco(), "identificador");
        } else if (isNumero) {
            Principal.tb.setToken(rl.getEndereco(), "num");
            int inteiro = 0;
            boolean hexa = false;

            if (lex.charAt(0) == '0' && lex.length() == 4) {
                if (lex.charAt(1) == 'x') {
                    hexa = true;
                    inteiro = Integer.parseInt(("" + lex.charAt(2) + lex.charAt(3)), 16);
                }
            }
            if (!hexa) {
                double decimal = Double.parseDouble(lex);         //pega o numero real(ou inteiro)
                inteiro = (int) decimal;

                if (inteiro != decimal) {
                    decimal = decimal - inteiro;
                    decimal *= 10000;

                    Principal.tb.setDecimal(rl.getEndereco(), (int) decimal);
                }
            }

            Principal.tb.setInteiro(rl.getEndereco(), inteiro);
        }
    }

}
