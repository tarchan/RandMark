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
        render();
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
		for (int i = 0; i < count / 4; i++) {
		}
		
		index();
	}
}
