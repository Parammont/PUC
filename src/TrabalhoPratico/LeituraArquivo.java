/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 1.17
 * @status updated to 2.0
 */
package TrabalhoPratico;

import TrabalhoPratico.Registros.ApontadorRegistro;
import static TrabalhoPratico.Registros.ApontadorRegistro.getApontador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

public class LeituraArquivo {

    public static int linha = 0;
    static String buffer;
    static BufferedReader in;
    static AnLexico al;
    private static ApontadorRegistro rl;
    private static boolean ultimaLinha;
    private FileOutputStream gravarExe;
    private BufferedWriter gravarAsm;
    private static String nome2;

    public LeituraArquivo() throws IOException {
        String nome = "";

        try {
            // Abrir painel grafico para escolha de arquivo
            JFileChooser chooser = recuperarLocal();
            int response = chooser.showOpenDialog(null);

            File fileDir = chooser.getSelectedFile();
            manterLocal(chooser.getCurrentDirectory());
            in = new BufferedReader(new FileReader(fileDir));

            nome += chooser.getSelectedFile();

            processo();
        } catch (FileNotFoundException ex) {
            System.out.println("erro ao ler arquivo");
            Logger.getLogger(LeituraArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }

        rl = getApontador();

        nome2 = nome; // apenas para teste

        //Gravar Arquivo Final
        nome = nome.substring(0, (nome.length() - 3));
        gravarAsm = new BufferedWriter(new FileWriter(nome + "1.asm"));
        gravarExe = new FileOutputStream(nome + "1.exe");
        //gravarExe2 = new ObjectOutputStream(new FileOutputStream((nome + "2.exe")));
        //ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);
/*
         //new byte[]{35, (byte)Integer.parseInt((neg ? "-" : "")+tmp), (byte)Integer.parseInt((neg ? "-" : "")+temp
         gravarExe(35);
         gravarExe("-10");
         gravarExe("-0000");
         gravarExe(0);
         //gravarExe.write(new byte[]{35, stringForBytes("-10")[0],stringForBytes("-10")[1], stringForBytes("-1")[0],stringForBytes("-1")[1], (byte) 0});
         gravarExe(35);
         gravarExe("10");
         gravarExe("0000");
         gravarExe(2);
         //gravarExe.write(new byte[]{35, stringForBytes("10")[0],stringForBytes("10")[1], stringForBytes("00")[0],stringForBytes("00")[1], (byte) 2});
         gravarExe(35);
         gravarExe("10");
         gravarExe("0000");
         gravarExe(4);
         //gravarExe.write(new byte[]{35, stringForBytes("10")[0],stringForBytes("10")[1], stringForBytes("00")[0],stringForBytes("00")[1], (byte) 4});
         gravarExe(35);
         gravarExe("-10");
         gravarExe("-0000");
         gravarExe(6);
         //gravarExe.write(new byte[]{35, stringForBytes("-10")[0],stringForBytes("-10")[1], stringForBytes("-1")[0],stringForBytes("-1")[1], (byte) 6});
         */
    }

    // Ler a proxima linha enquanto o arquivo existir
    private static void processo() {
        try {
            buffer = in.readLine();
            //System.out.println(buffer + " " + (linha + 1));
            if (buffer != null) {
                al = new AnLexico(buffer);
                linha++;
            } else if (ultimaLinha) {
                buffer = "\n";
                al = new AnLexico(buffer);
                linha++;
                //if (!Principal.fimArquivo) {
                //System.out.println("entrou em parar tudo");
                //Principal.fimArquivo = true;
                //}
            } else {
                System.out.println("ERRRRROOOO Muito estranho em Leitura de Arquivo");
            }

        } catch (IOException ex) {
            Logger.getLogger(LeituraArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // leitura do Token um a um
    public static void Proxima() {
        int cont = 0;
        try {
            if (in.ready() || ultimaLinha) {
                do {
                    if (al.executarProximo()) {
                        processo();
                    }
                    //System.out.println("Teste - " + (rl.getToken().length() == 0) + " " + rl.getToken() + " : " + buffer);

                    if (rl.getEndereco() == 0) {
                        cont++;
                    }
                } while ((rl == null || (rl.getToken().length() == 0) || rl.getEndereco() == 0) && (!(Principal.parar)) && (cont < 10));

            } else {
                ultimaLinha = true;
                al.executarProximo();
            }
        } catch (IOException ex) {
            System.out.println("Erro ao ler próxima linha");
        }
    }

    public static void testeTabela() throws IOException {
        while (in.ready() && (!(Principal.parar))) {
            Proxima();
            //System.out.println("Erro no teste da tabela");
        }
    }
/*
    public void gravarAsm2(String linha) {
        try {
            for (int i = 0; i < linha.length(); i++) {
                if (linha.charAt(i) == '\n') {
                    gravarAsm.newLine();
                }
                gravarAsm.write(linha.charAt(i));
            }

        } catch (IOException ex) {
            System.out.println("Erro ao gravar no arquivo ASM");
        }
    }*/

    public void gravarAsm(String linha) {
        try {
            if (linha.length() > 0) {
                //gravarAsm.writeObject(linha);
                gravarAsm.write(linha);
                gravarAsm.newLine();
            } else {
                System.out.println("Opa erro linha NULL, na linha: " + this.linha);
            }

        } catch (IOException ex) {
            System.out.println("Erro ao gravar no arquivo ASM");
        }

    }

    //Grava um dado na posição de memoria (1/2 posição de memoria - 8 bits)
    public void gravarExe(int original) {
        try {
            if (original < 0) {
                original *= -1;
                original--;
                gravarExe.write((byte) (255 - (original % 256)));
                gravarExe.write((byte) (255 - (original / 256)));
            } else {
                gravarExe.write((byte) (original % 256));
                gravarExe.write((byte) (original / 256));
            }
        } catch (IOException ex) {
            System.out.println("Erro ao gravar no arquivo EXE");
        }
    }

    //Grava um dado na posição de memoria (1 posição de memoria - 16 bits)
    public void gravarExe(String primeiro) {
        try {
            int original = Integer.parseInt(primeiro);
            if (original < 0) {
                original *= -1;
                original--;
                gravarExe.write((byte) (255 - (original % 256)));
                gravarExe.write((byte) (255 - (original / 256)));
            } else {
                gravarExe.write((byte) (original % 256));
                gravarExe.write((byte) (original / 256));
            }
            //gravarExe.write(0);
        } catch (IOException ex) {
            System.out.println("Erro ao gravar no arquivo EXE");
        }
    }

    //Grava um dado na posição de memoria (2 posição de memoria - 32 bits)
/*    public void gravarExe(String primeiro, String segundo) {
     try {
     int original = Integer.parseInt(primeiro);
     if (original < 0) {
     original *= -1;
     gravarExe.write((byte) (original % 255));
     gravarExe.write((byte) (original / 255));
     }
     byte num[] = stringForBytes(primeiro);
     gravarExe.write(num[0]);
     gravarExe.write(num[1]);
     if (num[1] < 0) {
     num = stringForBytes(segundo);
     gravarExe.write(num[0] + 255);
     gravarExe.write(num[1] + 0);
     } else {
     num = stringForBytes(segundo);
     gravarExe.write(num[0]);
     gravarExe.write(num[1]);
     }

     gravarExe.write(0);
     gravarExe.write(0);
     } catch (IOException ex) {
     System.out.println("Erro ao gravar no arquivo EXE");
     }
     }
     */
    public void fecharArquivo() {
        try {
            gravarAsm.flush();
            gravarAsm.close();
            gravarExe.flush();
            gravarExe.close();

            // **************Apenas um teste ****************
            FileInputStream teste1, teste2;
            String testenome = nome2.substring(0, (nome2.length() - 3));
            teste1 = new FileInputStream(testenome + ".exe");
            teste2 = new FileInputStream(testenome + "1.exe");

            int numTeste1, numTeste2;
            boolean teste = true;//false;           // coloque false para exibir apartir da linha errada
            for (int i = 0; i < 800; i++) {
                if (i == 190) {
                    System.out.println("color");
                }
                if (i == 582) {
                    System.out.println("fim da declaração");
                }
                numTeste1 = teste1.read();
                numTeste2 = teste2.read();
                if (numTeste1 != numTeste2 && !teste) {
                    teste = true;
                    System.out.println("linha: " + i);
                }
                if (teste) {
                    System.out.println(">>>> " + numTeste1 + " == " + numTeste2);
                }
            }

            //***********************************************
        } catch (FileNotFoundException ex) {
            System.out.println("Erro ao salvar arquivo (local do arquivo não foi salvo)");
        } catch (IOException ex) {
            Logger.getLogger(LeituraArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void manterLocal(File fileDir) {
        try {
            FileOutputStream arquivoGrav = new FileOutputStream("./saida.dat");
            ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

            objGravar.writeObject(fileDir);
            objGravar.flush();
            objGravar.close();
            arquivoGrav.flush();
            arquivoGrav.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Erro ao salvar arquivo (local do arquivo não foi salvo)");
        } catch (IOException ex) {
            Logger.getLogger(LeituraArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private JFileChooser recuperarLocal() {
        JFileChooser chooser = new JFileChooser(".");

        try {
            FileInputStream arquivoLeitura = new FileInputStream("./saida.dat");
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
            String dir = objLeitura.readObject().toString();
            chooser = new JFileChooser(dir);

            objLeitura.close();
            arquivoLeitura.close();

        } catch (FileNotFoundException ex) {
            // Arquivo não encontrado
        } catch (IOException ex2) {
            System.out.println("Erro IOException");
            Logger.getLogger(LeituraArquivo.class.getName()).log(Level.SEVERE, null, ex2);
        } catch (ClassNotFoundException ex2) {
            System.out.println("Erro ao recuperar o local");
        }

        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        return chooser;
    }

    // um teste que pega a mString com numero de 8 digitos e tranforma em numeros em 2 conjuntos de bytes
    private byte[] stringForBytes(String palabra) {
        int numero = Integer.parseInt(palabra);
        byte resp[] = new byte[4];

        resp[0] = (byte) (numero / 255);
        resp[1] = (byte) (numero % 255);

        return resp;
    }

}
