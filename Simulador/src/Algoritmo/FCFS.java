package Algoritmo;

import Items.Job;
import Items.Cola;

/**
 * @author Zalazar Rodrigo
 * El algoritmo de programación de CPU "Primero llegado, primero servido" funciona como su nombre
 * cuanto primero llegue el trabajo a la cola lista, primero se procesará
 * por la CPU.
 */
public class FCFS extends Principal{
     
    /**
     * pasa la cola de trabajo a la superclase para inicializar listas
     * @param workCola cola de listas para trabajar
     */
    public FCFS(Cola workCola)
    {  
        super(workCola); 
    }
    
    /**
     * muestra lo que sucede en un solo paso cuando se usa este algoritmo
     * @param simulationTime hora actual de esta simulación
     * @ trabajo de retorno en el que estaba trabajando la CPU
     */
    @Override
    public Job nextStep (int simulationTime)
    {
        updateReadyCola(simulationTime);  //agrega trabajos recién llegados a la cola lista
        if(!busy) //si la CPU no está procesando un trabajo (FCFS es un algoritmo no preventivo)
        {
            if(readyCola.isEmpty()) {return null;} 
            busy = true; 
            setCurrentJob(); // mueve el primer trabajo en la cola lista para ser el trabajo actual
        }
        return workInCPU(simulationTime);
    }
    
    /**
     * trabaja el trabajo actual en la CPU durante un paso de tiempo de simulación
     * @param simulationTime hora actual de la simulación
     * @ devuelve el trabajo actual en el que está trabajando la CPU
     */
    @Override
    protected Job workInCPU(int simulationTime)
    {
        currentJob.jobWorked(simulationTime); 
        if(currentJob.getRemainTime() == 0) {busy = false;} // si el trabajo está terminado, hace que la CPU no esté ocupada
        return currentJob; 
    }
    
   
}
