import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

    public class EfficientWordMarkov extends BaseWordMarkov {

        private Map<WordGram, ArrayList<String>> myMap;
        public EfficientWordMarkov (int order) {
            super(order);
            myMap = new HashMap<>(); //initialize new hashmap
        }
        public EfficientWordMarkov() {
            this(2);
        }
        @Override

        /** splits the text into array myWords
        * uses a for loop to create keys and
         * values for myMap and fill the map
         * with (Wordgram, arraylist of the following word)
        */
        public void setTraining(String text) {
            super.setTraining(text);
            String [] myWords = text.split("\\s+");
            myMap.clear();
            for (int i = 0; i < myWords.length - myOrder + 1; i += 1) {
                WordGram wg = new WordGram(myWords, i, myOrder);
                if (!myMap.containsKey(wg)) {
                    myMap.put((wg), new ArrayList<String>());
                }
                if (i+myOrder < myWords.length) {
                myMap.get(wg).add(myWords[i+myOrder]);
            }
                else {
                 myMap.get(wg).add(PSEUDO_EOS);
                }
                }

        }

        /**
         *
         *looks in myMap for a specific wordgram key
         @param wg
         @return myMap(key)
         */
        @Override
        public ArrayList<String> getFollows(WordGram wg) {
            if (!myMap.containsKey(wg)) {
                throw new NoSuchElementException(wg+" not in map");
            }
            return myMap.get(wg);
        }
    }

