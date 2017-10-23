package br.com.educacao.epymaps.Model;

/**
 * Created by Jaelson on 12/06/2017.
 */

public class UserAtivoSingleton {
    private static Usuario usuario;
    private static UserAtivoSingleton instacia = null;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        UserAtivoSingleton.usuario = usuario;
    }

    public static synchronized UserAtivoSingleton getInstacia() {
        if(instacia == null){
            instacia = new UserAtivoSingleton();
        }
        return instacia;
    }
}
