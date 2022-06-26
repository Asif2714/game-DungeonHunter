//Subclass Creep which extends the superclass Enemy and its properties
//
public class Creep extends Enemy{
    private String type; //Add instance varible of type of the enemy if its centaur, enemy will have double damage
    private int bounty;  //Add instace varible of bounty worth after killing

    //Constructor
    //
    public Creep(String name, String type, int damage){
        super(name, damage);
        this.type = type;
        this.bounty = (damage * 10);
    }

    //Method to get description of the enemy
    //
    @Override
    public String getDescription(){
        return "You are facing: "+ super.getDescription() + ", which is a Creep of Type: "+getType() + ", has a bounty of: "+ getBounty()+"!";
    }

    //Overriden method of getting the damage from the enemy
    //
    @Override
    public int getDamage(){
        if(getType().equals("Centaur")){
            return super.getDamage()*2;    //Damage is doubled if type is Centaur
        }
        else{
            return super.getDamage();
        }
    }

    //Setter and getter methods
    //
    public void setBounty(int newBounty){
        this.bounty = newBounty;
    }
    public String getType(){
        return this.type;
    }
    public int getBounty(){
        return this.bounty;
    }
}