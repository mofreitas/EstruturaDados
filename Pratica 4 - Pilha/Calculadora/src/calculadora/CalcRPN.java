package calculadora;
import java.io.*;

/**
 *
 * @author matheus
 */

public class CalcRPN {
// variáveis da instancia :
// uma pilha para os cálculos

    Pilha<Double> aPilha;
    Pilha<Operacao> hist;
// construtor

    CalcRPN() 
    {
        aPilha = new Pilha<>();
        hist = new Pilha<>();
    }
// Adiç~ao de dois elementos do topo da pilha

    public void mais() 
    {
        double x = aPilha.desempilha();
        double y = aPilha.desempilha();
        aPilha.empilha(x+y);
        hist.empilha(new Operacao('+', x, y));
    }
// Subtraç~ao de dois elementos do topo da pilha

    public void menos() 
    {
        double x=aPilha.desempilha();
        double y=aPilha.desempilha();
        aPilha.empilha(y-x);
        hist.empilha(new Operacao('-', x, y));
    }
// Multiplicaç~ao de dois elementos do topo da pilha

    public void vezes()
    {
        double x = aPilha.desempilha();
        double y = aPilha.desempilha();
        aPilha.empilha(x*y);
        hist.empilha(new Operacao('*', x, y));
    }
// Divisao de dois elementos no topo da pilha

    public void dividido()
    {
        double x=aPilha.desempilha();
        double y=aPilha.desempilha();
        aPilha.empilha(y/x);
        hist.empilha(new Operacao('/', x, y));
    }
// retorna o conteudo do topo da pilha

    public Double resultado() 
    {
        return(aPilha.topo());
    }
    
    public void reinicialize()
    {
        while(!aPilha.estaVazia())
        {
            aPilha.desempilha();
        }
        while(!hist.estaVazia())
        {
            hist.desempilha();
        }
    }    
     
    public void cancela()
    {
        if(hist.topo().code=='e')
        {
            hist.desempilha();
            aPilha.desempilha();
        }
        else
        {
            aPilha.desempilha();
            Operacao op = hist.desempilha();
            aPilha.empilha(op.b);
            aPilha.empilha(op.a);
        }
    }
    
    // interpretador de comandos

    public void exec(String cmd) 
    {
        switch(cmd)
        {
            case "+":
            {
                mais();
                break;
            }
            case "-":
            {
                menos();
                break;
            }
            case "*":
            {
                vezes();
                break;
            }
            case "/":
            {
                dividido();
                break;
            }
            case "clear":
            {
                reinicialize();
                break;
            }
            case "hist":
            {
                System.out.println("Historico = " + hist.toStringInverse());
                break;
            }
            case "undo":
            {
                cancela();
                System.out.println("Historico = " + hist.toStringInverse());
                break;
            }
            default:
            {
                try{
                    aPilha.empilha(Double.parseDouble(cmd));  
                    hist.empilha(new Operacao(Double.parseDouble(cmd)));
                }
                catch(NumberFormatException  e)
                {
                    throw new Error("Operação inválida");
                }
                break;
            }
        } 
    }
                   
    public void interfaceUsuario() throws IOException {
        CalcRPN calc = new CalcRPN();
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                continue;
            }
            for (String s : line.split(" ")) {
                calc.exec(s);
            }
            System.out.println("Pilha = " + calc.aPilha);
        }
        System.out.println("Até logo");
    }
}
    

    
   
    


