package mars.chatroom.bean;

/**
 * Created by hluo on 2017/5/3.
 */

public class RedBag {
    private double price;
    private String des;
    private int type;
    private int num;

    public RedBag(){}

    public RedBag(double price, String des) {
        this.price = price;
        this.des = des;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
