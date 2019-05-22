package calculadora;
import java.util.LinkedList;
import java.io.*;

/**
 *
 * @author matheus
 */
class Pilha<X>
{
    LinkedList<X> conteudo;
    
    public Pilha()
    {
        conteudo = new LinkedList<>();
    }
    
    public boolean estaVazia()
    {
        return(conteudo.isEmpty());
    }
    
    public void empilha(X x)
    {
        conteudo.addFirst(x);
    }
    
    public X desempilha()
    { 
        if(!estaVazia())
        {
            return(conteudo.removeFirst());
        }
        else
        {
            throw new Error("Não pode desempilhar lista vazia");
        }
    }
    
    public X topo()
    {
        if(!estaVazia())
        {
            return(conteudo.getFirst());
        }
        else
        {
            throw new Error("Não pode acessar topo de lista vazia");
        }
    }
    
    @Override
    public String toString()
    {
        return(conteudo.toString());       
    }
    
    public String toStringInverse()
    {
        LinkedList<X> temp=new LinkedList<>();
        conteudo.forEach((elementos) -> {
            temp.addFirst(elementos);
        });
        return(temp.toString());        
    }

}

public class Calculadora {

    /**
     * @param args the command line arguments
     */
    
        //Static deixa o metodo 
    static void test1() {
        Pilha<Double> aPilha = new Pilha<>();
        aPilha.empilha(1.1);
        aPilha.empilha(2.1);
        aPilha.empilha(3.1);
        aPilha.empilha(4.1);
        aPilha.empilha(5.1);
        double valor = 0.0;
        valor = aPilha.topo();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.topo();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
    }
    
     static void test2() {
        Pilha<Double> aPilha = new Pilha<>();
        System.out.println(aPilha);
        aPilha.empilha(1.1);
        System.out.println(aPilha);
        aPilha.empilha(2.1);
        System.out.println(aPilha);
        aPilha.empilha(3.1);
        System.out.println(aPilha);
        double valor = 0.0;
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        System.out.println(aPilha);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        System.out.println(aPilha);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        System.out.println(aPilha);
    }

    static void test3() {
        CalcRPN calc = new CalcRPN();
        System.out.print("3 2 + = ");
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(2.0);
        calc.mais();
        System.out.println(calc.resultado());
        calc = new CalcRPN();
        System.out.print("3 2 - = ");
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(2.0);
        calc.menos();
        System.out.println(calc.resultado());
        calc = new CalcRPN();
        System.out.print("3 2 * = ");
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(2.0);
        calc.vezes();
        System.out.println(calc.resultado());
        calc = new CalcRPN();
        System.out.print("3 2 / = ");
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(2.0);
        calc.dividido();
        System.out.println(calc.resultado());

        calc = new CalcRPN();
        System.out.print("1 2 + 3 4 - / 10 3 - * = ");
        calc.aPilha.empilha(1.0);
        calc.aPilha.empilha(2.0);
        calc.mais();
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(4.0);
        calc.menos();
        calc.dividido();
        calc.aPilha.empilha(10.0);
        calc.aPilha.empilha(3.0);
        calc.menos();
        calc.vezes();
        System.out.println(calc.resultado());
    }
    
    static void test4() {
        Operacao[] op = new Operacao[9];
        op[0] = new Operacao(16.0);
        op[1] = new Operacao(8.0);
        op[2] = new Operacao(4.0);
        op[3] = new Operacao(2.0);
        op[4] = new Operacao(1.0);
        op[5] = new Operacao('+', 2.0, 1.0);
        op[6] = new Operacao('-', 4.0, 3.0);
        op[7] = new Operacao('*', 8.0, 1.0);
        op[8] = new Operacao('/', 16.0, 8.0);
        for (int i = 0; i < op.length; i++) {
            System.out.print(op[i] + " ");
        }
        System.out.println();
    }

    
    
    public static void main(String[] args) throws IOException
    {
        //test1();
       // test2();
        //test3();
        CalcRPN c = new CalcRPN();
        c.interfaceUsuario();
        //test4();
    }
    
}
