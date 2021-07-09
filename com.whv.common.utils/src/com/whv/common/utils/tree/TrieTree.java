package com.whv.common.utils.tree;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 字典树
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
        tree.insertBatch("ban45ban,帮别个，shgdg hdfggdj-fhhjdj  dgfg/fjkk(hdg)dggg&ahysh");
        String queryPrefix = "abo";
        List<String> wordList =  tree.pre2word(queryPrefix);
        System.out.println("查找前缀 "+queryPrefix+" 的词语:"+(wordList!=null?wordList.toString():"查询不到"));
        List<String> wordList2 =  tree.getWordList(null,"",new ArrayList<String>());
        System.out.println("全部词语:"+wordList2.toString());
    }
    /**
     * 树节点
     * @author whv
     *
     */
    class TrieNode{
        public Map<Character,TrieNode> trieNodeMap;
        //是否是末节点
        public boolean isEnd;
        public TrieNode(){
            trieNodeMap = new ConcurrentHashMap<Character, TrieNode>();
        }
    }
    //根节点
    public TrieNode root;
    public TrieTree(){
        root = new TrieNode();
    }

    /**
     * 新增
     * @param word 全词
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
     * 批量新增字符类型
     * @param words 字符串
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
     * 批量新增
     * @param words 拼接后的字符
     * @param split 分割符
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
//            System.out.println("新增词语"+(i+1)+"/"+(wordArray.length)+":"+word);
        }
    }
    /**
     * 全词搜索
     * @param word 全词
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
     * 前缀搜索
     * @param prefix 前缀
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
     * 按前缀查询单词
     * @param prefix 前缀
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
     * 从某节点遍历剩余字符
     * @param node
     * @param prefix
     * @param rsltList
     * @return
     */
    public List getWordList(TrieNode node,String prefix,List<String> rsltList){
        //节点为null时，默认节点为根节点
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
