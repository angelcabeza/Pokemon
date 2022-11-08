/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curso.projectpokemon;

import java.util.ArrayList;

/**
 *
 * @author acabezam
 */
public class User {
    private ArrayList<Pokemon> pkmnTeam= new ArrayList<>();
    private PokemonWorld pkmnWorld = new PokemonWorld();
    
    public User(){
        pkmnTeam.add(pkmnWorld.getRandomPokemon());
    }
    
    
    public String showPokemonTeam(){
        String text = "Your pokemon team is:\n";
        
        for (Pokemon p : pkmnTeam){
            text += p.toString();
        }
        
        return text;
    }
    
    public String train(int index){
        Pokemon pkmn = pkmnTeam.get(index);
        String text = "Congratulations your pokemon has leveled up!. Your pokemon:\n";
        
        pkmn.levelUp();
        
        if(checkEvolution(pkmn)){
            evolvePokemon(index, pkmn);
        }
        
        text += pkmnTeam.get(index).toString();
        
        return text;
    }
    
    
    private TypeEffectivity getTypesEffectivity (Types typeA, Types typeB){
        switch (typeA){
            case FIRE:
                if (typeB == Types.GRASS){
                    return TypeEffectivity.EFFECTIVE;
                } else if (typeB == Types.FIRE || typeB == Types.WATER){
                    return TypeEffectivity.NO_EFFECTIVE;
                } else {
                    return TypeEffectivity.NEUTRAL;
                }
            case WATER:
                if (typeB == Types.FIRE){
                    return TypeEffectivity.EFFECTIVE;
                } else if (typeB == Types.WATER || typeB == Types.GRASS){
                    return TypeEffectivity.NO_EFFECTIVE;
                } else {
                    return TypeEffectivity.NEUTRAL;
                }
            case GRASS:
                if (typeB == Types.WATER){
                    return TypeEffectivity.EFFECTIVE;
                } else if (typeB == Types.FIRE || typeB == Types.GRASS){
                    return TypeEffectivity.NO_EFFECTIVE;
                } else {
                    return TypeEffectivity.NEUTRAL;
                }
            default:
                throw new IllegalArgumentException("The type of the pokemon doesn't exist");
        }
    }
    
    private boolean getWinner(Pokemon pkmnA, Pokemon pkmnB){
        TypeEffectivity typeEffectivity = getTypesEffectivity(pkmnA.getType(), pkmnB.getType());
        
        int powerPkmnA = pkmnA.getPower();
        
        if (typeEffectivity == TypeEffectivity.EFFECTIVE){
            powerPkmnA *= 1.5;
        } else if (typeEffectivity == TypeEffectivity.NO_EFFECTIVE){
            powerPkmnA /= 1.5;
        }
        
        return powerPkmnA >= pkmnB.getPower();
    }
    
    private boolean checkEvolution(Pokemon pkmn){
        if (pkmn.canEvolve()){
            return true;
        }
        
        return false;
    }
    
    private void evolvePokemon(int index, Pokemon pkmn){
        pkmnTeam.set(index, pkmn.evolve());
    }
    
    public String battle(int index){
        Pokemon pkmn = pkmnTeam.get(index);
        String text = "The battle has begun!\n";
        
        Pokemon wildPkmn = pkmnWorld.getRandomPokemon();
        
        text += "Your pokemon: \n\n" + pkmn.toString();
        text += "\t\t\t VS \n" + "\n\n" + wildPkmn.toString();
        
        boolean userWins = getWinner(pkmn, wildPkmn);
        
        if (userWins){
            text+="\nCongratulations you have won the battle!\n";
            pkmn.levelUp();
            pkmn.levelUp();
            
            if(checkEvolution(pkmn)){
                evolvePokemon(index, pkmn);
            }
            
            text+="\nYour pokemon has leveled up 2 leves!: \n" + pkmnTeam.get(index).toString();
        } else {
            text+="\nYou've lost the battle\n";
        }
        
        return text;
    }
    
    public String capture(){
        Pokemon wildPkmn = pkmnWorld.getRandomPokemon();
        String text = "\n You have captured a:\n" + wildPkmn.toString();
        
        pkmnTeam.add(wildPkmn);
        return text;
    }
    
    public int getPokemonTeamSize(){
        return pkmnTeam.size();
    }
}
