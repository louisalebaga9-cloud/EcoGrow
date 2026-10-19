package backend;

/**
 * Clase que representa un sensor en el invernadero EcoGrow.
 * Registra y almacena lecturas históricas para monitorear el estado del cultivo.
 */
public class Sensor {
    private String idSensor;
    private String tipo; // Ej: "Temperatura", "Humedad del Suelo", "Luz"
    private double valorActual;
    private String unidadMedida; // Ej: "°C", "%", "Lux"
    private double[][] historialLecturas; // Arreglo multidimensional: [día][lectura_del_día]
    private int diaActual;
    private int lecturaActual;

    // Constructor
    public Sensor(String idSensor, String tipo, String unidadMedida) {
        this.idSensor = idSensor;
        this.tipo = tipo;
        this.unidadMedida = unidadMedida;
        this.valorActual = 0.0;
        // Matriz para guardar lecturas de 7 días, con hasta 10 lecturas por día
        this.historialLecturas = new double[7][10];
        this.diaActual = 0;
        this.lecturaActual = 0;
    }

    // Registrar una nueva lectura en el arreglo multidimensional
    public void registrarLectura(double nuevoValor) {
        this.valorActual = nuevoValor;
        if (diaActual < 7) {
            historialLecturas[diaActual][lecturaActual] = nuevoValor;
            lecturaActual++;
            if (lecturaActual >= 10) {
                lecturaActual = 0;
                diaActual++; // Avanza al siguiente día si se llena el cupo
            }
        }
    }

    // Calcular el promedio de lecturas de un día específico
    public double calcularPromedioDia(int dia) {
        if (dia < 0 || dia >= 7) return 0.0;
        double suma = 0;
        int conteo = 0;
        for (int i = 0; i < 10; i++) {
            if (historialLecturas[dia][i] != 0.0) {
                suma += historialLecturas[dia][i];
                conteo++;
            }
        }
        return conteo > 0 ? suma / conteo : 0.0;
    }

    // Getters y Setters
    public String getIdSensor() { return idSensor; }
    public String getTipo() { return tipo; }
    public double getValorActual() { return valorActual; }
    public String getUnidadMedida() { return unidadMedida; }
}
