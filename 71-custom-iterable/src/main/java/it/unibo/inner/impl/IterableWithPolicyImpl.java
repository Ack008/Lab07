package it.unibo.inner.impl;
import java.util.Iterator;

import it.unibo.inner.api.*;
public class IterableWithPolicyImpl <T> implements IterableWithPolicy<T>{
    private final T[] elements;
    IterableWithPolicyImpl(final T[] array){
        elements = array.clone();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Iterator<T> iterator() {
        class ArrayIterator implements Iterator<T>{
            private int arrayPosition = 0;
            @Override
            public boolean hasNext() {
                return arrayPosition < elements.length;
            }

            @Override
            public T next() {
                arrayPosition++;
                return elements[arrayPosition - 1];
            }
            
        }
        return new ArrayIterator();
    }

    
   
}
