package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

	/**
	 * Game一覧
	 * 試合の乱数表を表示します。
	 */
    public static void index() {
    	List<Game> games = Game.findAll();
        render(games);
    }

	/**
	 * Gameの詳細を表示します。
	 */
	public static void game(long id) {
		List<Game> games = Game.find("byId", id).fetch();
		Game game = games.get(0);
		render(game);
	}

	/**
	 * Player一覧
	 * プレイヤーの勝敗表を表示します。
	 */
	public static void player() {
	}

	public static void newGame(int count) {
		Player.deleteAll();
		Game.deleteAll();
		
		// make player
		for (int i = 0; i < count; i++) {
			Player player = new Player(i + 1);
			player.save();
		}
		
		// make game
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			list.add(i + 1);
		}
		Collections.shuffle(list);
		for (int i = 0; i < count; i += 4) {
			int p1 = list.get(i);
			int p2 = list.get(i + 1);
			int p3 = list.get(i + 2);
			int p4 = list.get(i + 3);
			Game game = new Game(p1, p2, p3, p4);
			game.save();
		}
		
		index();
	}
}
