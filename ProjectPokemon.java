/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.curso.projectpokemon;

import java.util.Scanner;

/**
 *
 * @author acabezam
 */
public class ProjectPokemon {
    
    public static boolean isCorrectIndex(User user, int index){
        return index <= user.getPokemonTeamSize() && index > 0;
    }
    
    public static void main(String[] args) {
        int accion = -1;
        Scanner sc = new Scanner(System.in);
        User user = new User();
        int numPokemon;
        
        while (accion != 9){
            System.out.println("Welcome to Pokemitos!");
            
            System.out.println("Press:\n 1: To see your pokemon team \t "
                    + "2: Battle \t 3: Train \t 4: Capture a new pokemon \t 9: Exit");
            
            accion = sc.nextInt();
            
            switch(accion){
                case 1:
                    System.out.println(user.showPokemonTeam());
                    break;
                case 2:
                    numPokemon = -1;
                    
                    while (numPokemon == -1 || !isCorrectIndex(user, numPokemon)){
                        System.out.println(user.showPokemonTeam());
                        
                        System.out.println("Choose the pokemon who is going to battle");
                        numPokemon = sc.nextInt();
                    }
                    
                    System.out.println(user.battle(numPokemon-1));
                    break;
                case 3:
                    numPokemon = -1;
                    
                    while (numPokemon == -1 || !isCorrectIndex(user, numPokemon)){
                        System.out.println(user.showPokemonTeam());
                        
                        System.out.println("Choose the pokemon you want to train");
                        numPokemon = sc.nextInt();
                    }
                    
                    System.out.println(user.train(numPokemon-1));
                    break;
                case 4:
                    System.out.println(user.capture());
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Please introduce a correct number");
                    
            }
        }
    }
}
