package ru.job4j.array;

/**class MassivesAddition Решение задачи о слиянии 2 упорядоченных масивов в третий упорядоченный массив.
 *@author antontokarev
 *@since 16.10.2018
 */
public class MassivesAddition {
    int[] result(int[] first, int[] second) {
        int[] res = new int[first.length + second.length];
        int i = 0, j = 0, k = 0;
        while (i < first.length && j < second.length) {
            if (first[i] < second[j]) {
                res[k] = first[i];
                i++;
            } else {
                res[i + j] = second[j];
                j++;
            }
        k++;
        }
        while (i < first.length) {
            res[k++] = first[i++];
        }
        while (j < second.length) {
            res[k++] = second[j++];
        }
        return res;
    }
}