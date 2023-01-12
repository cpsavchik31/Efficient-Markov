import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	public EfficientMarkov (int order) {
		super(order);
		myMap = new HashMap<>();
	}

	public EfficientMarkov() {
		this(5);
	}
	/*
	@param text
	fills myMap with (substring, following character)
	 */
	@Override
	public void setTraining(String text) {
		myText = text;
		myMap.clear();
		for (int i = 0; i < myText.length() - myOrder + 1; i += 1) {
			if (!myMap.containsKey(myText.substring(i, i + myOrder))) {
				myMap.put(myText.substring(i, i + myOrder), new ArrayList<String>());
			}
			if (i+myOrder < myText.length()) {
				myMap.get(myText.substring(i, i + myOrder)).add(myText.substring(i + myOrder, i + myOrder + 1));
			}
			else {
				myMap.get(myText.substring(i, i + myOrder)).add(PSEUDO_EOS);
			}
	}
	}
	/*
	@param key
	checks to see if myMap contains specified key
	@return the value associated with the key
	 */
	@Override
	public ArrayList<String> getFollows(String key) {
			if (!myMap.containsKey(key)) {
				throw new NoSuchElementException(key+" not in map");
			}
			return myMap.get(key);
	}
}


