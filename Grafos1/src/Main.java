
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
    private static final String POSITIVO = "yes";
    private static final String NEGATIVO = "no";
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
            acao = entrada.next();
            switch(acao)
            {
                case PASSEIO:
                    System.out.println(g.doPasseio(getVertices()));                    
                    break;
                case CAMINHO:
                    System.out.println(g.doCaminho(getVertices()));
                    break;
                case TRILHA:
                    System.out.println(g.doTrilha(getVertices()));
                    break;
                case CIRCUITO:
                    g.doCircuito();
                    break;
                case CICLO:
                    g.doCiclo();
                    break;
                case HAMILTONIANO:
                    g.doHamiltoniano();
                    break;
                case EULERIANO:
                    g.doEuleriano();
                    break;
                default:
                    System.out.println("default");
                    break;
            }
        }
        System.out.println("Main.main()");
    }
    
    private static ArrayList<Integer> getVertices()
    {
        String caminho, vertices[];
        caminho = entrada.next();
        
        vertices = caminho.split("-");
        ArrayList<Integer> vs = new ArrayList<>();
        
        for (String vertice : vertices) {
            vs.add(Integer.parseInt(vertice));
        }
        return vs;
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
        
        private String doPasseio(ArrayList<Integer> vertices)
        {
            //System.out.println("vetor: " + vertices);
            for(int i = 0 ; i < vertices.size() - 1 ; i++)
            {
                // se não tiver aresta
                if(getAresta(vertices.get(i), vertices.get(i + 1)) == 0)
                {
                    return POSITIVO;
                }
            }
            return NEGATIVO;
        }
        private String doCaminho(ArrayList<Integer> vertices)
        {
            //System.out.println("vetor: " + vertices);
            int percorridos[] = new int[vertices.size() + 1];
            int primeiro;
            
            // preenchimento vazio dos vértices já percorridos
            for(int i = 0 ; i < percorridos.length ; i++)
            {
                percorridos[i] = 0;
            
            }
            primeiro = vertices.get(0);
            percorridos[primeiro] = 1;
            
            
            for(int i = 0 ; i < vertices.size() - 1 ; i++)
            {
                int atual = vertices.get(i);
                int proximo = vertices.get(i + 1);
                
                // se não tem aresta
                if(getAresta(atual, proximo) == 0)
                {
                    return NEGATIVO;
                }
                // se o próximo já tiver sido visitado
                else if(percorridos[proximo] == 1)
                {
                    // se o próximo não for o primeiro
                    if(proximo != primeiro)
                    {
                        return POSITIVO;
                    }
                    // se o próximo não for o último do passeio
                    else if((i + 1) != (vertices.size() - 1))
                    {
                        return NEGATIVO;
                    }   
                }
                // marca o próximo como percorrido
                percorridos[proximo] = 1;
            }
            return POSITIVO;
        }
        private String doTrilha(ArrayList<Integer> vertices)
        {
            int quantidade = vertices.size();
            int percorridas[][] = new int [quantidade + 1][quantidade + 1];
            int i;
            
            // preenchimento vazio das arestas já visitadas
            for(i = 1 ; i < percorridas.length ; i++)
            {
                for(int j = 1 ; j < percorridas.length ; j++)
                {
                    percorridas[i][j] = 0;
                }
            }
            
            for(i = 0 ; i < quantidade - 1 ; i++)
            {
                int atual = vertices.get(i);
                int proximo = vertices.get(i + 1);
                
                // se não tiver aresta
                if(getAresta(atual, proximo) == 0)
                {
                    return NEGATIVO;
                }
                // se a aresta já tiver sido percorrida
                else if(percorridas[i][i + 1] == 1)
                {
                    return NEGATIVO;
                }
                
                // marca a aresta como percorrida
                percorridas[i][i + 1] = 1;
            }
            return POSITIVO;
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
 