package com.javarush.test.level21.lesson05.task01;

import java.util.HashSet;
import java.util.Set;

/* Equals and HashCode
В классе Solution исправить пару методов equals/hashCode в соответствии с правилами реализации этих методов.
Метод main не участвует в тестировании.
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object n) {
        if (n == this) {
            return true;
        }
        if (n == null || n.getClass() != this.getClass()) {
            return false;
        }

        Solution x = (Solution) n;

        if (first != null ? ! first.equals(x.first) : x.first != null) {
            return false;
        }

        if (last != null ? ! last.equals(x.last) : x.last != null) {
            return false;
        }

        return true;
    }

    public int hashCode() {

        int hashResult = (first != null ? first.hashCode() : 0);
        return 31 * hashResult + (last != null ? last.hashCode() : 0);

    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}
