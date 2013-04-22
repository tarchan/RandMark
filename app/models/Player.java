package models;

import play.db.jpa.Model;

public class Player extends Model {
    public int number;
    public String name;

	public Player(int num) {
		number= num;
		name = String.valueOf(num);
	}
}
