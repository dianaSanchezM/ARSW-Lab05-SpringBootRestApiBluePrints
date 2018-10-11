/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.creators;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.Tuple;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author diana
 */
public class blueprintsCreator {
    private static BlueprintsServices bp;
    
    public static void main(String args[]) throws BlueprintPersistenceException,BlueprintNotFoundException{
        ApplicationContext apCtxt = new ClassPathXmlApplicationContext("applicationContext.xml");
        bp= apCtxt.getBean(BlueprintsServices.class);
        registerBlueprints();
        consultBlueprints();
        consultBlueprintByAuthor();
        consultSpecifiBlueprints();
    }

    private static void registerBlueprints() throws BlueprintNotFoundException, BlueprintPersistenceException {
        Point[] pts0=new Point[]{new Point(30, 40),new Point(15, 15),new Point(15, 15),new Point(45, 60),new Point(90, 40)};
        Blueprint bp0=new Blueprint("Dali", "Blueprint",pts0);        
        bp.addNewBlueprint(bp0);
        Point[] pts1=new Point[]{new Point(60, 80),new Point(95, 15),new Point(23, 47),new Point(75, 83),new Point(90, 40)};
        Blueprint bp1=new Blueprint("Omar", "Casa",pts1);
        bp.addNewBlueprint(bp1);
        Point[] pts2=new Point[]{new Point(97, 86),new Point(7, 63),new Point(43, 25),new Point(52, 74),new Point(90, 30)};
        Blueprint bp2=new Blueprint("Ricardo", "Hotel",pts2);
        bp.addNewBlueprint(bp2);
        Point[] pts3=new Point[]{new Point(10, 10),new Point(10, 10),new Point(10, 10),new Point(10, 10),new Point(10, 10)};
        Blueprint bp3=new Blueprint("Omar", "Conjunto",pts3);        
        bp.addNewBlueprint(bp3);
        System.out.println("-------");
        
    }

    private static void consultBlueprints() throws BlueprintNotFoundException {
        Set<Blueprint> set = bp.getAllBlueprints();
        for ( Iterator it = set.iterator(); it.hasNext();){
            Blueprint x = (Blueprint)it.next();
            System.out.println(x.toString());
            for (Point p :x.getPoints()){
                System.out.println("    "+p.getX()+" "+p.getY());
            }
        }
        System.out.println("-------");
    }

    private static void consultBlueprintByAuthor() throws BlueprintNotFoundException {
        Set<Blueprint> x=bp.getBlueprintsByAuthor("Omar");
        for ( Iterator it = x.iterator(); it.hasNext();){
            Blueprint b = (Blueprint)it.next();
            System.out.println(b.toString());
            for (Point p :b.getPoints()){
                System.out.println("    "+p.getX()+" "+p.getY());
            }
        }
        System.out.println("-------");
        
    }

    private static void consultSpecifiBlueprints() throws BlueprintNotFoundException {
        Blueprint x=bp.getBlueprint("Dali", "Blueprint");
        System.out.println(x.toString());
        for (Point p :x.getPoints()){
                System.out.println("    "+p.getX()+" "+p.getY());
            }
    }
    
    
}
