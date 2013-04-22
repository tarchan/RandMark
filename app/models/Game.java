package models;

import play.db.jpa.Model;

public class Game extends Model {
    public int count;
    public Player[] player;
    public String result;

	public Game() {
		count = 4;
	}
}
