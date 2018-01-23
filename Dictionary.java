import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;


public class Dictionary {

	public static void main(String[] args) throws IOException {

		FileInputStream fis = new FileInputStream("alphabet.txt");

		Map<Character, HashSet<Character>> map = new HashMap<Character, HashSet<Character>>();

		HashSet<Character> newset = new HashSet<Character>();
		HashSet<Character> set = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line1 = br.readLine().trim();
		String line2 = null;
		int length = 0;

		for (int i =0;i<line1.length() ;i++ ) {
			if(i==0){
				map.put(line1.charAt(i),new HashSet<Character>());
			}
			else{
				if(line1.charAt(i)!=line1.charAt(0)){
					map.put(line1.charAt(i),new HashSet<Character>());
					addToSet(map.get(line1.charAt(i)), line1.charAt(0));
				}
			}
		}

		while ((line2 = br.readLine()) != null) {

			line2 = line2.trim();
			length = line2.length();

			for (int i = 0; i < length; i++) {

				if (i < line1.length()) {

					if (line1.charAt(i) != line2.charAt(i)) {

						if(map.get(line2.charAt(i))!=null){
							addToSet(map.get(line2.charAt(i)), line1.charAt(i));
							break;
						}
						else{
							map.put(line2.charAt(i),new HashSet<Character>());
							addToSet(map.get(line2.charAt(i)), line1.charAt(i));
							break;
						}
					}
				} 
			}
			line1 = line2;
		}

		br.close();

		char[] result = new char[map.size()];
		System.out.println(map.size());
		Iterator<Map.Entry<Character, HashSet<Character>>> i = map.entrySet().iterator();

		while (i.hasNext()) {

			Map.Entry<Character, HashSet<Character>> m = i.next();
			set = m.getValue();
			newset.clear();
			newset.addAll(set);

			for (Character a : newset) {
				set.addAll(map.get(a));
			}

			result[m.getValue().size()] = m.getKey();
		}

		for (int j = 0; j < result.length; j++) {
			System.out.println(j + " " + result[j]);
		}
	}

	private static void addToSet(HashSet<Character> set, char c) {
		set.add(c);
	}
}