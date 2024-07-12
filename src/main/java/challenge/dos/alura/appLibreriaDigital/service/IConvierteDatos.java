package challenge.dos.alura.appLibreriaDigital.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
