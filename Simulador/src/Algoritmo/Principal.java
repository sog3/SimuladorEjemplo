package Algoritmo;

import Items.Job;
import Items.Cola;

/**
 * @author Zalazar Rodrigo
 * "Principal" es una clase que se hereda de todas las clases de algoritmos de programación
 * por el bien del polimorfismo. también contiene variables mutuas y métodos entre
 * todas las clases de algoritmos.
 */
public abstract class Principal {
    
    protected Cola list ;  // lista de todos los trabajos (todavía no ha funcionado)
    protected Cola readyCola;  // lista de todos los trabajos en la lista de espera
    protected Job currentJob;  //  está trabajando en un trabajo actual en el que la simulación está trabajando ahora
    protected boolean busy ;  // indica si la CPU está ocupada o no (trabajos no preventivos)
    
    /**
     * inicializa la lista y las colas listas y toma una copia de la cola de simulación
     * para trabajar y organizarlo a la hora de llegada se hace fácil para la programación
     * algoritmo para definir qué trabajo vino primero.
     * @param workCola cola de trabajos de la simulación
     */
  
    public Principal(Cola workCola)
    {
        readyCola = new Cola(workCola.size()); // inicializar el tamaño de la cola lista
        currentJob = new Job(9);  //inicializar un trabajo vacío para evitar una excepción de puntero nulo
        busy = false;  //establecer ocupado como predeterminado
        list = workCola.getCopy(); //copiar la cola de simulación a la cola principal del algoritmo
        list.OrderedByArrive();  // ordenar la cola del algoritmo por tiempo de llegada
    }
    
    /**
     * El método abstracto necesita ser anulado en clases heredadas.
     * implementa cómo se comportará el algoritmo de programación en trabajos en
     * Un paso.
     * @param simulationTime hora actual de la simulación
     * @ devuelve el trabajo en el que trabaja actualmente el algoritmo
     */
    

    public abstract Job nextStep (int simulationTime);
    
    /**
     * cambia los datos del trabajo actual después de haber trabajado
     * en la CPU en un paso de tiempo de simulación.
     * @param simulationTime hora actual de la simulación
     * @ devolver el trabajo actual en el que está trabajando la CPU
     */
    protected Job workInCPU(int simulationTime)
    {
        currentJob.jobWorked(simulationTime);
        return currentJob;
    }
    
    /**
     * @devuelve una copia separada de la cola lista
     */
    public Cola getReadyCola()
    {
        return readyCola.getCopy();
    }

    /**
     * comprueba si el algoritmo terminó la simulación o no.
     * comprueba si la lista principal y la cola preparada están vacías y
     * la CPU no funciona en ningún trabajo
     * @return true si la simulación ha finalizado
     */
    public boolean isFinished()
    {
        return (list.isEmpty() && readyCola.isEmpty()  && !busy &&  currentJob.getRemainTime() == 0);
    }
    
    /**
     * agregua los trabajos recién llegados a la cola lista
     * comparando el tiempo de llegada con el tiempo de simulación.
     * @param simulationTime hora actual de la simulación
     */
 
    protected void updateReadyCola(int simulationTime)
    {
        for (int i = 0 ; i<list.size() ; i++)
        {
            Job temp = list.getJob(i);
            if(temp.arrivalTime == simulationTime)  // si llegó el trabajo 
            {
                readyCola.addJob(temp);  //si llegó el trabajo, mueve a la lista de espera
                list.removeJob(i);   //elimina el trabajo de la lista principal de trabajos
                i--; // elimina reduciendo el tamaño de la lista en uno
            }
        }
    }
    
    /**
     * mueve el primer trabajo a la cola lista
     * es el trabajo actual para que la CPU trabaje.
     */
    protected void setCurrentJob()
    {
        currentJob = readyCola.getJob(0); 
        readyCola.removeJob(0);
    }
    
}
