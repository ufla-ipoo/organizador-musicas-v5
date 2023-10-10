/**
 * Guarda os detalhes deuma música, tais como,
 * o artista, o título e o nome do arquivo.
 * 
 * Traduzido por Julio César Alves - 2023.10.10
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class Musica
{
    // O artista.
    private String artista;
    // O título da música.
    private String titulo;
    // Onde a música está guardada.
    private String nomeDoArquivo;
    
    /**
     * Construtor de objetos da classe música.
     * @param artista O artista da música.
     * @param titulo O título da música.
     * @param nomeDoArquivo O arquivo da música. 
     */
    public Musica(String artista, String titulo, String nomeDoArquivo)
    {
        definirDetalhes(artista, titulo, nomeDoArquivo);
    }
    
    /**
     * Construtor de objetos da classe música.
     * Este construtor seria usado para nomes de arquivos
     * a partir dos quais não conseguimos extrair os
     * detalhes de artista e título.
     * @param nomeDoArquivo O arquivo da música. 
     */
    public Musica(String nomeDoArquivo)
    {
        definirDetalhes("desconhecido", "desconhecido", nomeDoArquivo);
    }
    
    /**
     * Retorna o artista.
     * @return O artista.
     */
    public String obterArtista()
    {
        return artista;
    }
    
    /**
     * Retorna o título.
     * @return O título.
     */
    public String obterTitulo()
    {
        return titulo;
    }
    
    /**
     * Retorna o nome do arquivo.
     * @return O nome do arquivo.
     */
    public String obterNomeDoArquivo()
    {
        return nomeDoArquivo;
    }
        
    /**
     * Retorna os detalhes da música: artista, título e nome do arquivo.
     * @return Os detalhes da música.
     */
    public String obterDetalhes()
    {
        return artista + ": " + titulo + "  (arquivo: " + nomeDoArquivo + ")";
    }
    
    /**
     * Define os detalhes da música.
     * @param artista O artista da música.
     * @param titulo O título da música.
     * @param nomeDoArquivo O arquivo da música. 
     */
    private void definirDetalhes(String artista, String titulo, String nomeDoArquivo)
    {
        this.artista = artista;
        this.titulo = titulo;
        this.nomeDoArquivo = nomeDoArquivo;
    }
    
}
