package sorting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LeaderBoard {
	static int[] leaders;
	static int[] aliceScores;
	public static void main(String[] args) throws IOException {
		
		File file = new File("C:\\Users\\RamaKrishna\\Desktop\\hackerrankTemp\\input.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String str = null;
		while ((str = br.readLine()) != null) {
			String[] split = str.split(" ");
			if(split.length>1) {
				if(leaders==null) {
					leaders=new int[split.length];
					for(int i=0; i<split.length;i++) {
						leaders[i]=Integer.parseInt(split[i]);
					}
				}else {
					aliceScores=new int[split.length];
					for(int i=0; i<split.length;i++) {
						aliceScores[i]=Integer.parseInt(split[i]);
					}
				}
			}
		}
		climbingLeaderboard(leaders, aliceScores);
	}

	static int[] climbingLeaderboard(int[] scores, int[] alice) {
		System.out.println("In climbing Leaderboard");
		List<Integer> resultList = new ArrayList<>();
		Set<Integer> scoreSet = new HashSet<>();
		List<Integer> rankList = new ArrayList<>();
		scoreSet.addAll(Arrays.stream(scores).boxed().collect(Collectors.toList()));
		rankList.addAll(scoreSet);
		Collections.sort(rankList, Collections.reverseOrder());
		// System.out.println(rankList);
		int rank = 0;
		for (int i = 0; i < alice.length; i++) {
			if (scoreSet.contains(alice[i])) {
				rank = rankList.indexOf(alice[i]) + 1;
				resultList.add(rank);
			} else {
				rankList.add(alice[i]);
				Collections.sort(rankList, Collections.reverseOrder());
				rank = rankList.indexOf(alice[i]) + 1;
				resultList.add(rank);
				rankList.remove(rankList.indexOf(alice[i]));
			}
		}

		int[] resultFinal = resultList.stream().mapToInt(Integer::intValue).toArray();
		// System.out.println(Arrays.toString(resultFinal));
		return resultFinal;
	}
}
