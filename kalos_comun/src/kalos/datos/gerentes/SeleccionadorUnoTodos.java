package kalos.datos.gerentes;

import java.util.List;

public interface SeleccionadorUnoTodos<T> extends SeleccionadorUno{

    public abstract List<T> getTodos();
}
