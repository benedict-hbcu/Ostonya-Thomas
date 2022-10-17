package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Util {

	private static final String ANSI_BLACK = "\u001B[30m";
	private static final String BACKGROUND_YELLOW	= "\u001B[43m";
	private static final String BACKGROUND_GREEN	= "\u001B[42m";
	private static final String BACKGROUND_GRAY = "\033[48;2;142;142;142m";
	private static final String ANSI_RESET = "\u001B[0m";

	protected enum Result {
		HIT,
		MISS,
		SEMI_HIT
	}

	private static final Map<Result, String> COLOR_MAP;

	static {
		COLOR_MAP = Map.of(
				Result.HIT, BACKGROUND_GREEN,
				Result.MISS, BACKGROUND_GRAY,
				Result.SEMI_HIT, BACKGROUND_YELLOW
		);
	}

	protected static String getFormattedLetter(char c, Result r) {
		return String.format("%s%s%s%s", COLOR_MAP.get(r), ANSI_BLACK, c, ANSI_RESET);
	}

	protected static String getRandomWord() {
		List<String> lines;
		try {
			lines = Files.readAllLines(Path.of(Main.class.getClassLoader().getResource("wordle-answers-alphabetical.txt").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		Random random = new Random();
		return lines.get(random.nextInt(lines.size()));
	}
}
