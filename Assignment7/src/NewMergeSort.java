/*
 * Name: Asher Bolleddu
 * Date: 11/18/2022
 * Class: CS2336.504
 *
 * The program takes in a class object of arrays and sorts everything inside via the Mergesort algorithm, then prints out the sorted array.
 * */
import java.util.Date;

public class NewMergeSort {
    /** The method for sorting the numbers */
    public static <E extends Comparable<E>> void mergeSort(E[] list) {
        if (list.length > 1) {
            // Merge sort the first half
            E[] firstHalf = (E[])new Comparable[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf);

            // Merge sort the second half
            int secondHalfLength = list.length - list.length / 2;
            E[] secondHalf = (E[])new Comparable[secondHalfLength];
            System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);

            // Merge firstHalf with secondHalf into list
            merge(firstHalf, secondHalf, list);
        }
    }

    /** Merge two sorted lists */
    public static <E extends Comparable<E>> void merge(E[] list1, E[] list2, E[] temp) {
        int current1 = 0; // Current index in list1
        int current2 = 0; // Current index in list2
        int current3 = 0; // Current index in temp

        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1].compareTo(list2[current2]) < 0){
                temp[current3++] = list1[current1++];
            }
            else {
                temp[current3++] = list2[current2++];
            }
        }

        while (current1 < list1.length)
            temp[current3++] = list1[current1++];

        while (current2 < list2.length)
            temp[current3++] = list2[current2++];
    }

    /** A test method */
    public static void main(String[] args) {
        Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        Date[] datelist = {new Date(20025689547l), new Date(34446954957l), new
                Date(22990000000l), new Date(58585689412l), new Date(67956898000l), new
                Date(105687456365l), new Date(34987400512l), new Date(125146636472l), new
                Date(125412566632l)};

        mergeSort(list);
        mergeSort(datelist);

        for (int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");

        System.out.println('\n');

        for (int i = 0; i < datelist.length; i++){
            System.out.println("Date[" + i + "]= " + datelist[i].toString());
        }
    }
}