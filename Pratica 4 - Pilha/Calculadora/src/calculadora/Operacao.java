package calculadora;

/**
 *
 * @author matheus
 */

public class Operacao 
{
    char code;
    double a;
    double b;
    
    public Operacao(double a)
    {
        this.a=a;
        code = 'e';
    }
    
    public Operacao(char code, double a, double b)
    {
        this.code=code;
        this.a=a;
        this.b=b;
    }
    
    public String toString()
    {
        if(code=='e')
        {
            return(Double.toString(a));
        }
        else
        {
            return(Character.toString(code));
        }
    }
}
