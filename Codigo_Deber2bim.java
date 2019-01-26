import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Random;

/**
 *
 * @author usuario
 */
public class Principal {
    public static void main(String[] args) {
        Random r = new Random();
        int chat1,asp1=0,for1,eva1,tar1,pre1,chat2,asp2=0,for2,eva2,tar2,pre2;
        int total,totfn,fin;
        int acu60,acu40,recu = 0,exfin = 0;
        String alt = "",est;
        //Variables adicionales para calcular estadisticas/indicadores
        
        int noPRE = 0;
        double noPREp,noTarp,Aprp,Reprp;
        int noTar = 0;
        int Apr = 0,Repr =0;
        int totalClase = 0;
        double prom;
        ArrayList<String> SupProm=new ArrayList<>();
        ArrayList<String> notaCercana=new ArrayList<>();
        ArrayList<String> notasBajas=new ArrayList<>();
        
        
        //El número de estudiantes es el tamaño de la lista con los nombres
        String [] estudiantes = {"Juan","Luis","Carlos","Gustavo","Alfredo"};
        try {
            Formatter fileOut = new Formatter("Tabla.csv");
            
            for (String nombre : estudiantes){
                
                chat1 = r.nextInt(2);
                for1 = r.nextInt(2);
                eva1 = r.nextInt(2)+1;
                tar1 = r.nextInt(6)+1;
                pre1 = r.nextInt(10)+1;
                chat2 = r.nextInt(1)+1;
                for2 = r.nextInt(1)+1;
                eva2 = r.nextInt(2)+1;
                tar2 = r.nextInt(6)+1;
                pre2 = r.nextInt(10)+1;
                //Primera condición [1]
                if(chat1 == 0){
                    asp1 = r.nextInt(2);
                }
                if(chat2 == 0){
                    asp2 = r.nextInt(2);
                }
                total = chat1+asp1+for1+eva1+tar1+pre1+chat2+asp2+for2+eva2+
                        tar2+pre2;
                totfn = 0;
                fin = 0;
                acu60 = (int) Math.round(total*0.6);
                //Tercera condición [3]
                if(total < 28){
                    //Segunda condición [2]
                    alt = "FINAL";
                    //Cuarta condición [4]
                    exfin = r.nextInt(16)+1;
                    totfn = acu60 + exfin;
                    totalClase += totfn;
                }else{
                    totalClase += total;
                }
               
                acu40 = (int) Math.round(totfn*0.4);
                if(totfn < 28){
                    //Segunda condición [2]
                    alt = "RECUPERACION";
                    //Quinta condición [5]
                    recu = r.nextInt(24)+1;
                    fin = acu40+recu;
                    totalClase += fin;
                }
                //Séptima condición [7]
                if (fin >= 28 || total >= 28 || totfn >= 28){
                    est = "APROBADO";
                }else{
                    est = "REPROBADO";
                }
                //CONDICIONES ADICIONALES
                //El estudiante se ha presentado o no a los presenciales
                if (pre1 == 0 || pre2 == 0 || exfin == 0 || recu == 0){
                    noPRE += 1;
                }
                //El estudiante a aprobado o no
                if (est.equals("APROBADO")){
                    Apr += 1;
                }
                if (est.equals("REPROBADO")){
                    Repr += 1;
                    //Estudiantes con notas bajas
                    notasBajas.add(nombre);
                }
                if(tar1 == 0 || tar2 == 0){
                    noTar += 1;
                }
                if (fin >= 35 || total >= 35 || totfn >= 35){
                    notaCercana.add(nombre);
                }
                fileOut.format("ESTUDIANTE;%s\r\n",nombre);
                fileOut.format("NOMBRE;VALOR\r\n");
                fileOut.format("CHT1;%d\r\nASP1;%d\r\nFOR1;%d\r\nEVA1;%d\r\n"
                        + "TAR1;%d\r\nPRE1;%d\r\nCHT2;%d\r\nASP2;%d\r\nFOR2;"
                        + "%d\r\nEVA2;%d\r\nTAR2;%d\r\nPRE2;%d\r\n"
                        + "TOTAL;%d\r\nALT;%s\r\nACU60;%d\r\nEXFIN;%d\r\n"
                        + "TOTFN;%d\r\nACU40;%d\r\nRECU;%d\r\nFIN;%d\r\n"
                        + "EST;%s\r\n\r\n",chat1,asp1,for1,eva1,tar1,pre1,chat2,
                        asp2,for2,eva2,tar2,pre2,total,alt,acu60,exfin,
                        totfn,acu40,recu,fin,est);
            }
            //AGREGAR DATOS ADICIONALES
            prom = totalClase/estudiantes.length;
            noPREp = estudiantes.length/100*noPRE;
            Aprp = ((estudiantes.length/100)*Apr);
            Reprp = ((estudiantes.length/100)*Repr);
            noTarp = ((estudiantes.length/100)*noTar);
            fileOut.format("PORCENTAJE DE ESTUDIANTES QUE NO SE HA PRESENTADO"
                    + " A RENDIR EXAMENES PRESENCIALES:\r\n%.2f\r\n"
                    + "PORCENTAJE DE ESTUDIANTES QUE APROBARON:\r\n%.2f\r\n"
                    + "PORCENTAJE DE ESTUDIANTES QUE REPROBARON:\r\n%.2f\r\n"
                    + "PORCENTAJE DE ESTUDIANTES QUE NO ENTREGARON TAREAS:\r\n"
                    + "%.2f\r\nListado de estudiantes que mas se acercan a la "
                    + "nota maxima de 40 pts\r\n",noPREp,Aprp,Reprp,noTarp);
            for (int i = 0; i < notaCercana.size();i++){
                fileOut.format("&s;",notaCercana.get(i));
            }
            fileOut.format("\r\nListado de estudiantes mas bajos en notas\r\n");
            for (int i = 0; i < notasBajas.size();i++){
                fileOut.format("%s;",notasBajas.get(i));
            }
            fileOut.close(); 
        } catch (FileNotFoundException e) {
        }
    }    
}