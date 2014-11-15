/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 1.0
 */

package TrabalhoPratico.Registros;


//Metodo Singleton -- http://pt.wikipedia.org/wiki/Singleton
public class ApontadorRegistro extends RegistroLexico{
    private static ApontadorRegistro ponteiro = null;
    
    // Construtor privado. Suprime o construtor público padrao.
    private ApontadorRegistro () {
        new RegistroLexico();
    }
    
    static {
        if (ponteiro == null) {
            ponteiro = new ApontadorRegistro();
        }
    }
    
    // Método público estático de acesso único ao objeto!
    public static ApontadorRegistro getApontador () {
        return ponteiro;
    }
}
