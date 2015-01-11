package com.gmn.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// Find the shortest unique prefix for each input string that uniquely identifes the string
// Sample input: [cat, hotdog, catalog, dog, category ]
// Output: [cat:cat, hotdog:h, catalog:cata, dog:dog, category:cate]

public class StringPrefixes {


	public Map<String, String> findPrefixes(List<String> strList ) {
		Map<String, String> wordPrefixes = new HashMap<>();
		TrieNode root = new TrieNode(null, null);
		Map<String, TrieNode> lastNodes = new HashMap<>();
		for( String word : strList) {
			TrieNode lastNode = addWordToTrie(root, word);
			lastNodes.put(word, lastNode);
		}
		
		for( Entry<String, TrieNode> entry : lastNodes.entrySet() ) {
			String prefix = findPrefix(entry.getKey(), entry.getValue());
			System.out.println(entry.getKey() + ", " + prefix);
			wordPrefixes.put(prefix, entry.getKey());

		}
		return wordPrefixes;
	}
	
	public TrieNode addWordToTrie(TrieNode root, String word) {
		TrieNode lastNode = root;
		for( int ctr = 0; ctr < word.length(); ctr++) {
			lastNode = lastNode.addChar(word.charAt(ctr));
		}
		return lastNode;
	}
	
	public String findPrefix(String word, TrieNode wordLeafNode) {
		
		TrieNode node = wordLeafNode;
		int numNodesTraversed = 0;
		while( node != null && node.getNumberOfchildren() <= 1 ) {
			node = node.getParent();
			numNodesTraversed++;
		}
		if( numNodesTraversed == 0 ) {
			return word;
		}
		if( numNodesTraversed == word.length() ) {
			return word.substring(0, 1);
		} else {
			return word.substring(0, word.length() - (numNodesTraversed-1));
		}
	}

	public static void main(String[] args) {
		String[] input = new String[] {"cat", "catalog", "catergory", "category", "dog", "hotdog"};
		StringPrefixes sp = new StringPrefixes();
		List<String> words = Arrays.asList(input);
		Map<String, String> prefixes = sp.findPrefixes(words);
		
		StringBuilder sb = new StringBuilder();
		
		boolean first = true;
		for( Entry<String, String> entry : prefixes.entrySet() ) {
			if( first ) {
				first = false;
			} else {
				sb.append(", ");
			}
			sb.append(entry.getKey()).append(":").append(entry.getValue());
		}
		
		System.out.println(sb.toString());
	}
	

}

class TrieNode {
	
	private TrieNode parent;
	private TrieNode[] children = new TrieNode[26];
	private int numberOfChildren = 0;
	private Character character;
	
	public TrieNode(Character character, TrieNode parent) {
		this.parent = parent;
		this.character = character;
	}
	
	public TrieNode addChar(char character) {
		
		int index = character - 'a';
		if( children[index] == null ) {
			children[index] = new TrieNode(character, this);
			numberOfChildren++;
		}
		return children[index];
	}
	
	public TrieNode findChar(char character) {
		return children[character-'a'];
	}
	
	public int getNumberOfchildren() {
		return numberOfChildren;
	}
	
	public TrieNode getParent() {
		return parent;
	}
	
	public Character getCharacter() {
		return this.character;
	}
}
	