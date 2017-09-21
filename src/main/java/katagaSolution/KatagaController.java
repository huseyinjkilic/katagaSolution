package katagaSolution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KatagaController {

    @RequestMapping(path = "/calculateKataga/{firstWord}/{secondWord}", method = RequestMethod.GET)
    public String calculateKataga(@PathVariable String firstWord, @PathVariable String secondWord) {
		try {
			return this.findChain(firstWord, secondWord);
		} catch (Exception e) {
			e.printStackTrace();
			return "Error accured " + e.getMessage();
		}
    }
    
	List<String> words;

	private void readWordsOfLength(int wordLength) throws IOException {
		words = new ArrayList<String>();
		
		ClassPathResource resource = new ClassPathResource("wordlist.txt");
		InputStream inputStream = resource.getInputStream();
		BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
		
		try {
			String line;
			while ((line = r.readLine()) != null) {
				if(line.length() == wordLength)
				words.add(line);
			}
		} finally {
			r.close();
		}
	}

	private void checkWordinDictionary(String word) throws Exception {
		if (!words.contains(word)) {
			throw new Exception("Word is not in dictionary");
		}
	}

	List<String> candidates;
	Map<String, String> seen;
	int count;

	public String findChain(String start, String end)
		throws Exception {
		
		if (start.length() != end.length()) {
			throw new Exception("words must be of the same length");
		}
			
		if(start.equals(end)) {
			return start;
		}
			
		
		readWordsOfLength(start.length());
		
		checkWordinDictionary(start);
		checkWordinDictionary(end);

		candidates = new LinkedList<String>();
		seen = new HashMap<String, String>();
		count = 0;
		
		candidates.add(start);
		seen.put(start, null);

		return searchForEndWord(end);
	}

	private String searchForEndWord(String end) throws Exception {
		while (candidates.size() > 0) {
			if (count++ > 15000)
				throw new Exception("exceeded search limit");

			String c = (String) candidates.remove(0);

			Iterator<String> iter = words.iterator();
			while (iter.hasNext()) {
				String w = (String) iter.next();
				if (!seen.containsKey(w) && checkWordDiffrences(c, w)) {
					seen.put(w, c);

					if (end.equals(w))
						return formatWordChain(w);

					candidates.add(w);
				}
			}
		}
		return null;
	}

	private String formatWordChain(String word) {
		String result = word;
		word = (String) seen.get(word);
		while (word != null) {
			result = word + "," + result;
			word = (String) seen.get(word);
		}
		return result;
	}

	public boolean checkWordDiffrences(String stringToBeCheckedForOneCharacterDiff, String dictionaryWord) {
		int numberOfDiffrenceForCharacter = 0;
		int stringLength = stringToBeCheckedForOneCharacterDiff.length();
		for (int i = 0; i < stringLength; i++) {
			if (stringToBeCheckedForOneCharacterDiff.charAt(i) != dictionaryWord.charAt(i)) {
				numberOfDiffrenceForCharacter++;
			}
			if(numberOfDiffrenceForCharacter > 1)
				return false;
		}
		return true;
	}
    
}
