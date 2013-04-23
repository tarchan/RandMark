package controllers;

import play.*;
import play.mvc.*;

import java.util.*;
import java.util.concurrent.*;

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

	/**
	 * 指定された人数分の新しい乱数表を作成します。
	 */
	public static void newGame(int count) {
		Player.deleteAll();
		Game.deleteAll();
		
		if (count < 4) {
			index();
		}
		
		// make player
		for (int i = 0; i < count; i++) {
			Player player = new Player(i + 1);
			player.save();
		}
		
		// make game
		int max = count * (count - 1);
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < max; i += 4) {
			if (list.size() < 4) {
				offerPlayer(list, count);
			}
			int p1 = list.remove();
			int p2 = list.remove();
			int p3 = list.remove();
			int p4 = list.remove();
			Game game = new Game(p1, p2, p3, p4);
			game.result = "2-0";
			game.save();
		}
		
		index();
	}

	/**
	 * 乱数表を追加します。
	 */
	public static void appendGame() {
		long count = Player.count();
	}

	@Util
	public static void offerPlayer(LinkedList<Integer> list, int count) {
		for (int i = 0; i < count; i++) {
			list.offer(i + 1);
		}
		Collections.shuffle(list);
	}
}
