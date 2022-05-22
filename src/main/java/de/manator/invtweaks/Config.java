package de.manator.invtweaks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Config {

	private static String path = "/config.yml";
	
	public final static int CHEST = 0;
	public final static int BARREL = 1;
	public final static int SHULKER = 2;
	public final static int ENDER = 3;

	public static void createConfig(File dataFolder) {
		if (!dataFolder.exists()) {
			dataFolder.mkdirs();

			File config = new File(dataFolder.getAbsolutePath() + path);

			try {
				config.createNewFile();
				BufferedWriter bw = new BufferedWriter(new FileWriter(config));

				bw.write("Toggle-Chest-Sorting: true");
				bw.newLine();
				bw.write("Toggle-Barrel-Sorting: true");
				bw.newLine();
				bw.write("Toggle-Shulker-Box-Sorting: false");
				bw.newLine();
				bw.write("Toggle-Ender-Chest-Sorting: false");
				
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean configExists(File dataFolder) {
		return (new File(dataFolder.getAbsolutePath() + path)).exists();
	}
	
	
	
	public static void toggleSorting(File dataFolder, int invtype) {
		File config = new File(dataFolder.getAbsolutePath() + path);
		if (!config.exists()) {
			createConfig(dataFolder);
		}

		ArrayList<String> lines = readLines(config);
		
		String type = getType(invtype);
		
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).equalsIgnoreCase(type + " true")) {
				lines.set(i, type + " false");
				break;
			} else if (lines.get(i).equalsIgnoreCase(type + " false")) {
				lines.set(i, type + " true");
				break;
			}
		}

		write(config, lines);

	}
	
	private static String getType(int i) {
		switch(i) {
			case 0:
				return "Toggle-Chest-Sorting:";
			case 1:
				return "Toggle-Barrel-Sorting:";
			case 2:
				return "Toggle-Shulker-Box-Sorting:";
			case 3:
				return "Toggle-Ender-Chest-Sorting:";
			default:
				return null;
		}
	}
	
	public static boolean isSortingEnabled(File dataFolder, int invtype) {
		File config = new File(dataFolder.getAbsolutePath() + path);
		if (!config.exists()) {
			createConfig(dataFolder);
		}

		ArrayList<String> lines = readLines(config);
		
		String type = getType(invtype);
		
		for (int i = 0; i < lines.size(); i++) {
			if (lines.get(i).equalsIgnoreCase(type + " true")) {
				return true;
			}
		}
		return false;
	}
	
	private static void write(File config, List<String> lines) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(config));
			for (String line : lines) {
				bw.write(line);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ArrayList<String> readLines(File config) {
		ArrayList<String> lines = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(config));

			while (br.ready()) {
				lines.add(br.readLine());
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

}
