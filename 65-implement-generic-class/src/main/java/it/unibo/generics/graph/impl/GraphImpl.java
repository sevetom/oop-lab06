package it.unibo.generics.graph.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
    // uses BFS
    public List<V> getPath(final V source, final V target) {
        final List<V> path = new LinkedList<>();
        final Queue<V> queue = new LinkedList<>();
        final Map<V, Boolean> visited = new LinkedHashMap<V, Boolean>();
        final Map<V, V> previous = new LinkedHashMap<V, V>();
        V current = source;
        queue.add(current);
        visited.put(current, true);
        while(!queue.isEmpty()){
            current = queue.remove();
            if (current.equals(target)){
                break;
            }else{
                for(final V node : edges.get(current)){
                    if(!visited.containsKey(node.hashCode())){
                        queue.add(node);
                        visited.put(node, true);
                        previous.put(node, current);
                    }
                }
            }
        }
        if (!current.equals(target)){
            System.out.println("Node " + target +" is unreachable");
        }
        for(V node = target; node != null; node = previous.get(node)) {
            path.add(node);
        }
        Collections.reverse(path);
        return path;
    }
}