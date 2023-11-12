import java.util.ArrayList;
import java.util.List;

public class RegistroResultadoEjecucion {
    private static final ThreadLocal<List<String>> CONTEXTO = new ThreadLocal<>();

    public static List<String> getResultadoEjecucion(){
        return getContexto().get();
    }

    public static void addResultadoEjecucion(String resultadoEjecucion){
        getContexto().get().add(resultadoEjecucion);
    }

    public static void clearResultadoEjecucion(){
        getContexto().set(new ArrayList<>());
    }

    private static ThreadLocal<List<String>> getContexto(){
        if (CONTEXTO.get() == null)
            CONTEXTO.set(new ArrayList<>());
        return CONTEXTO;
    }

}
