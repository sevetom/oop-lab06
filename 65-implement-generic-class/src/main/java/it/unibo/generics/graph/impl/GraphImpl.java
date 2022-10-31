package it.unibo.generics.graph.impl;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<V> implements Graph<V>{

    private final Map<V, Set<V>> edges = new LinkedHashMap<>();

    @Override
    public void addNode(final V node) {
        edges.putIfAbsent(node, new HashSet<>());
    }

    @Override
    public void addEdge(final V source, final V target) {
        if (isNodeIn(source, target)) {
            edges.get(source).add(target);
        }
    }

    private boolean isNodeIn(final V source, final V target) {
        if (!edges.containsKey(source) || !edges.containsKey(target)) {
            try {
                throw new IllegalAccessException("This graph doesn't contain " + source + " or " + target);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public Set<V> nodeSet() {
        return new HashSet<>(edges.keySet());
    }

    @Override
    public Set<V> linkedNodes(final V node) {
        return edges.get(node);
    }

    @Override
    public List<V> getPath(final V source, final V target) {
        
        return null;
    }
}