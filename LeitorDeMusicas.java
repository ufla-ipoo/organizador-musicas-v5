import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Uma classe auxiliar para nossa aplicação de música. 
 * Esta classe pode ler arquivos de uma dada pasta com um sufixo especificado.
 * Ela interpretará que o nome do arquivo tem a informação do artista e do
 * título da música.
 * 
 * Espera-se que os nomes dos arquivos de música seguem um formato padrão do
 * do nome do artista e do título da música, separados por hífen.
 * Ex.: TheBeatles-HereComesTheSun.mp3
 * 
 * Traduzido por Julio César Alves - 2023.10.10
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class LeitorDeMusicas
{
    /**
     * Cria um leitor de músicas, pronto para ler as músicas da pasta de músicas.
     */
    public LeitorDeMusicas()
    {
        // Nada a fazer aqui.
    }
    
    /**
     * Lê arquivos de música com o sufixo informado da pasta passada.
     * @param pasta A pasta onde as músicas serão procuradas.
     * @param sufixo O sufixo que indica o tipo de arquivo.
     */
    public ArrayList<Musica> lerMusicas(String pasta, String sufixo)
    {
        File pastaMusicas = new File(pasta);
        File[] arquivosMusicas = pastaMusicas.listFiles((dir, name) -> 
                                    name.toLowerCase().endsWith(sufixo));
        
        // Monta uma coleção com objetos Musica de todos os arquivos encontrados
        ArrayList<Musica> musicas = 
            Arrays.stream(arquivosMusicas).
                   map(arquivo -> decodificarDetalhes(arquivo)).
                   collect(Collectors.toCollection(ArrayList::new));
        return musicas;
    }

    /**
     * Tenta decodificar os detalhes do artista e do título
     * a partir do nome do arquivo.
     * Assume que os detalhes estão na forma:
     *     artista-titulo.mp3
     * @param arquivo O arquivo da música.
     * @return Um objeto música contendo os detalhes encontrados.
     */
    private Musica decodificarDetalhes(File arquivo)
    {
        // A informação necessária.
        String artista = "desconhecido";
        String titulo = "desconhecido";
        String nomeDoArquivo = arquivo.getPath();
        
        // Procura pelo artista e pelo título no nome do arquivo
        String detalhes = arquivo.getName();
        String[] partes = detalhes.split("-");
        
        if(partes.length == 2) {
            artista = partes[0];
            String parteDoTitulo = partes[1];
            // Remove o sufixo que indica o tipo do arquivo.
            partes = parteDoTitulo.split("\\.");
            if(partes.length >= 1) {
                titulo = partes[0];
            }
            else {
                titulo = parteDoTitulo;
            }
        }
        return new Musica(artista, titulo, nomeDoArquivo);
    }
}
