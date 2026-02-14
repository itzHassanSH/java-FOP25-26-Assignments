package h05;

public abstract class BaseDurable implements Durable {
    private double durability = 100;
    @Override
    public double getDurability() {
        return durability;
    }

    @Override
    public void setDurability(double durability) {
        if (durability<=0) {
            this.durability = 0;
        } else if (durability>=100) {
            this.durability = 100;
        } else {this.durability = durability;}
    }

    @Override
    public void reduceDurability(double amount) {
        durability = durability-amount<0? 0:durability-amount;
    }
}
