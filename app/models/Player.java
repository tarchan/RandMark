package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity
public class Player extends Model {
    public int number;
    public String name;

	public Player(int num) {
		number= num;
		name = String.valueOf(num);
	}
}
