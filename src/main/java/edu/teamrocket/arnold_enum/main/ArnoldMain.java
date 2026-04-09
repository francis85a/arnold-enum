package edu.teamrocket.arnold_enum.main;

import java.util.Optional;

import edu.teamrocket.arnold_enum.logica.Planeta;

public class ArnoldMain {

    public static void main(String[] args) {
        
        if(args.length == 1 && args[0].equalsIgnoreCase("help")) {
            System.out.println("Usage: java ArnoldMain <weight_in_kg> <planet_name>");
            System.out.println("Example: java ArnoldMain 70 Earth");
            return;
        }

        if(args.length != 2){
            System.out.println("Invalid arguments. Please provide your weight in kg and the name of the planet for more information use 'help'.");
            return;
            
        } 
        

        Optional<Double> peso = Optional.empty(); // kg
        Optional<Planeta> pluto = Optional.empty();
        
        try {
            peso = Optional.of(Double.parseDouble(args[0]));
            pluto = Optional.of(Planeta.valueOf(args[1].toUpperCase()));
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid weight in kg as the first argument");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("Please provide a valid planet name as the second argument.");
            return;
        }

        if (peso.get() <= 0) {
            System.out.println("Weight must be a positive number.");
            return;
        }

        double pesoEnPlaneta = pluto.get().pesoSuperficie(peso.get());
        System.out.printf("Your weight on %s is %f N%n", pluto.get().name(), pesoEnPlaneta);
         
        System.out.println("\nYour weight only on the terrestrial planets: ");
        for(Planeta planeta: Planeta.getPlanetasTerrestres()){
            System.out.printf("Your weight on %s is %f N%n", planeta.name(), planeta.pesoSuperficie(peso.get()));
        }
         
        System.out.println("\nYour weight only on the gas giant planets: ");
        for(Planeta planeta: Planeta.getGigantesGaseosos()){
            System.out.printf("Your weight on %s is %f N%n", planeta.name(), planeta.pesoSuperficie(peso.get()));
        }
       System.out.println("\nYour weight only on the frozen giant planets: ");
       for(Planeta planeta : Planeta.getGigantesHelados()){
           System.out.printf("Your weight on %s is %f N%n", planeta.name(), planeta.pesoSuperficie(peso.get()));
       }
    }

}