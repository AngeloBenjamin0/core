import java.util.ArrayList;
import java.util.List;

public class RegistroEjecucionComando {
    private static final ThreadLocal<List<String>> CONTEXTO = new ThreadLocal<>();

    public static List<String> getEjecucionComandos(){
        return getContexto().get();
    }

    public static void addEjecucionComando(String ejecucionComando){
        getContexto().get().add(ejecucionComando);
    }

    public static void clearEjecucionComandos(){
        getContexto().set(new ArrayList<>());
    }

    private static ThreadLocal<List<String>> getContexto(){
        if (CONTEXTO.get() == null)
            CONTEXTO.set(new ArrayList<>());
        return CONTEXTO;
    }

}
