//Subclass Ancient which extends the superclass Enemy and its properties
//
public class Ancient extends Enemy{
    private int power; //Amplifies the damage

    //Constructor
    //
    public Ancient(int damage, int power){
        super("Ancient", damage);
        this.power = power*((int)(Math.random()*(3-1))+1);
    }

    //Method to get description of the enemy
    //
    @Override
    public String getDescription(){
        return "You are facing: "+super.getDescription() + " who also has an attack multiplier of "+getPower();
    }

    //Overriden method of getting the damage from the enemy
    //    
    @Override
    public int getDamage(){ //Damage is multiplied by power
        return super.getDamage() * this.getPower();
    }

    //Setter and getter methods
    //
    public void setPower(int newPower){
        this.power = newPower;
    }
    public int getPower(){
        return this.power;
    }
}