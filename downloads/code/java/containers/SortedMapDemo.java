package containers;

import java.util.*;

public class SortedMapDemo {
    public static void main(String[] args) {
        SortedMap<Integer, String> sortedMap = new TreeMap<>(new CountingMapData(10));
        System.out.println("First key: " + sortedMap.firstKey());
        System.out.println("Last key: " + sortedMap.lastKey());
        System.out.println(sortedMap.headMap(3));
        System.out.println(sortedMap.subMap(3, 6));
        System.out.println(sortedMap.tailMap(6));
    }
}/* Output:
First key: 0
Last key: 9
{0=A0, 1=B0, 2=C0}
{3=D0, 4=E0, 5=F0}
{6=G0, 7=H0, 8=I0, 9=J0}
*/
