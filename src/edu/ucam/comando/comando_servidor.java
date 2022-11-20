package edu.ucam.comando;

public class comando_servidor extends comando{
    private Tipos_respuesta tipo_respuesta;
    private enum Tipos_respuesta{
        OK,
        PREOK,
        FAILED
    }
    private int cod_respuesta;

    public comando_servidor(String number, String informacion_adicional, Character tipo_respuesta, int cod_respuesta) {
        super(number, informacion_adicional);
        if(tipo_respuesta.equals('O')){
            this.tipo_respuesta = Tipos_respuesta.OK;
        }
        if(tipo_respuesta.equals('P')){
            this.tipo_respuesta = Tipos_respuesta.PREOK;
        }
        if(tipo_respuesta.equals('F')){
            this.tipo_respuesta = Tipos_respuesta.FAILED;
        }
        this.cod_respuesta = cod_respuesta;

    }

    public Tipos_respuesta getTipo_respuesta() {
        return tipo_respuesta;
    }

    public int getCod_respuesta() {
        return cod_respuesta;
    }
}
