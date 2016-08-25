
import java.util.ArrayList;
import  java.util.Scanner;
public class Main
{
    public static final String PASSEIO = "passeio";
    public static final String CAMINHO = "caminho";
    public static final String TRILHA = "trilha";
    public static final String CIRCUITO = "circuito";
    public static final String CICLO = "ciclo";
    public static final String HAMILTONIANO = "hamiltoniano";
    public static final String EULERIANO = "euleriano";
    
    public static void main(String[] args)
    {
        Scanner entrada = new Scanner(System.in);
        int ordem, testes, i, j;
        Grafo g;
        
        // leitura da ordem da matriz
        ordem = entrada.nextInt();
        // leitura da quantidade de testes
        testes = entrada.nextInt();
        
        g = new Grafo(ordem);
        
        // leitura da matriz de adjacencias
        for(i = 1 ; i <= ordem ; i++)
        {
            for(j = 1 ; j <= ordem ; j++)
            {
                g.AddAresta(i, j, entrada.nextInt());
            }
        }
        
       
        
        // leitura dos testes
        for(i = 0 ; i < testes ; i++)
        {
            
        }
    }
    
    private static class Grafo
    {
        private int adj[][];
        private int vertices = 0;
        
        public Grafo(int quantidade)
        {
            vertices = quantidade;
            adj = new int [quantidade + 1][quantidade + 1];
        }
        
        public void AddAresta(int linha, int coluna, int value)
        {
            adj[linha][coluna] = value;
        }
        
        public void ImprimirAdjacencias()
        {
            int i, j;
            for(i = 1 ; i <= vertices ; i++)
            {
                for(j = 1 ; j <= vertices ; j++)
                {
                    System.out.print(adj[i][j] + " ");
                }
                System.out.println("");
            }
        }
    }
}
 