import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 * Fornece funcionalidade básica para tocar arquivos MP3 através
 * da biblioteca javazoom.
 * Veja http://www.javazoom.net/
 * 
 * Traduzido por Julio César Alves. 2023.10.01
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class TocadorDeMusica
{
    // O tocador atual. Pode ser null.
    private AdvancedPlayer tocador;
    
    /**
     * Construtor para objetos da classe TocadorDeMusica
     */
    public TocadorDeMusica()
    {
        tocador = null;
    }
    
    /**
     * Toca uma parte do arquivo de música passado.
     * O método retorna quando ele termina de tocar.
     * @param nomeDoArquivo O arquivo a ser tocado.
     */
    public void tocarAmostra(String nomeDoArquivo)
    {
        try {
            configurarTocador(nomeDoArquivo);
            tocador.play(500);
        }
        catch(JavaLayerException e) {
            reportarProblema(nomeDoArquivo);
        }
        finally {
            matarTocador();
        }
    }    
    
    /**
     * Começa a tocar o arquivo de música passado.
     * O método retorna assim que a música começa a tocar.
     * @param nomeDoArquivo O arquivo a ser tocado.
     */
    public void comecarATocar(final String nomeDoArquivo)
    {
        try {
            configurarTocador(nomeDoArquivo);
            Thread threadTocador = new Thread() {
                public void run()
                {
                    try {
                        tocador.play(5000);
                    }
                    catch(JavaLayerException e) {
                        reportarProblema(nomeDoArquivo);
                    }
                    finally {
                        matarTocador();
                    }
                }
            };
            threadTocador.start();
        }
        catch (Exception ex) {
            reportarProblema(nomeDoArquivo);
        }
    }
    
    public void parar()
    {
        matarTocador();
    }
    
    /**
     * Deixa o tocador pronto para tocar o arquivo passado.
     * @param nomeDoArquivo O nome do arquivo a ser tocado.
     */
    private void configurarTocador(String nomeDoArquivo)
    {
        try {
            InputStream is = obterInputStream(nomeDoArquivo);
            tocador = new AdvancedPlayer(is, criarDispositivoDeAudio());
        }
        catch (IOException e) {
            reportarProblema(nomeDoArquivo);
            matarTocador();
        }
        catch(JavaLayerException e) {
            reportarProblema(nomeDoArquivo);
            matarTocador();
        }
    }

    /**
     * Retorna um objeto InputStream para o arquivo passado.
     * @param nomeDoArquivo O arquivo a ser aberto.
     * @throws IOException se o arquivo não pode ser aberto.
     * @return Um fluxo de entrada para o arquivo.
     */
    private InputStream obterInputStream(String nomeDoArquivo)
        throws IOException
    {
        return new BufferedInputStream(
                    new FileInputStream(nomeDoArquivo));
    }

    /**
     * Cria um dispositivo de áudio.
     * @throws JavaLayerException se o dispositivo não pode ser criado.
     * @return Um dispositivo de aúdio.
     */
    private AudioDevice criarDispositivoDeAudio()
        throws JavaLayerException
    {
        return FactoryRegistry.systemRegistry().createAudioDevice();
    }

    /**
     * Termina o tocador, se ele existir.
     */
    private void matarTocador()
    {
        synchronized(this) {
            if(tocador != null) {
                tocador.stop();
                tocador = null;
            }
        }
    }
    
    /**
     * Reporta um problema ao tocar o arquivo passado.
     * @param nomeDoArquivo O arquivo sendo tocado.
     */
    private void reportarProblema(String nomeDoArquivo)
    {
        System.out.println("Houve um problema ao tocar o arquivo: " + nomeDoArquivo);
    }

}
