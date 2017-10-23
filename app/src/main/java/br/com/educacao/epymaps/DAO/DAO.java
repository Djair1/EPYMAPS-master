package br.com.educacao.epymaps.DAO;

/**
 * Created by lab1-19 on 05/06/17.
 */

public interface DAO<T> {

    public abstract boolean salvar(T t);
    public abstract boolean filtrar(T t);
    public abstract boolean atualiza(T t);
}
