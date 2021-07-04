package org.example;

import java.util.Random;

public class Map {
	private char[][] map;
	private int size;
	private int[] playerCoords;
	private int[][] enemiesCoords;
	private int enemiesCount;
	private Random random;

	public Map(int size, int wallsCount, int enemiesCount) {
		this.map = new char[size][size];
		this.size = size;
		this.random = new Random();
		this.playerCoords = new int[2];
		this.enemiesCount = enemiesCount;
		this.enemiesCoords = new int[2][enemiesCount];
		initMap(wallsCount);
	}

	private void initMap(int wallsCount) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				map[i][j] = '.';
		}

		for (int i = 0; i < wallsCount; i++)
			addWall();
	}
	private void addPlayer() {
		playerCoords[0] = random.nextInt(size);
		playerCoords[1] = random.nextInt(size);
		map[playerCoords[0]][playerCoords[1]] = 'o';
	}

	private void addWall() {
		int i, j;
		do {
			i = random.nextInt(size);
			j = random.nextInt(size);
		} while (map[i][j] != '.');
		map[i][j] = '#';
	}



	public void printMap() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				System.out.print(map[i][j] + " ");
			System.out.println();
		}
	}
}
