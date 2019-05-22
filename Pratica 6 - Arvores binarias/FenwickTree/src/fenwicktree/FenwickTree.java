/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fenwicktree;

/**
 *
 * @author Matheus Oliveira de Freitas
 */
public class FenwickTree {
    int value;
    int leftSize;
    FenwickTree left;
    FenwickTree right;
    
    public FenwickTree(int value){
        this.value=value;
        this.leftSize=0;
        this.left=null;
        this.right=null;
    }
    
    public FenwickTree(int leftSize, FenwickTree left, FenwickTree right){
        this.value=left.value+right.value;
        this.leftSize=leftSize;
        this.left=left;
        this.right=right;
    }
    
    public FenwickTree(int value, int leftSize, FenwickTree left, FenwickTree right){
        this.value=value;
        this.leftSize=leftSize;
        this.left=left;
        this.right=right;
    }
    
    public String toString(){        
        String texto="[" + Integer.toString(this.value) + ", "  +
                     Integer.toString(this.leftSize);
        if(this.left!=null){
            texto+=", " + this.left.toString();
        }
        if(this.right!=null){
            texto+= ", " + this.right.toString();
        }
        return(texto + "]");
    }    

    static FenwickTree allZeros(int n) {
        if (n == 0) {
            return null;
        }
        if (n == 1) {
            return new FenwickTree(0);
        }
        int m = n / 2;
        return new FenwickTree(0, n - m, allZeros(n - m), allZeros(m));
    }
    
    int size(){
        if(this.right != null){
            return(leftSize+right.size());
        }
        else{
            return(1);   
        }
    }

    public void increment(int i, int delta)
    {
        try{
            if(i==0 && leftSize==0){
                this.value=+delta;
            } 
            else if(i<leftSize){
                left.increment(i, delta);  
                value=left.value+right.value;  
            }
            else {
                right.increment(i-this.leftSize, delta);  
                value=left.value+right.value;  
            }
        }
        catch(Exception E){
            throw new Error("Posição i = " + i + "não encontrada");
        }
    }
    
    public int prefixSum(int upto)
    {
        if(upto==0){
            return(0);
        } 
        else if (this.leftSize == 0) {
            return (this.value);
        } 
        else if (this.leftSize == upto) {
            return (this.left.value);
        } 
        else if (upto < this.leftSize) {
            return (this.left.prefixSum(upto));
        } 
        else {
            return (this.left.value
                    + this.right.prefixSum(upto - this.leftSize));
        }       
    }
    
    public int between(int lo, int hi){
        int result = this.prefixSum(hi)-this.prefixSum(lo);
	return(result);
    }
    
    public static void main(String[] args) {
        //teste 2
        System.out.println("Construcao de FenwickTree(3) : " + new FenwickTree(3));
        System.out.println("Construcao da arvore da figura : "
                + new FenwickTree(3, new FenwickTree(1, new FenwickTree(4),
                        new FenwickTree(1, new FenwickTree(2), new FenwickTree(5))),
                        new FenwickTree(1, new FenwickTree(3),
                                new FenwickTree(1, new FenwickTree(6), new FenwickTree(1)))));
        //teste 3
        System.out.println("Construcao de allZeros(3) : " + FenwickTree.allZeros(3));
        System.out.println("Construcao de allZeros(4) : " + FenwickTree.allZeros(4));
        System.out.println("Construcao de allZeros(5) : " + FenwickTree.allZeros(5));
        System.out.println("Construcao de allZeros(6) : " + FenwickTree.allZeros(6));

        // teste de correcao 4
        System.out.println("Verificacao de correcao da funcao...");
        System.out.println("Tamanho de FenwickTree(6) :"
                + (new FenwickTree(6)).size());
        System.out.println("Tamanho de allZeros(6) :"
                + (FenwickTree.allZeros(6)).size());
        System.out.println("Tamanho de allZeros(12) :"
                + (FenwickTree.allZeros(12)).size());
        FenwickTree T1 = new FenwickTree(3, new FenwickTree(1, new FenwickTree(4),
                new FenwickTree(1, new FenwickTree(2), new FenwickTree(5))),
                new FenwickTree(1, new FenwickTree(3),
                        new FenwickTree(1, new FenwickTree(6), new FenwickTree(1))));
        System.out.println("Árvore this : " + T1);
        System.out.println("Tamanho de this : " + T1.size());

        // teste de correcao 5
        System.out.println("Verificacao de correcao da funcao...");
        FenwickTree T = new FenwickTree(3,
                new FenwickTree(1, new FenwickTree(0),
                        new FenwickTree(1, new FenwickTree(0), new FenwickTree(0))),
                new FenwickTree(1, new FenwickTree(0),
                        new FenwickTree(1, new FenwickTree(0), new FenwickTree(0))));
        System.out.println("Arvore this :" + T);
        T.increment(0, 4);
        System.out.println("Resultado de increment(0, 4) : " + T);
        T.increment(1, 2);
        System.out.println("Resultado de increment(1, 2) : " + T);
        T.increment(2, 5);
        System.out.println("Resultado de increment(2, 5) : " + T);
        T.increment(3, 3);
        System.out.println("Resultado de increment(3, 3) : " + T);
        T.increment(4, 6);
        System.out.println("Resultado de increment(4, 6) : " + T);
        T.increment(5, 1);
        System.out.println("Resultado de increment(5, 1) : " + T);

        // teste de correcao 6
        System.out.println("Verificacao de correcao da funcao...");
        FenwickTree W = new FenwickTree(3, new FenwickTree(1, new FenwickTree(4),
                new FenwickTree(1, new FenwickTree(2), new FenwickTree(5))),
                new FenwickTree(1, new FenwickTree(3),
                        new FenwickTree(1, new FenwickTree(6), new FenwickTree(1))));
        System.out.println("Arvore this : " + W);
        System.out.println("Soma das primeiras folhas : ");
        for (int upto = 0; upto <= 6; upto++) {
            System.out.println("prefixSum(" + upto + ") : " + W.prefixSum(upto));
        }

        //teste 7
        FenwickTree Z = new FenwickTree(3, new FenwickTree(1, new FenwickTree(4),
                new FenwickTree(1, new FenwickTree(2), new FenwickTree(5))),
                new FenwickTree(1, new FenwickTree(3),
                        new FenwickTree(1, new FenwickTree(6), new FenwickTree(1))));
        System.out.println("Arvore this : " + Z);
        System.out.println("Soma das folhas entre lo e hi : ");
        System.out.print(" ");
        for (int lo = 0; lo <= 6; lo++) {
            System.out.print("lo = " + lo + " ");
        }
        System.out.println();
        for (int hi = 0; hi <= 6; hi++) {
            System.out.print("hi = " + hi + " ");
            for (int lo = 0; lo <= hi; lo++) {
                System.out.print(Z.between(lo, hi) + " ");
                if (Z.between(lo, hi) < 10) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
