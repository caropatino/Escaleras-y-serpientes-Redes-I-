/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import com.fazecast.jSerialComm.SerialPort;

/**
 *
 * @author admin
 */
public class Comunicacion implements Runnable {

    SerialPort puertoEntrada;
    SerialPort puertoSalida;

    public Comunicacion(int entrada, int salida) {
        puertoEntrada = SerialPort.getCommPorts()[entrada];
        puertoEntrada.setComPortParameters(2400, 8, 0, 1);
        puertoEntrada.openPort();
        if (entrada != salida) {
            // Los puertos de salida y entrada son diferentes(para realizar pruebas en una sola maquina)
            puertoSalida = SerialPort.getCommPorts()[salida];
            puertoSalida.setComPortParameters(2400, 8, 0, 1);
            //puertoSalida.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 1, 1);
            puertoSalida.openPort();
        } else {
            // COnfiguracion real en el laboratorio
            puertoSalida = puertoEntrada;
        }
    }

    public Comunicacion() {

    }

    public void Escuchar() {
        Comunicacion proceso1 = this;
        new Thread(proceso1).start();
    }

    public void Escribir() {

        byte[] envio = new byte[3];
        envio[0] = (byte) Short.parseShort("11110000", 2);
        envio[1] = (byte) Short.parseShort("11110000", 2);
        envio[2] = (byte) Short.parseShort("00000000", 2);

        System.out.print("Iniciar P Mensaje enviado: "
                + " " + pasarByteAString(envio[0])
                + " " + pasarByteAString(envio[1])
                + " " + pasarByteAString(envio[2])
                + "\n");
        this.puertoSalida.writeBytes(envio, envio.length);
    }

    public static String pasarByteAString(byte b) {
        String retorno = Integer.toBinaryString(b & 0xFF);
        //Para asegurar que sean 8 caracteres(llenar de ceros a la izquierda)
        while (retorno.length() < 8) {
            retorno = "0" + retorno;
        }
        return retorno;
    }

    @Override
    public void run() {
        do {
            byte[] readBuffer = null; // Bytes para almacenar la informacion
            try{

            while (puertoEntrada.bytesAvailable() < 3) {
            }
            
                readBuffer = new byte[3];
                int numRead = puertoEntrada.readBytes(readBuffer, 3);
            
                //Comprobacion de que se recibio
                System.out.print("Se encontro el mensaje:\n");
                for(int i=0; i<numRead;i++) System.out.println(" "+
                   pasarByteAString(readBuffer[i]));
                
            }catch (Exception e) {
                e.printStackTrace();
            }  
        } while (true);
    }
}
