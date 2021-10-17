//Subclass Mage which extends the superclass Hero and its properties
//
public class Mage extends Hero implements PotionInterface{
    //Instance variables
    //
    private int magic;         //Increases damage output of the mage
    private int potionCount;   //Keeps count of potions available
    
    //Constructor
    //
    Mage(int health, int damage, int magic){
        super("Mage",health, damage);
        this.magic = magic;
        this.potionCount = 5;
    }
        
    //Fighting method
    //
    @Override 
    public void fightEnemy(Enemy e){
        //gets enemy as an argument and hero kills the enemy and loses the health equal to the damage difference.
        super.fightEnemy(e);
        System.out.println("You are a mage, so you have an attack bonus of "+ getMagic() + ".");
        int mageDamage = this.getDamage() + this.getMagic();
        int enemyDamage = e.getDamage(); //A place of dynamic binding and polymorphism,
        int damageTaken = enemyDamage - mageDamage;
        System.out.println();
        this.changeHealth(-damageTaken);
        e.setAlive(false);
        System.out.println("You have killed the "+e.getName());
        System.out.println("After the battle, your health: "+this.getHealth());
    }
        
    //Methods to get information about hero during selection and after fights, overriden with more details
    //
    @Override
    public String selectionInfo(){
        return super.selectionInfo()+ "Name: "+this.getName()+ " Ability: Magic, gives increased damage, and has "+this.getPotionCount()+" healing potions."; 
    }
    @Override
    public String getInfo(){
        return super.getInfo() + "Potions: "+this.getPotionCount() + ".";
    }
        
    //Interface methods to consume potions
    //
    @Override
    public int getPotionCount(){
        return this.potionCount;
    }
    @Override
    public String getPotionStatus(){
        String available = ".";
        if (this.getPotionCount() > 0){
            available = " You can consume 1 potion to get 50 health.";
        }
        
        return this.getName() + " has " + this.getPotionCount() + " potions left." + available ;
    }
    @Override
    public void usePotion(){    
        if (this.getHealth() <= 950){
        this.setHealth(this.getHealth()+50);
        }
        else{
            this.setHealth(1000);
        }
    }
    @Override
    public void setPotionCount(int n){
        this.potionCount += n; 
    }
    
    //Setter and getter methods
    //
    public int getMagic(){
        return this.magic;
    }
    public void setMagic(int newMagic){
        this.magic = newMagic;
    }    
}