package referenceBook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IndexedTree {

	/* �� ��尡 ��������� ������ ��ǥ��(�ּ�,�ִ밪,�Ǵ� ��)�� ������ �ִ� Ʈ��(��ȭƮ��)
	 * �ܸ����� ���� �迭�� ��
	 * �׿��� ���� ���������� ��ǥ��
	 * 
	 * ex) N(1<=N<=100,000)���� �����Ͱ� �־�������, Q(1<=Q<=100,000)���� ������ �����ϴ� �ڵ带 �ۼ��϶�, ������ 3���� ���� C,X,Y�� �̷���� ������, 
	 * ������ ������ C�� 0�� ��� X~Y������ �ּڰ��� ����ϰ� C�� 1�� ��� X���� �����͸� Y�� �����Ѵ�
	 * */
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		System.setIn(new FileInputStream("sample.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		int TC = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		for(int tc=1; tc<TC;tc++){
			int z = Integer.parseInt(st.nextToken());
		}
	}

}
