package it.unibo.inner.impl;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.unibo.inner.api.*;
public class IterableWithPolicyImpl <T> implements IterableWithPolicy<T>{
    private final T[] elements;
    private Predicate<T> filter;
    public IterableWithPolicyImpl(final T[] array){
        this(array, new Predicate<T>() {
            public boolean test(T elem) {
                return true;
            }
        });
    }

    public IterableWithPolicyImpl(final T[] array, Predicate<T> predicate){
        elements = array.clone();
        setIterationPolicy(predicate);
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        class ArrayIterator implements Iterator<T>{
            private List<T> filteredArrayList;

            ArrayIterator(){
                filteredArrayList = new LinkedList<>();
                for(var elem : elements){
                    if (filter.test(elem)){
                        filteredArrayList.add(elem);
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return !filteredArrayList.isEmpty();
            }

            @Override
            public T next() {
                return filteredArrayList.removeFirst();
            }
            
        }
        return new ArrayIterator();
    }

    
   
}
