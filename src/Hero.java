//Superclass Hero
//
public class Hero{
    //Instance variables
    //
    private String name;
    private int health;
    private int damage;

    //Constructor
    //
    public Hero(String name, int health, int damage){
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    //Fighting method
    //
    public void fightEnemy(Enemy e){
        System.out.println("You are fighting : "+e.getName());
        System.out.println("Your current health is: "+this.getHealth());
    }

    //Methods to get information about hero during selection and after fights.
    //
    public String selectionInfo(){
        return ": ";
    }
    public String getInfo(){
        return "Your current status:: Hero: "+getName()+", Health: "+getHealth()+ " ";
    }

    //Mehtod to change health of hero by an amount.
    //
    public void changeHealth(int change){
        this.health = this.health + change;
    }

    //Setter and getter methods
    //
    public String getName(){
        return this.name;
    }
    public int getHealth(){
        return this.health;
    }
    public int getDamage(){
        return this.damage;
    }
    public void setName (String name){
        this.name = name;
    }
    public void setHealth (int health){
        this.health = health;
    }
    public void setDamage (int damage){
        this.damage = damage;
    }
}