package banco;

/**
 *
 * @author Suporte
 */
public class Conta {
    String nomeCliente;
    String saldo;
    String agencia;
    String numero;
    String senha;
    String md5;
    
    public Conta(String agencia, String numero, String senha)
    {
        this.agencia=agencia;
        this.numero = numero;
        this.senha = senha;
        this.md5 = SecurityProvider.md5ToServer(this);
    }
    
    public Conta(String agencia, String numero, String senha, String saldo, String nomeCliente)
    {
        this.agencia = agencia;
        this.numero = numero;
        this.senha = senha;
        this.saldo = saldo;
        this.nomeCliente = nomeCliente;
        this.md5 = SecurityProvider.md5ToServer(this);
    }
    
    public Conta(String nomeCliente, String saldo)
    {
        this.saldo = saldo;
        this.nomeCliente = nomeCliente;
    }
    
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
    
    @Override
    public String toString()
    {
        return(
                "AGENCIA: " + agencia + "\n" +
                "CONTA: " + numero + "\n" +
                "SENHA: " + senha + "\n" +
                "NOME CLIENTE: " + nomeCliente + "\n" +
                "SALDO: " + saldo + "\n" +
                "MD5: " + md5 + "\n"                
        );
    }
    
    public static void test() {
        Conta c1 = new Conta("124", "333", "1234", "10", "john doe");
        System.out.println(c1);
        Conta c2 = new Conta("John Doe", "10");
        System.out.println(c2);
        Conta c3 = new Conta("123", "321", "666");
        System.out.println(c3);
    }                
}
