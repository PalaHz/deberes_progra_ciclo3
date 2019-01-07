package tarea1_2b;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andres Palacios
 */

public class Tarea1_2b {
    public static void main(String[] args) {
        Random r = new Random();
        double for1, cha1, vid1, tra1, pre1, for2, cha2, vid2, tra2, pre2, 
               fin1, fin2,total;
        String alerta = "", promocion = "";
        int limEstudiantes = 5;
        try {
            //Crear el archivo
            Formatter fileOut = new Formatter("Tablita.csv");
            //Crear el encabezado
            fileOut.format(";;;;;;;NOTAS\r\nNOMBRES;FOR1;CHA1;VID1;TRA1;PRE1;"
                    + "FOR2;CHA2;VID2;TRA2;PRE2;FIN1;FIN2;TOTAL;AVISO;PROMOCION\r\n");
            
            //Ciclo para recorrer el numero de estudianes/ ingresar cada columna
            for (int i = 1; i <= limEstudiantes; i++){
                promocion = "APROBADO";
                
                //Generando aleatorios entre 0 y 1 (Trabajos no presenciales)
                for1 = r.nextDouble();
                cha1 = r.nextDouble();
                vid1 = r.nextDouble();
                for2 = r.nextDouble();
                cha2 = r.nextDouble();
                vid2 = r.nextDouble();
                
                //Generando aleatorios entre 0 y 6 (Trabajos presenciales)
                tra1 = r.nextDouble()*6;
                tra2 = r.nextDouble()*6;
                
                //Generando aleatorios entre 0 y 14 (Evaluaciones presenciales)
                pre1 = r.nextDouble()*14;
                pre2 = r.nextDouble()*14;
                
                fin1 = for1 + cha1 + vid1 + tra1 + pre1;
                fin2 = for2 + cha2 + vid2 + tra2 + pre2;
                total = fin1 + fin2;
                
                //Comprobación para alertar si sacó menos de 8 en los pre
                if(pre1 <= 8 && pre2 <= 8){
                    alerta = "Rendir 1 y 2";
                }else{
                    if(pre1 <= 8){
                    alerta = "Rendir 1";
                    }
                    if(pre2 <= 8){
                    alerta = "Rendir 2";
                    }
                }
                if (total < 28){
                    promocion = "REPROBADO";
                }
                
                //Pasar al archivo cada valor
                fileOut.format("Estudiante %d;%.2f;%.2f;%.2f;%.2f;%.2f;"
                        + "%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%s;%s\r\n"
                        ,i,for1,cha1,vid1,tra1,pre1,for2,cha2,vid2,tra2,pre2,
                        fin1, fin2, total, alerta, promocion);
            }
            fileOut.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tarea1_2b.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
