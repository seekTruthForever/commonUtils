package com.whv.common.utils.tree;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * �ֵ���
 * @author whv
 */
public class TrieTree {
    public static void main(String[] args){
        TrieTree tree = new TrieTree();
        tree.insertBatch("abacus,abandon,abbey,abdomen,abhor,abide,ability," +
                "ablate,ablation,able,abnormal,abnormally,aboard,abolish,abortion," +
                "abound,about,above,abroad,abrupt,abruptly,Absence,absent,absentee," +
                "absentminded,absentmindedly,absolute,absolutely,absorb,absorbability," +
                "abstract,absurd,abundance",",");
        tree.insertBatch("ban45ban,������shgdg hdfggdj-fhhjdj  dgfg/fjkk(hdg)dggg&ahysh");
        String queryPrefix = "abo";
        List<String> wordList =  tree.pre2word(queryPrefix);
        System.out.println("����ǰ׺ "+queryPrefix+" �Ĵ���:"+(wordList!=null?wordList.toString():"��ѯ����"));
        List<String> wordList2 =  tree.getWordList(null,"",new ArrayList<String>());
        System.out.println("ȫ������:"+wordList2.toString());
    }
    /**
     * ���ڵ�
     * @author whv
     *
     */
    class TrieNode{
        public Map<Character,TrieNode> trieNodeMap;
        //�Ƿ���ĩ�ڵ�
        public boolean isEnd;
        public TrieNode(){
            trieNodeMap = new ConcurrentHashMap<Character, TrieNode>();
        }
    }
    //���ڵ�
    public TrieNode root;
    public TrieTree(){
        root = new TrieNode();
    }

    /**
     * ����
     * @param word ȫ��
     */
    public void insert(String word){
    	if(word==null||"".equals(word.trim())) {
        	return;
        }
        TrieNode node = root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if(!node.trieNodeMap.containsKey(ch)){
                node.trieNodeMap.put(ch,new TrieNode());
            }
            node = node.trieNodeMap.get(ch);
        }
        node.isEnd = true;
    }
    /**
     * ���������ַ�����
     * @param words �ַ���
     */
    public void insertBatch(String words){
        if(words==null||"".equals(words.trim())) {
        	return;
        }
        TrieNode node = root;
        for(int i=0;i<words.length();i++){
            char ch = words.charAt(i);
            if(Character.isLetterOrDigit(ch)) {
            	if(!node.trieNodeMap.containsKey(ch)){
                    node.trieNodeMap.put(ch,new TrieNode());
                }
            	node = node.trieNodeMap.get(ch);
            }else {
            	if(node != root) {
            		node.isEnd = true;
            	}
            	node = root;
            }
        }
    }
    /**
     * ��������
     * @param words ƴ�Ӻ���ַ�
     * @param split �ָ��
     */
    public void insertBatch(String words,String split){
        if(split==null || "".equals(split)){
            return;
        }
        if(words==null||"".equals(words.trim())) {
        	return;
        }
        String[] wordArray = words.split(split);
        for(int i=0;i<wordArray.length;i++){
            String word = wordArray[i];
            insert(word);
//            System.out.println("��������"+(i+1)+"/"+(wordArray.length)+":"+word);
        }
    }
    /**
     * ȫ������
     * @param word ȫ��
     * @return
     */
    public boolean search(String word){
        TrieNode node = root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if(!node.trieNodeMap.containsKey(ch)){
               return false;
            }
            node = node.trieNodeMap.get(ch);
        }
        return node.isEnd;
    }

    /**
     * ǰ׺����
     * @param prefix ǰ׺
     * @return
     */
    public boolean startWith(String prefix){
        TrieNode node = root;
        for(int i=0;i<prefix.length();i++){
            char ch = prefix.charAt(i);
            if(!node.trieNodeMap.containsKey(ch)){
                return false;
            }
            node = node.trieNodeMap.get(ch);
        }
        return true;
    }

    /**
     * ��ǰ׺��ѯ����
     * @param prefix ǰ׺
     * @return
     */
    public List pre2word(String prefix){
        TrieNode node = root;
        for(int i=0;i<prefix.length();i++){
            char ch = prefix.charAt(i);
            if(!node.trieNodeMap.containsKey(ch)){
                return null;
            }
            node = node.trieNodeMap.get(ch);
        }
        TrieNode preNode = node;
        List<String> rsltList = new ArrayList<String>();
        rsltList = getWordList(preNode,prefix,rsltList);
        return rsltList;
    }

    /**
     * ��ĳ�ڵ����ʣ���ַ�
     * @param node
     * @param prefix
     * @param rsltList
     * @return
     */
    public List getWordList(TrieNode node,String prefix,List<String> rsltList){
        //�ڵ�Ϊnullʱ��Ĭ�Ͻڵ�Ϊ���ڵ�
        if(node == null) node = root;
        if(prefix == null) prefix="";
        if(rsltList == null) rsltList = new ArrayList<String>();
        if(node.isEnd){
            rsltList.add(prefix);
        }
        Iterator<Character> keyIt =  node.trieNodeMap.keySet().iterator();
        while(keyIt.hasNext()){
            char keych = keyIt.next();
            rsltList = getWordList(node.trieNodeMap.get(keych),prefix.concat(String.valueOf(keych)),rsltList);
        }
        return rsltList;
    }

}
