import java.util.Random;
//Subclass Ancient which extends the superclass Enemy and its properties
//
public class Monster extends Enemy{
    private int bashChance; //Have a chance to deal extra heavy damage
    private int bounty;     //Add instance variable of bounty after killing

    //Constructor
    //
    public Monster(String name, int damage, int bashChance){
        super(name, damage);
        this.bounty = (damage * 5);
        this.bashChance = bashChance;
    }

    //Method to get description of the enemy
    //
    @Override
    public String getDescription(){
        return "You are facing: "+super.getDescription() + ", which is a Monster, have bash chance of "+(bashChance*100/10)+"% and has a bounty of "+getBounty()+"!";
    }

    //Overriden method of getting the damage from the enemy
    //
    @Override
    public int getDamage(){
        //If bash is less than the bash chance, enemy will have an extra damage of 100
        Random RNG = new Random();
        int bash = RNG.nextInt(10);

        if(bash < this.getBashChance()){    //damage is increased by 100 if chance is satisfied
            return super.getDamage() + 100;
        }
        else {
            return super.getDamage();
        }
    }

    //Setter and getter methods
    //
    public void setBounty(int newBounty){
        this.bounty = newBounty;
    }
    public int getBounty(){
        return this.bounty;
    }
    public void setBashChance(int newChance){
        this.bashChance = newChance;
    }
    public int getBashChance(){
        return this.bashChance;
    }
}