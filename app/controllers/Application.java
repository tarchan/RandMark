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
		List<Player> players = Player.findAll();
		render(players);
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
		//int max = count * (count - 1);
		int max = count * (count - 1) * (count - 2) * (count - 3);
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
		String combi = " from Player p1, Player p2, Player p3, Player p4 where p1.number < p2.number and p3.number < p4.number and p1.number != p3.number and p1.number != p4.number and p2.number != p3.number and p2.number != p4.number";
		List<Player> combi1 = Player.find("select p1" + combi).fetch();
		List<Player> combi2 = Player.find("select p2" + combi).fetch();
		List<Player> combi3 = Player.find("select p3" + combi).fetch();
		List<Player> combi4 = Player.find("select p4" + combi).fetch();
		Logger.info("###parent type: %s (%s)", combi1.getClass().getName(), combi1.size());
		for (int i = 0; i < combi1.size(); i++) {
			Player p1 = combi1.get(i);
			Player p2 = combi2.get(i);
			Player p3 = combi3.get(i);
			Player p4 = combi4.get(i);
			Logger.info("##child type: %s, %s - %s, %s", p1.number, p2.number, p3.number, p4.number);
			list.offer(p1.number);
			list.offer(p2.number);
			list.offer(p3.number);
			list.offer(p4.number);
		}
		/*for (Player n4 : combi1) {
			Logger.info("##child type: %s, %s (%s)", n4.getClass().getName(), n4.toString(), n4.number);
			List l4 = Arrays.asList(n4);
			for (Object l : l4) {
				//Logger.info("#mago type: %s, %s", l.getClass().getName(), l.toString());
			}
		}*/
		/*
		for (int i = 0; i < count; i++) {
			list.offer(i + 1);
		}
		Collections.shuffle(list);
		*/
	}
}
