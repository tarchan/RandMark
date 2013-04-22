package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity
public class Game extends Model {
    public int count;
    public int[] player;
    public String result;

	public Game(int p1, int p2, int p3, int p4) {
		count = 4;
		player = new int[count];
		player[0] = p1;
		player[1] = p2;
		player[2] = p3;
		player[3] = p4;
	}

	public String toString() {
		return String.format("%s %s - %s %s", player[0], player[1], player[2], player[3]);
	}
}
