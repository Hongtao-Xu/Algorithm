package com.cn.advance.Stage3.Trie;

/*
 * TrieSet
 */
public class TrieSet<E extends Comparable<E>> implements Set<E> {
    private Trie trie;

    public TrieSet() {
        this.trie = new Trie();
    }

    @Override
    public void add(E e) {
        trie.add((String) e);
    }

    @Override
    public boolean contains(E e) {
        return trie.contains((String) e);
    }

    @Override
    public void remove(E e) {
        return;
    }

    @Override
    public int getSize() {
        return trie.size();
    }

    @Override
    public boolean isEmpty() {
        return trie.isEmpty();
    }
}
