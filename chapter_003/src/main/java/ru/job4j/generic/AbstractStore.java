package ru.job4j.generic;

import ru.job4j.arrays.SimpleArray;

/**class AbstractStore Решение задачи 5.2.2. Реализовать Store<T extends Base>[#84115]
 *
 *@author antontokarev
 *@since 12.11.2018
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> values;

    /**
     * Конструктор по размеру.
     * @param size размер SimpleArray<T>, что нужно создать.
     */
    public AbstractStore(int size) {
        this.values = new SimpleArray<>(size);
    }

    /**
     * Метод добавления элементов в хранилище.
     * @param model что подставить на это место.
     */
    @Override
    public void add(Base model) {
        this.values.add((T) model);
    }

    /**
     * Метод замены элементов хранилища.
     * @param id ключ поиска.
     * @param model что подставить на это место.
     * @return true, если замена прошла успешно, false иначе.
     */
    @Override
    public boolean replace(String id, Base model) {
        boolean result = false;
        if (findIndexById(id) != -1) {
            this.values.set(findIndexById(id), (T) model);
            result = true;
        }
        return result;
    }

    /**
     * Метод удаления элемента хранилища по id.
     * @param id ключ поиска.
     * @return true, если удаление прошло успешно, false иначе.
     */
    public boolean delete(String id) {
        boolean result = false;
        if (findIndexById(id) != -1) {
            this.values.delete(findIndexById(id));

            result = true;
        }
        return result;
    }

    /**
     * Метод поиска по id.
     * @param id ключ поиска.
     * @return найденный элемент хранилища.
     */
    public T findById(String id) {
        return this.values.get(findIndexById(id));
    }

    /**
     * Очень нужный для реализации других методов метод поиска по id.
     * ВОПРОС. А можно это всё реализовать через Stream API?
     * return values.get(IntStream.range(0, values.getSize()).filter(i -> this.values.get(i).getId().equals(id)).findFirst().orElse(0)); - примерно так.
     * Или проблема в том, что перебирать надо по int, а вернуть надо null если элемента не нашлось?
     * @param id ключ поиска.
     * @return индекс найденного элемента.
     */
    private int findIndexById(String id) {
        int result = -1;
        for (int i = 0; i < this.values.getSize(); i++) {
            if (this.values.get(i) == null) {
                break;
            } else {
                if (this.values.get(i).getId().equals(id)) {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }

    public T get(int index) {
        return this.values.get(index);
    }
}