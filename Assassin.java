import java.util.Random; //Random number to be used for evasion chances

//Subclass Assassin which extends the superclass Hero and its properties
//
public class Assassin extends Hero{
    private int evasion; //Adds a chance of evasion for the assassin
    
    //Constructor
    //
    public Assassin(int health, int damage, int evasion){
        super("Assassin", health, damage);
        this.evasion = evasion; //range from 0 to 10
    }
        
    //Fighting method
    //
    @Override 
    public void fightEnemy(Enemy e){
        super.fightEnemy(e);
        System.out.println("You are an Assassin, so you have a "+((this.getEvasion())*100)/10 +"% chance of evading attacks");
        Random rand = new Random();
        int RNG = rand.nextInt(10)+1;
        
        if(RNG <= this.getEvasion()){
            System.out.println("You have evaded the attack and killed the enemy!");
            e.setAlive(false);
            System.out.println("After the battle, your health is "+this.getHealth());
        }
        else{
            int assassinDamage = this.getDamage();
            int enemyDamage = e.getDamage(); //A place of dynamic binding and polymorphism,
            int damageTaken = enemyDamage - assassinDamage;
            System.out.println();
            this.changeHealth(-damageTaken);
            e.setAlive(false);
            System.out.println("You have killed the "+e.getName());
            System.out.println("After the battle, your health: "+this.getHealth());
           
        }
    }
    
    //Methods to get information about hero during selection and after fights, overriden with more details
    //
    @Override
    public String selectionInfo(){
        return super.selectionInfo()+ "Name: "+this.getName()+ " Ability: Evasion, less chance of getting attacked by enemies, and has no healing potions."; 
    }
    public String getInfo(){
        return super.getInfo() + ", you have no potions, evasion is your friend.";
    }
        
    //Setter and getter methods
    //
    public int getEvasion(){
        return this.evasion;
    }
    public void setEvasion(){
        this.evasion = evasion;
    }
}