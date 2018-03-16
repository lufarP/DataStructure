package ds;

import java.util.HashMap;

class Trie
{
	//total number of words inserted
	private int numberOfWords;

	private class TrieNode
	{
		private HashMap<Character, TrieNode> character;
		boolean isWordComplete;
		private int numberOfWords;

		TrieNode()
		{
			character = new HashMap<Character, TrieNode>();
			isWordComplete = false;
			numberOfWords=0;
		}
	}

	private final TrieNode root;

	public Trie()
		{
			root = new TrieNode();
			numberOfWords = 0;
		}
	// total number of words inserted
	public int getTotalNumberOfWords()
		{
			return numberOfWords;
		}

	// insert a word in TRie
	public void insertWord(String word)
		{
			TrieNode current = root;
			current.numberOfWords++;
			int index, size = word.length();
			Character letter;
			for (index = 0; index < size; index++)
				{
					letter = word.charAt(index);
					if (!current.character.containsKey(letter))
						{
							current.character.put(letter, new TrieNode());
						}
					current = current.character.get(letter);
					current.numberOfWords++;
				}
			current.isWordComplete = true;
			numberOfWords++;
		}

	
	private TrieNode isWordPresent(String word)
		{
			TrieNode current = root;
			int index, size;
			size = word.length();
			Character letter;
			for (index = 0; index < size; index++)
				{
					letter = word.charAt(index);
					if (current.character.containsKey(letter))
						{
							current = current.character.get(letter);
						} else
							return null;// word not found

				}
			return current;// word found and last character node returned

		}

	//check whetehr the compelete word is present or not
	public boolean isCompleteWordPresent(String word)
		{
			TrieNode lastNode = isWordPresent(word);
			if (lastNode == null)// word not present
				return false;
			else
				return lastNode.isWordComplete;// returns whether it is a
			// complete word or not
		}
	//returns the number of word with same prefix as the word
	public int numberOfWordWithPrefix(String word)
	{
		TrieNode last=isWordPresent(word);
		if(last==null)
			return 0;
		else
			return last.numberOfWords;
	}
	//checks whether a word with prefix as the word is present or not
	public boolean isPrefixWordPresent(String word)
		{
			TrieNode lastNode = isWordPresent(word);
			if (lastNode == null)
				return false;
			else
				return true;
		}

	//delete a word from the trie structure
	public void delete(String word)
		{
			if(!isCompleteWordPresent(word))
				throw new IllegalStateException("Word Not Present");
			deleteWord(word, word.length(), 0, root);
			numberOfWords--;
		}

	private boolean deleteWord(String word, int size, int index, TrieNode current)
		{
			if (index == size)
				{
					return current.character.size() == 0;
				} 
			else
				{
					boolean deletable = deleteWord(word, size, index + 1, current.character.get(word.charAt(index)));
					if (deletable)
						{
							current.character.remove(word.charAt(index));
							return current.character.size() == 0;
						} 
					else
						return false;
				}
		}
	public String getlongestPrefix(String word)
		{
			/*
			 * returns the longest prefix of the string which is also a word in dictionary.
			 * if no prefix then returns null
			 */
			TrieNode current=root;
			int endIndex=0;
			int size=word.length();
			char letter;
			for(int index=0;index<size;index++)
				{
					letter=word.charAt(index);
					if(current.character.containsKey(letter))
						{
							if(current.isWordComplete)
								endIndex=index;
							current=current.character.get(letter);
						}
					else
						break;
				}
			if(endIndex==0)//no prefix found
				return null;
			else
				return word.substring(0, endIndex);
		}


}
