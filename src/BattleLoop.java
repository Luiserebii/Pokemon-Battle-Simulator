import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class BattleLoop {


	public void startBattle(){

		System.out.println("Welcome to the 2016 Pokemon Battle Simulator!!!");

		Pokemon pok1 = new Pokemon("Volcarona", new String[]{"Bug","Fire"}, "Modest", 100, 85, 60, 65, 135, 105, 100, 31, 31, 31, 31, 31, 31, 72, 0, 0, 252, 0, 184);
		Pokemon pok2 = new Pokemon("Garchomp", new String[]{"Dragon","Ground"}, "Adamant", 100, 108, 130, 95, 80, 85, 102, 31, 31, 31, 31, 31, 31, 4, 252, 0, 0, 0, 252);

		pok1.setMoves(new String[]{"Fire Blast","Ice Beam","Thunder","Hyper Beam"});


		pok1.statsToString();
		pok2.statsToString();

		battleDamage(pok1,pok2);


	}



	public void battleDamage(Pokemon p1, Pokemon p2){


		System.out.println(p1.getLevel() + " " + p1.getCurrentSpAtk() + " " + p2.getCurrentSpDef());
//		int damage = (int) ((((2 * p1.getLevel() + 10)/(double) 250) * ((double) p1.getCurrentSpAtk()/p2.getCurrentSpDef()) * 120 + 2) * 1.5);
//
//		System.out.println("Fire Blast does " + damage + " dmg to " + p2.getName() + " (type effectiveness not implemented.)");

		double typeEff = checkTypeEffectiveness(p2.getType(), "Fire");

		int damage = (int) (((((2 * p1.getLevel() + 10)/(double) 250) * ((double) p1.getCurrentSpAtk()/p2.getCurrentSpDef()) * 120 + 2) * 1.5) * typeEff);
		System.out.println("Fire Blast does " + damage + " dmg to " + p2.getName() + " (type effectiveness implmented!)");

		if(typeEff == 0){
			System.out.println("It doesn't affect " + p2.getName() + "...");
		}
		if(typeEff == 0.5){
			System.out.println("It's not very effective...");
		}
		if(typeEff == 1){

		}
		if(typeEff == 2){
			System.out.println("It's super effective!");
		}
		if(typeEff == 4){
			System.out.println("It's ultra effective!");
		}
	}

	public double checkTypeEffectiveness(String[] pokeType, String moveType){

		double typeEff = 1;

		try {

			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("data/typechart.txt"));

			for(String type : pokeType){
				int val = (int) (long) ((JSONObject)((JSONObject) jsonObject.get(type)).get("damageTaken")).get(moveType);

				if(val == 0){

				}
				if(val == 1){
					typeEff = typeEff * 2;
				}
				if(val == 2){
					typeEff = typeEff * 0.5;
				}
				if(val == 3){
					typeEff = typeEff * 0;
				}

			}


		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return typeEff;
	}

}
