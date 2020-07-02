package cls2thread;

public class TimerThread implements Runnable {//Clase Runner Implementa la Interfaz Runnable

    @Override//Sobreescribe el metodo run()
    public void run() {
        while (Utils.running) {//Mientras el Hilo se este ejecutando, haga lo siguiente:
            try {

                //En la primera parte se define cuanto tiempo se va a agregar
                int time = (int) (Math.random() * 100);//Valor aleatorio de 1 - 100 (tiempo)
                int additionalTime;//Variable de valor adicional
                
                if (time == 0 && time < 10) {//Si el valor generado aleatoriamente esta entre 0 y 10, haga lo siguiente:
                    additionalTime = 0;//Le agrega 0 de tiempo adicional
                } else if (time > 10 && time < 20) {//Si el valor generado aleatoriamente esta entre 10 y 20, haga lo siguiente:
                    additionalTime = 1000;//Le agrega 1000 de tiempo adicional
                } else if (time > 20 && time < 30) {//Si el valor generado aleatoriamente esta entre 20 y 30, haga lo siguiente:
                    additionalTime = 3000;//Le agrega 3000 de tiempo adicional
                } else {//Si el valor generado aleatoriamente esta entre 30 y 100, haga lo siguiente:
                    additionalTime = 6000;//Le agrega 6000 de tiempo adicional
                }

//                //En la segunda parte se define a cual Thread se le va a agregar el tiempo adicional
//                int threadToAddtime = (int) (Math.random() * 60); //Numero aleatorio hasta 60
//
//                if (threadToAddtime == 0 && threadToAddtime < 10) {//Si el numero aleatorio es igual a cero y ademas menor que 10
//
//                    //Se le agrega tiempo adicional al objeto en la posicion 0
//                    Utils.teamList[0].setAdditionalTime(additionalTime);
//
//                } else if (threadToAddtime > 10 && threadToAddtime < 20) {//Si el numero aleatorio es mayor a 10 pero menor a 20
//                    
//                    //Se le agrega tiempo adicional al objeto en la posicion 1
//                    Utils.teamList[1].setAdditionalTime(additionalTime);
//                    
//                } else if (threadToAddtime > 20 && threadToAddtime < 30) {//Si el numero aleatorio es mayor a 20 pero menor a 30
//                    
//                    //Se le agrega tiempo adicional al objeto en la posicion 2
//                    Utils.teamList[2].setAdditionalTime(additionalTime);
//                    
//                } else if (threadToAddtime > 30 && threadToAddtime < 40) {//Si el numero aleatorio es mayor a 30 pero menor a 40
//                    
//                    //Se le agrega tiempo adicional al objeto en la posicion 3
//                    Utils.teamList[3].setAdditionalTime(additionalTime);
//                    
//                } else if (threadToAddtime > 40 && threadToAddtime < 50) {//Si el numero aleatorio es mayor a 40 pero menor a 50
//                    
//                    //Se le agrega tiempo adicional al objeto en la posicion 4
//                    Utils.teamList[4].setAdditionalTime(additionalTime);
//                    
//                }
//
//                System.out.println("Numero de Thread: " + threadToAddtime + " Additional time to Thread " + additionalTime);
//                Thread.sleep(100);//Se espera 100 milisegundos
            } catch (Exception ex) {
                
            }
        }
    }
}
