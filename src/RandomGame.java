import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class RandomGame {

	static int entryCnt, pickCnt;
	static ArrayList<Rank> list;
	static ArrayList<String> name;
	static ArrayList<Integer> score;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("entry.txt"));
		entryCnt = Integer.parseInt(br.readLine());
		pickCnt  = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		name = new ArrayList<>();
		score = new ArrayList<>();
		for(int i=0;i<entryCnt;i++){
//			list.add(new Rank(name, r.nextInt(1000)));
			name.add(br.readLine());
		}
		while(true){
			makeRandomData();
			inputRank();
			printResult();
			System.out.println("다시 돌려보시려면 R을 종료하려면 그외의 값을 입력하세요");
			BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
			String next = br2.readLine();
			if(!"R".equalsIgnoreCase(next)){
				break;
			}
			score.clear();
			list.clear();
		}
		
		

		
		
	}
	private static void inputRank() {
		for(int i=0;i<entryCnt;i++){
			list.add(new Rank(name.get(i), score.get(i)));
		}		
		Collections.sort(list, new Comparator<Rank>(){

			@Override
			public int compare(Rank o1, Rank o2) {
				// TODO Auto-generated method stub
				return o2.score-o1.score;//desc
			}
			
		});		
	}

		
	private static void makeRandomData() {
		Random r = new Random(entryCnt);		
		r.setSeed(System.currentTimeMillis());
		for(int i=0;i<entryCnt;i++){
			int s = r.nextInt(1000);
			while(true){
				if(score.contains(s)){
					s = r.nextInt(1000);
				}else{
					score.add(s);
					break;
				}
			}
		}		
	}
	private static void printResult() throws Exception {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		System.out.println("랜덤게임을 시작합니다.");
		System.out.println("총 참가자  "+entryCnt+ ", 이 중 당첨자 "+ pickCnt+"명 을 뽑습니다");
		System.out.println("*************************************************");
		System.out.println("당첨자 리스트입니다.");
		for(int i=0;i<pickCnt;i++){
			System.out.println(list.get(i).name+"님.");				
		}
		System.out.println("입니다. 축하합니다!");
		System.out.println("순위는 아래와 같이 발표합니다.");
		int i=1;
		for(Rank r : list){			
			String a = "";
			a = i+"위 참가자 : "+r.name;
			int l = a.length();
			for(int j=l;j<40;j++){
				a = a+" ";
			}
			System.out.println(a+", 점수 : "+ r.score);
			i++;
		}		
	}
}
class Rank{
	public Rank(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}
	String name;
	int score;
}