package banco;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Suporte
 */
public class ServerDatabase {

    public static final ArrayList<ArrayList<Conta>> contas;
    public static final int N = 100;

    //iniciar o array estatico
    static {
        contas = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            contas.add(new ArrayList<Conta>());
        }
    }

    public static int hashCode(String md5) {
        BigInteger bi = new BigInteger(md5, 16);
        BigInteger m = new BigInteger(Integer.toString(N), 10);
        int pos;
        pos = bi.mod(m).intValue();
        return pos;
    }
    
    public static void insereConta(Conta conta) {
        String md5conta=conta.getMd5();
        int posicaoTabHash = ServerDatabase.hashCode(md5conta);
        contas.get(posicaoTabHash).add(conta);
    }
    
    public static Conta getConta(String md5)
    {
        int posicaoTabHash = ServerDatabase.hashCode(md5);
        for(Conta i : contas.get(posicaoTabHash))
        {
            if(md5.equals(i.getMd5()))
            {
                return(i);
            }
        }
        return null;
    }

    public static void test3() {
        Conta c = new Conta("1234", "2222", "1245");
        ServerDatabase.insereConta(c);
        String chave = SecurityProvider.md5ToServer(c);
        System.out.println(chave);
        Conta conta = ServerDatabase.getConta(chave);
        System.out.println(conta);
    }

}
