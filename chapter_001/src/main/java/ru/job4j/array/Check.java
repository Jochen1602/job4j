package ru.job4j.array;

/**class Check Решение задачи 6.3. Массив заполнен true или false.
 *@author antontokarev
 *@since 14.10.2018
 */
public class Check {
    /**Функция определяет все ли элементы булева массива одинаковы.
     * @param data - массив булеанов.
     * @return true если все одинаковые, false в противном случае.
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (boolean aData : data)
            if (data[0] != aData) {
                result = false;
                break;
            }
        return result;
    }
}