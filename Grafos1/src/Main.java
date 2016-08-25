
import java.util.ArrayList;
import  java.util.Scanner;
public class Main
{
    private static final String PASSEIO = "passeio";
    private static final String CAMINHO = "caminho";
    private static final String TRILHA = "trilha";
    private static final String CIRCUITO = "circuito";
    private static final String CICLO = "ciclo";
    private static final String HAMILTONIANO = "hamiltoniano";
    private static final String EULERIANO = "euleriano";
    private static Scanner entrada = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        
        int ordem, testes, i, j;
        String acao;
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
            acao = entrada.nextLine();
            switch(acao)
            {
                case PASSEIO:
                    doPasseio();
                    break;
                case CAMINHO:
                    doCaminho();
                    break;
                case TRILHA:
                    doTrilha();
                    break;
                case CIRCUITO:
                    doCircuito();
                    break;
                case CICLO:
                    doCiclo();
                    break;
                case HAMILTONIANO:
                    doHamiltoniano();
                    break;
                case EULERIANO:
                    doEuleriano();
                    break;
            }
        }
    }
    
    private static void doPasseio()
    {
        String vertice = entrada.next();
        while(vertice != )
        {
            
        }
    }
    private static void doCaminho()
    {
        
    }
    private static void doTrilha()
    {
        
    }
    private static void doCircuito()
    {
        
    }
    private static void doCiclo()
    {
        
    }
    private static void doHamiltoniano()
    {
        
    }
    private static void doEuleriano()
    {
        
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
        
        public int getAresta(int origem, int destino)
        {
            return adj[origem][destino];
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
 