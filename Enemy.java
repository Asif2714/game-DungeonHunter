//Superclass Enemy
//
public class Enemy{
    //Instance variables
    //
    private String name;
    private int damage;
    private boolean alive;
    
    //Constructor
    //
    public Enemy(String name, int damage){
        this.name = name;
        this.damage = damage;
        this.alive = true;
    }
    
    //Method to get description of the enemy
    //
    public String getDescription(){
        return "Name: "+getName() + ", Damage: "+getDamage();
    }
    
    //Setter and getter methods
    //
    public String getName(){
        return this.name;
    }
    public int getDamage(){
        return this.damage;
    }
    public boolean isAlive(){
        return this.alive;
    }
    public void setName(String newName){
        this.name = newName;
    }
    public void setDamage(int newDamage){
        this.damage = newDamage;
    }
    public void setAlive(boolean newAlive){
        this.alive = newAlive;
    }
}