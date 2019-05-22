
package banco;

import java.util.ArrayList;

/**
 *
 * @author matheus
 */
public class Database {
    public static final ArrayList<Letra> caracteres;

    static {
        caracteres = new ArrayList<Letra>();
        caracteres.add(new Letra("a"));
        caracteres.add(new Letra("b"));
        caracteres.add(new Letra("c"));
        caracteres.add(new Letra("d"));
        caracteres.add(new Letra("e"));
        caracteres.add(new Letra("f"));
        caracteres.add(new Letra("g"));
        caracteres.add(new Letra("h"));
        caracteres.add(new Letra("i"));
        caracteres.add(new Letra("j"));
        caracteres.add(new Letra("k"));
        caracteres.add(new Letra("l"));
        caracteres.add(new Letra("m"));
        caracteres.add(new Letra("n"));
        caracteres.add(new Letra("o"));
        caracteres.add(new Letra("p"));
        caracteres.add(new Letra("q"));
        caracteres.add(new Letra("r"));
        caracteres.add(new Letra("s"));
        caracteres.add(new Letra("t"));
        caracteres.add(new Letra("u"));
        caracteres.add(new Letra("v"));
        caracteres.add(new Letra("w"));
        caracteres.add(new Letra("x"));
        caracteres.add(new Letra("y"));
        caracteres.add(new Letra("z"));
        caracteres.add(new Letra(" "));
        caracteres.add(new Letra("A"));
        caracteres.add(new Letra("B"));
        caracteres.add(new Letra("C"));
        caracteres.add(new Letra("D"));
        caracteres.add(new Letra("E"));
        caracteres.add(new Letra("F"));
        caracteres.add(new Letra("G"));
        caracteres.add(new Letra("H"));
        caracteres.add(new Letra("I"));
        caracteres.add(new Letra("J"));
        caracteres.add(new Letra("K"));
        caracteres.add(new Letra("L"));
        caracteres.add(new Letra("M"));
        caracteres.add(new Letra("N"));
        caracteres.add(new Letra("O"));
        caracteres.add(new Letra("P"));
        caracteres.add(new Letra("Q"));
        caracteres.add(new Letra("R"));
        caracteres.add(new Letra("S"));
        caracteres.add(new Letra("T"));
        caracteres.add(new Letra("U"));
        caracteres.add(new Letra("V"));
        caracteres.add(new Letra("W"));
        caracteres.add(new Letra("X"));
        caracteres.add(new Letra("Y"));
        caracteres.add(new Letra("Z"));
        caracteres.add(new Letra("0"));
        caracteres.add(new Letra("1"));
        caracteres.add(new Letra("2"));
        caracteres.add(new Letra("3"));
        caracteres.add(new Letra("4"));
        caracteres.add(new Letra("5"));
        caracteres.add(new Letra("6"));
        caracteres.add(new Letra("7"));
        caracteres.add(new Letra("8"));
        caracteres.add(new Letra("9"));        
    }
    
    public static Letra getLetra(String md5){
        for(Letra i : caracteres)
        {
            if(i.md5Code.equals(md5))
            {
                return(i);
            }
        }
        throw new Error("Nao achou essa letra");
    } 
    
    public static void test6() {
        Letra l = new Letra("a");
        String md5 = l.getMd5Code();
        System.out.println(md5);
        Letra ll = Database.getLetra(md5);
        System.out.println(ll.getCaractere());
    }
    
    public static Conta getConta(String[] md5){
        String nomeCliente="", saldo="";
        int ns = 0;
        for(String m : md5)
        {
            Letra lt = Database.getLetra(m);
            if(lt.getCaractere().equals(" "))
            {
                ns++;
            }
            if(ns <= 1)
            {
                nomeCliente += lt.getCaractere();
            }
            else if(!lt.getCaractere().equals(" ") && ns > 1)
            {
                saldo += lt.getCaractere();
            }
        }
        return(new Conta(nomeCliente, saldo));
        
    }
    
    public static void test5() {
        Conta c = new Conta("124", "333", "1234", "10", "john doe");
        ServerDatabase.insereConta(c);
        String chave = SecurityProvider.md5ToServer(c);
        Conta conta = ServerDatabase.getConta(chave);
        String chars[];
        chars = SecurityProvider.md5ToClient(conta);
        System.out.println(Database.getConta(chars));
    }

    


}
