import java.util.ArrayList;
import  java.util.Scanner;
class Main
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
                    System.out.println(g.doCircuito(getVertices()));
                    break;
                case CICLO:
                    System.out.println(g.doCiclo(getVertices()));
                    break;
                case HAMILTONIANO:
                    System.out.println(g.doHamiltoniano());
                    break;
                case EULERIANO:
                    System.out.println(g.doEuleriano());
                    break;
                default:
                    System.out.println("default");
                    break;
            }
        }
        //System.out.println("Main.main()");
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
        private int graus[];

        public Grafo(int quantidade)
        {
            vertices = quantidade;
            adj = new int [quantidade + 1][quantidade + 1];
            graus = new int [quantidade + 1];
        }
        
        public int getAresta(int origem, int destino)
        {
            return adj[origem][destino];
        }
        
        public void AddAresta(int linha, int coluna, int value)
        {
            adj[linha][coluna] = value;
        }
        
        private void setGraus()
        {
            for(int i = 1 ; i < vertices + 1 ; i++)
            {
                graus[i] = 0;
                for(int j = 1 ; j < vertices + 1 ; j++)
                {
                    // se tiver vizinho
                    if(getAresta(i, j) == 1)
                    {
                        graus[i]++;
                    }
                }
                
            }
        }
        private String doPasseio(ArrayList<Integer> vertices)
        {
            //System.out.println("vetor: " + vertices);
            for(int i = 0 ; i < vertices.size() - 1 ; i++)
            {
                // se nao tiver aresta
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
            
            // preenchimento vazio dos vertices ja percorridos
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
                
                // se nao tem aresta
                if(getAresta(atual, proximo) == 0)
                {
                    return NEGATIVO;
                }
                // se o proximo ja tiver sido visitado
                else if(percorridos[proximo] == 1)
                {
                    // se o proximo nao for o primeiro
                    if(proximo != primeiro)
                    {
                        return POSITIVO;
                    }
                    // se o proximo nao for o ultimo do passeio
                    else if((i + 1) != (vertices.size() - 1))
                    {
                        return NEGATIVO;
                    }   
                }
                // marca o proximo como percorrido
                percorridos[proximo] = 1;
            }
            return POSITIVO;
        }
        private String doTrilha(ArrayList<Integer> vertices)
        {
            int quantidade = vertices.size();
            int percorridas[][] = new int [quantidade + 1][quantidade + 1];
            int i;
            
            // preenchimento vazio das arestas ja visitadas
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
                
                // se nao tiver aresta
                if(getAresta(atual, proximo) == 0)
                {
                    return NEGATIVO;
                }
                // se a aresta ja tiver sido percorrida
                else if(percorridas[atual][proximo] == 1)
                {
                    return NEGATIVO;
                }
                
                // marca a aresta como percorrida
                percorridas[atual][proximo] = 1;
                percorridas[proximo][atual] = 1;
            }
            return POSITIVO;
        }
        private String doCircuito(ArrayList<Integer> vertices)
        {
            // se os vertices iniciais e finais sao diferentes
            if(!vertices.get(0).equals(vertices.get(vertices.size() - 1)))
            {
                return NEGATIVO;
            }
            // se nao for trilha
            else if(doTrilha(vertices).equals(NEGATIVO))
            {
                return NEGATIVO; 
            }
            return POSITIVO;
        }
        private String doCiclo(ArrayList<Integer> vertices)
        {
            // se nao for circuito (trilha fechada)
            if(doCircuito(vertices).equals(NEGATIVO))
            {
                return NEGATIVO;
            }
            else
            {
                int tam = vertices.size();
                //  percorre o vetor de vertices
                for(int i = 0 ; i < tam - 1 ; i++)
                {
                    for(int j = i + 1 ; j < tam; j++)
                    {
                        // se for repetido
                        if(vertices.get(i).equals(vertices.get(j)))
                        {
                            // se nao for inicial e final
                            if(i != 0 || j != tam - 1)
                            {
                                return NEGATIVO;
                            }
                        }
                    }
                }
            }
            return POSITIVO;
        }
        private String doHamiltoniano()
        {
            boolean menor = false;
            // teorema de dirac
            // se tiver 3 ou mais vertices
            if(vertices > 2)
            {
                setGraus();
                for(int i = 1 ; i < vertices + 1 ; i++)
                {
                    if(graus[i] < vertices / 2)
                    {
                        menor = true;
                        break;
                    }
                }
                if(!menor)
                {
                    return POSITIVO;
                }
            }
            return POSITIVO;
        }
        private String doEuleriano()
        {
            int i;
            
            // inicializa os graus de cada vertice
            setGraus();
            
            // para o grau de  cada vertice
            for(i = 1 ; i < vertices + 1 ; i++)
            {
                // se for impar
                if(graus[i] % 2 != 0)
                {
                    return NEGATIVO;
                }
            }
            return POSITIVO;
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
 