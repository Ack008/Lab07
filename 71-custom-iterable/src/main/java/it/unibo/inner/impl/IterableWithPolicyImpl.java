package it.unibo.inner.impl;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.unibo.inner.api.*;
public class IterableWithPolicyImpl <T> implements IterableWithPolicy<T>{

    private final List<T> elements;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(final T[] array){
        this(
            array,
            new Predicate<T>() {
                public boolean test(T elem) {
                    return true;
                }
            }
        );
    }

    public IterableWithPolicyImpl(final T[] array, final Predicate<T> predicate){
        elements = List.of(array);
        setIterationPolicy(predicate);
    }

    @Override
    public void setIterationPolicy(final Predicate<T> filter) {
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Iterator<T> unfilteredIterator = elements.iterator();
            T nextElementToReturn;
            @Override
            public boolean hasNext() {
                while(unfilteredIterator.hasNext()){
                    nextElementToReturn = unfilteredIterator.next();
                    if(filter.test(nextElementToReturn)){
                        return true;
                    }
                }
                return false;
            }

            @Override
            public T next() {
                return nextElementToReturn;
            }
            
        };
    }

    
   
}
