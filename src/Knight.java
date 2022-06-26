//Subclass Knight which extends the superclass Hero and its properties
//
public class Knight extends Hero implements PotionInterface{
    private int armor;          //Makes knight more resistant to attack
    private int potionCount;    //Keeps count of potions available

    //Constructor
    //
    public Knight(int health, int damage, int armor){
        super("Knight", health, damage);
        this.armor = armor;
        this.potionCount = 3;
    }

    //Fighting method
    //
    @Override
    public void fightEnemy(Enemy e){
        super.fightEnemy(e);
        System.out.println("You are a Knight, so you have an attack resistance of"+ getArmor() + ".");
        int knightDamage = this.getDamage();
        int enemyDamage = e.getDamage() - this.getArmor();  //A place of dynamic binding and polymorphism,
        int damageTaken = enemyDamage - knightDamage;
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
        return super.selectionInfo()+ "Name: "+this.getName()+ " Ability: Armor, get reduced damage from enemies, and has "+this.getPotionCount()+" healing potions.";
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
            available = " You can consume 1 potion to get 50 health";
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
    public int getArmor(){
        return this.armor;
    }
    public void setArmor(int newArmor){
        this.armor = newArmor;
    }
}