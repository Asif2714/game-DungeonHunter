/*=====================================================================
 *Miniproject Level 3
 *Author: Abdullah Al Asif
 *ID: 200794417
 *An adventure game called Dungeon Hunter where player has to kill the
 *Enemies and get bounties or pay them bounty and avoid them and cross
 *the dungeon. More options and complexity will be added later.

======================================================================= */
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.*;

public class AdventureGame{

    //Main method
    //
    public static void main (String [] args) throws IOException{
        //Greetings message, Hero selection and Enemy generation
        //
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Dungeon Hunter made by Asif!");
        System.out.println("===========================================");
        System.out.println();
        System.out.println("Hello adventurer, what is your name?");
        String playerName = scanner.nextLine();
        System.out.println("Greetings, "+ playerName +"!");
        System.out.println();
        Hero hero = heroSelection();
        int bountyCollected = 0;

        ArrayList<Enemy> enemyArrayList = generateEnemyArray();

        //Enemy enemyArray[] = generateEnemyArray();
        int enemiesPassed = 0;
        int totalEnemies = enemyArrayList.size();
        int enemyIndex = 0;
        System.out.println("Press any key and Enter to start the game!");
        String prompt = scanner.nextLine();

        //Gameloop
        //
        while((enemiesPassed < totalEnemies) && (hero.getHealth() > 0)){
            System.out.println("==========");
            System.out.println("LEVEL: "+(enemyIndex+1));
            System.out.println("==========");
            System.out.println();
            System.out.println(totalEnemies - enemyIndex + " enemies alive!");
            System.out.println();
            System.out.println(hero.getInfo()); //A place of dynamic binding and polymorphism,
            System.out.println("");
            System.out.println("An enemy is in front of you!");
            System.out.println(enemyArrayList.get(enemyIndex).getDescription()); //A place of dynamic binding and polymorphism
            System.out.println();
            System.out.println("You have "+bountyCollected+ " bounty with you.");
            System.out.println();
            System.out.println("If you dont want to fight him, you have to pay a fee of 1000 bounty!");
            String ans = "";
            System.out.println();

            //Two types of encountering based on bounty.
            //
            if(bountyCollected < 1000){
                System.out.println("You dont have enough bounty to pay him. You have to fight!");
                System.out.println();
                System.out.println("Press any key to proceed");
                String dummy0 = scanner.nextLine();
                hero.fightEnemy(enemyArrayList.get(enemyIndex));  //A place of dynamic binding and polymorphism,
                if (enemyArrayList.get(enemyIndex) instanceof Creep){
                    int bountyFound = ((Creep)enemyArrayList.get(enemyIndex)).getBounty();
                    System.out.println("You have got "+bountyFound+" bounty from the creep!");
                    bountyCollected += bountyFound;
                }
                if(enemyArrayList.get(enemyIndex) instanceof Monster){
                    int bountyFound = ((Monster)enemyArrayList.get(enemyIndex)).getBounty();
                    System.out.println("YOu have got "+bountyFound + " bounty from the monster!");
                    bountyCollected += bountyFound;
                }
            }
            else{
                System.out.println("You have enough bounty to pay him.");
                System.out.println("Do you want to fight the enemy? yes/no ");

                ans = scanner.nextLine();
                if (ans.equals("yes")){
                    hero.fightEnemy(enemyArrayList.get(enemyIndex)); //A place of dynamic binding and polymorphism,
                    //
                    System.out.println();
                    if (enemyArrayList.get(enemyIndex) instanceof Creep){
                        int bountyFound = ((Creep)enemyArrayList.get(enemyIndex)).getBounty();
                        System.out.println("You have got "+bountyFound+" bounty!");
                        bountyCollected += bountyFound;
                    }
                    if(enemyArrayList.get(enemyIndex) instanceof Monster){
                        int bountyFound = ((Monster)enemyArrayList.get(enemyIndex)).getBounty();
                        System.out.println("YOu have got "+bountyFound + " bounty from the monster!");
                        bountyCollected += bountyFound;
                    }
                }
                else{
                    bountyCollected -= 1000;
                    System.out.println("You have paid "+enemyArrayList.get(enemyIndex).getName() + " 1000 bounty and avoided the fight.");
                }
            }

            //Going to potionPrompt if potion is available for player
            //
            if(hero instanceof Mage || hero instanceof Knight){
                potionPrompt(hero);
            }

            //Change of level details
            //
            enemiesPassed += 1;
            enemyIndex +=1;
            System.out.println();
            System.out.println("Press any key to proceed");
            String dummy0 = scanner.nextLine();
        }

        //End of the game
        //
        System.out.println("Game over!");
        System.out.println();
        if(enemiesPassed == totalEnemies){
            System.out.println("You have successfully went through the dangerous dungeon. Congratulations!");
        }
        else{
            System.out.println("You have failed to pass the dungeon, better luck next time...");
        }
        System.out.println();
        System.out.println("You have looted a total bounty of "+bountyCollected +" from the creepers!");

        System.out.println("Do you want to save your score in the scoresheet?");
        String ans = "";

        boolean ansInvalid = true;
        while(ansInvalid == true){
            try{
                ans = scanner.nextLine();
                if(!(ans.equals("yes")||ans.equals("no")))
                {throw new InvalidAns("Invalid input, select yes/no");}
                ansInvalid= false;
            }catch(InvalidAns e){
                System.out.println(e.getMessage());
                ansInvalid = true;
            }
        }

        if(ans.equals("yes")){
            FileWriter outputStream = new FileWriter("Scores.txt", true);
            BufferedWriter output = new BufferedWriter(outputStream);
            output.write(playerName+" had collected "+ bountyCollected + " at "+ new java.util.Date()+"\n");
            output.close();
        }

        System.out.println();
        System.out.println("================================");
        System.out.println("Thank you very much for playing!");
        System.out.println("================================");
    }//END of main



    //Static method for potion consumption
    //
    public static void potionPrompt(Hero hero){
        Scanner scanner = new Scanner(System.in);
        if (hero instanceof Mage){      //Potion consumption if hero is Mage
            ((Mage)hero).getPotionStatus();
            ((Mage)hero).getPotionCount();

            if ( ((Mage)hero).getPotionCount() > 0  ){
                System.out.println("Press y if you want to consume potion.");
                String input = scanner.nextLine();
                if(input.equals("y")){
                    ((Mage)hero).usePotion();
                    ((Mage)hero).setPotionCount(-1);
                    System.out.println("You have consumed a potion. Your health is increased by 50");
                }
            }
            else{
                System.out.println("You didn't consume a potion.");
            }
        }
        if (hero instanceof Knight){    //Potion consumption if hero is Knight
            ((Knight)hero).getPotionStatus();
            ((Knight)hero).getPotionCount();

            if ( ((Knight)hero).getPotionCount() > 0  ){
                System.out.println("Press y if you want to consume potion.");
                String input = scanner.nextLine();
                if(input.equals("y")){
                    ((Knight)hero).usePotion();
                    ((Knight)hero).setPotionCount(-1);
                    System.out.println("You have consumed a potion. Your health is increased by 50");
                }
                else{
                    System.out.println("You didn't consume a potion.");
                }
            }
        }
    }//END of potionPrompt

    //Method to generate enemy array with 5 creeps and 1 ancient
    //
    public static ArrayList<Enemy> generateEnemyArray(){
        final int TOTAL_ENEMIES = 6;
        String[] nameArray = {"Amor","Iphis","Hapi","Nuhn","Eros","Flax","Tyca","Miro","Ziga","Inis","Frost","Vipa","Necro","Chenoo","Yuteng","Ijraq"};
        int nameArrayIndex = 0;

        ArrayList<Enemy> enemyArrayList = new ArrayList<Enemy>();
        Random RNG = new Random();

        //Creating 5 creepers
        for(int i =0; i < TOTAL_ENEMIES-1; i++){
            String name = nameArray[nameArrayIndex];
            nameArrayIndex +=1;
            int randType = RNG.nextInt(3);

            if(randType == 0){
                Enemy creeper = new Creep(name, "Centaur", ((int)(Math.random()*(100-65))+65)); //Use of Substitution Principle
                enemyArrayList.add(creeper);
            }
            else if(randType == 1){
                Enemy monster = new Monster(name, ((int)(Math.random()*(90-60))+60), ((int)(Math.random()*(5-1))+1) ); //Use of Substitution Principle
                enemyArrayList.add(monster);
            }
            else{
                Enemy creeper = new Creep(name, "Ogre", ((int)(Math.random()*(120-40))+40)); //Use of Substitution Principle
                enemyArrayList.add(creeper);
            }

        }

        //Generating 1 Ancient
        Enemy ancient = new Ancient(100, 2); //Use of Substitution Principle
        enemyArrayList.add(ancient);

        return enemyArrayList;
    }//END of generateEnemyArray

    //Method to output a hero selection menu
    //
    public static Hero heroSelection(){
        final int CHOICES = 3;
        System.out.println("Choose your hero!");
        System.out.println("Their information are given as follows:");
        System.out.println();
        Hero[] heroArray = new Hero[CHOICES];

        Hero hero0 = new Mage(1000, 13, 12); //Substitution principle
        heroArray[0] = hero0;
        Hero hero1 = new Knight(1000, 17, 8); //Substitution principle
        heroArray[1] = hero1;
        Hero hero2 = new Assassin(1000, 15, 3); //Substitution principle
        heroArray[2] = hero2;

        for(int i = 0; i < heroArray.length; i++){
            System.out.println((i+1)+heroArray[i].selectionInfo()); //A place of dynamic binding and polymorphism, where selectionInfo can called be from
            //any of the three types of Hero which is decided during runtime, and those are overriden in subclass.
        }

        System.out.println("");
        System.out.println("Choose your hero by entering 1/2/3.");
        System.out.println("Choose wisely!");
        boolean correctSelection = false;
        Scanner scanner = new Scanner(System.in);

        Hero selected = new Hero(null, 0, 0);

        while (!correctSelection){
            String sel = scanner.nextLine();
            if(sel.equals("1")){
                correctSelection = true;
                selected = heroArray[0];
            }
            else if(sel.equals("2")){
                correctSelection = true;
                selected = heroArray[1];
            }
            else if(sel.equals("3")){
                correctSelection = true;
                selected = heroArray[2];
            }
            else{
                System.out.println("You have entered an invalid number, try again");
                correctSelection = false;
            }

        }
        System.out.println("You have selected the "+selected.getName());
        System.out.println("Great choice!");

        return selected;
    }//END of heroSelection

}//END of AdventureGame
class InvalidAns extends Exception{
    public InvalidAns(){
        super("Invalid input");
    }
    public InvalidAns(String message){
        super(message);
    }
}