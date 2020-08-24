package com.fun.poker;

import java.security.SecureRandom;
import java.util.*;

/**
 * 扑克牌洗牌算法
 *
 * @author yehuan
 * @version v1.0.0
 * @date 2017-03-03 11:13
 */
public class PokerMachine {

	private static final String[] POKER_COLORS = {"黑桃","红心","梅花","方块"};
	private static final String[] POKER_FACES_NORMAL = {"1","2","3","4","5","6","7","8","9","10","J","Q","K"}; // 普通,体现大小
	private static final String[] POKER_FACES_DZ = {"3","4","5","6","7","8","9","10","J","Q","K","1","2"}; // 斗地主,体现大小
	private SecureRandom rd = new SecureRandom();

	public PokerMachine() {
	}

	public Poker[] resetPokerList(String[] playRule) {
		Poker[] pokers = new Poker[54];

		int lastIndex = 0;
		for(int i = 0; i < POKER_COLORS.length; i++ ) {
			for( int j = 0; j < playRule.length; j++ ) {
				int value = j + 1;
				Poker poker = new Poker(POKER_COLORS[i],playRule[j],value);
				pokers[lastIndex++] = poker;
			}
		}

		// 填加大小鬼
		pokers[52] = new Poker("小","鬼",99);
		pokers[53] = new Poker("大","鬼",100);

		// 打乱
		for (int i = 0; i < 54; i++ ) {
			int randIndex = rd.nextInt(54);
			Poker tmp = pokers[i];
			pokers[i] = pokers[randIndex];
			pokers[randIndex] = tmp;
		}
		return pokers;
	}

	/**
	 * 扑克牌排序
	 * @param pokers 待排序扑克牌数组
	 */
	public void arrangePoker(Poker[] pokers) {
		Arrays.sort(pokers, new Comparator<Poker>() {
			@Override
			public int compare(Poker o1, Poker o2) {
				if(o1.getValue() < o2.getValue()) {
					return -1;
				}else if(o1.getValue() > o2.getValue()) {
					return 1;
				}
				return 0;
			}
		});
	}

	public static void main(String[] args) {
		PokerMachine pm = new PokerMachine();
		Poker[] pokers = pm.resetPokerList(POKER_FACES_DZ);
		for (Poker p : pokers ) {
			System.out.println(p);
		}

		Poker[] userOnePokers = new Poker[17];
		Poker[] userTwoPokers = new Poker[17];
		Poker[] userThreePokers = new Poker[20];

		int index = 0;
		for(int i = 0 ; i < 17 ; i++ ) {
			userOnePokers[i] = pokers[index++];
			userTwoPokers[i] = pokers[index++];
			userThreePokers[i] = pokers[index++];
		}
		userThreePokers[17] = pokers[index++];
		userThreePokers[18] = pokers[index++];
		userThreePokers[19] = pokers[index++];

		pm.arrangePoker(userOnePokers);
		pm.arrangePoker(userTwoPokers);
		pm.arrangePoker(userThreePokers);

		System.out.println(Arrays.asList(userOnePokers));
		System.out.println(Arrays.asList(userTwoPokers));
		System.out.println(Arrays.asList(userThreePokers));
	}

	/**
	 * 扑克类
	 */
	class Poker {
		private String pokerColor;
		private String pokerFace;
		private int value;

		public Poker(String pokerColor, String pokerFace,int value) {
			this.pokerFace = pokerFace;
			this.pokerColor = pokerColor;
			this.value = value;
		}

		public String getPokerFace() {
			return pokerFace;
		}

		public void setPokerFace(String pokerFace) {
			this.pokerFace = pokerFace;
		}

		public String getPokerColor() {
			return pokerColor;
		}

		public void setPokerColor(String pokerColor) {
			this.pokerColor = pokerColor;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return pokerColor+pokerFace;
		}
	}

}
