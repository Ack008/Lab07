package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    final static private int longerMonthDays = 31;
    final static private int shorterMonthDays = 30;
    final static private int februaryMonthDays = 28;
    public enum Month{
        JANUARY(1, longerMonthDays),
        FEBRUARY(2, februaryMonthDays),
        MARCH(3, longerMonthDays),
        APRIL(4, shorterMonthDays),
        MAY(5, longerMonthDays),
        JUNNE(6, shorterMonthDays),
        JULY(7, longerMonthDays),
        AUGUST(8, longerMonthDays),
        SEPTEMBER(9, shorterMonthDays),
        OCTOBER (10, longerMonthDays),
        NOVEMBER(11, shorterMonthDays),
        DECEMBER(12, longerMonthDays);

        private final int dayOfTheMonth;
        private final int position;
        private Month(final int position, final int dayOfTheMonth){
            this.dayOfTheMonth = dayOfTheMonth;
            this.position = position;
        }
        public int getPositionInTime(){
            return position;
        }
        public int getDayOfTheMonth(){
            return dayOfTheMonth;
        }
    }
    public static Month fromString(String monthString){
        monthString = monthString.toLowerCase();
        final TreeMap<String, Month> mappa = new TreeMap<>(){{
            put("january",Month.JANUARY);
            put("february",Month.FEBRUARY);
            put("march",Month.MARCH);
            put("april",Month.APRIL);
            put("may",Month.MAY);
            put("junne",Month.JUNNE);
            put("july",Month.JULY);
            put("august",Month.AUGUST);
            put("september",Month.SEPTEMBER);
            put("october",Month.OCTOBER);
            put("november",Month.NOVEMBER);
            put("december",Month.DECEMBER);
        }};
        final Iterator<String> keySeyIter = mappa.keySet().iterator();
        final List<String> possibleKeyList = new ArrayList<>();
        while(keySeyIter.hasNext()){
            String key = keySeyIter.next();
            if(key.startsWith(monthString)){
                possibleKeyList.add(key);
            }
        }
        if(possibleKeyList.size() != 1){
            throw new IllegalArgumentException();
        }
        return mappa.get(possibleKeyList.remove(0));
    }

    public final static class SortByDays implements Comparator<String>{
        @Override
        public int compare(String o1, String o2){
            int d1 = fromString(o1).getDayOfTheMonth();
            int d2 = fromString(o2).getDayOfTheMonth();
            return d1 - d2;
        }
    }

    public final static class SortByMonthOrder implements Comparator<String>{
        @Override 
        public int compare(String o1, String o2){
            int p1 = fromString(o1).getPositionInTime();
            int p2 = fromString(o2).getPositionInTime();
            return p1 - p2;
        }
    }


    @Override
    public Comparator<String> sortByDays() {
        return new SortByDays();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }
}
