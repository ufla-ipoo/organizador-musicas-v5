import java.util.ArrayList;

/**
 * Uma classe que guarda detalhes de arquivos de música.
 * Esta versão pode tocar os arquivos de música.
 * 
 * Traduzido por Julio César Alves. 2023.10.01
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class OrganizadorDeMusicas
{
    // Um ArrayList para guardar as músicas.
    private ArrayList<Musica> musicas;
    // Um tocador de arquivos de música.
    private TocadorDeMusica tocador;
    // Um leitor que pode ler arquivos de música e carregá-los como músicas.
    private LeitorDeMusicas leitor;

    /**
     * Cria um OrganizadorDeMusicas
     */
    public OrganizadorDeMusicas()
    {
        musicas = new ArrayList<>();
        tocador = new TocadorDeMusica();
        leitor = new LeitorDeMusicas();
        lerBiblioteca("."); // pasta do projeto
        System.out.println("Biblioteca de músicas carregada. " + obterQuantidadeDeMusicas() + " músicas.");
        System.out.println();
    }
    
    /**
     * Adiciona um arquivo à coleção.
     * @param nomeDoArquivo O nome do arquivo a ser adicionado.
     */
    public void adicionarArquivo(String nomeDoArquivo)
    {
        musicas.add(new Musica(nomeDoArquivo));
    }
    
    /**
     * Adiciona uma música à coleção.
     * @param musica A música a ser adicionada.
     */
    public void adicionarMusica(Musica musica)
    {
        musicas.add(musica);
    }
    
    /**
     * Começa a tocar uma música da coleção.
     * @param indice O índice (posição) da música a ser tocada.
     */
    public void tocarMusica(int indice)
    {
        if(indiceEhValido(indice)) {
            Musica musica = musicas.get(indice);
            tocador.comecarATocar(musica.obterNomeDoArquivo());
            System.out.println("Agora tocando: " + musica.obterArtista() + " - " + musica.obterTitulo());
        }
    }
    
    /**
     * Retorna a quantidade de músicas na coleção.
     * @return A quantidade de músicas na coleção.
     */
    public int obterQuantidadeDeMusicas()
    {
        return musicas.size();
    }
    
    /**
     * Lista (exibe) uma música da coleção.
     * @param indice O índice (posição) da música a ser listada.
     */
    public void listarMusica(int indice)
    {
        System.out.print("Musica " + indice + ": ");
        Musica musica = musicas.get(indice);
        System.out.println(musica.obterDetalhes());
    }
    
    /**
     * Exibe uma lista de todas as músicas na coleção.
     */
    public void listarTodasAsMusicas()
    {
        System.out.println("Listagem das músicas: ");

        for(Musica musica : musicas) {
            System.out.println(musica.obterDetalhes());
        }
        System.out.println();
    }
    
    /**
     * Lista todas as músicas do artista passado.
     * @param artista O nome do artista.
     */
    public void listarPeloArtista(String artista)
    {
        for(Musica musica : musicas) {
            if(musica.obterArtista().contains(artista)) {
                System.out.println(musica.obterDetalhes());
            }
        }
    }
    
    /**
     * Remove uma música da coleção.
     * @param indice O índice (posição) da música a ser removida.
     */
    public void removerMusica(int indice)
    {
        if(indiceEhValido(indice)) {
            musicas.remove(indice);
        }
    }
    
    /**
     * Toca a primeira música da coleção, se ela não estiver vazia.
     */
    public void tocarPrimeiraMusica()
    {
        if(musicas.size() > 0) {
            tocador.comecarATocar(musicas.get(0).obterNomeDoArquivo());
        }
    }
    
    /**
     * Para o tocador.
     */
    public void pararDeTocar()
    {
        tocador.parar();
    }

    /**
     * Verifica se o índice passado é válido para a coleção.
     * Exibe uma mensagem de erro se não for.
     * @param indice O índice a ser verificado.
     * @return true se índice é válido, false em caso contrário
     */
    private boolean indiceEhValido(int indice)
    {
        // O valor a ser retornado.
        // Define de acordo se o índice é válido ou não.
        boolean valido;
        
        if(indice < 0) {
            System.out.println("Índice não pode ser negativo: " + indice);
            valido = false;
        }
        else if(indice >= musicas.size()) {
            System.out.println("Índice é muito grande: " + indice);
            valido = false;
        }
        else {
            valido = true;
        }
        return valido;
    }
    
    private void lerBiblioteca(String pasta)
    {
        ArrayList<Musica> musicasTemporarias = leitor.lerMusicas(pasta, ".mp3");

        // Coloca todas as músicas no organizador.
        for(Musica musica : musicasTemporarias) {
            adicionarMusica(musica);
        }
    }
}
