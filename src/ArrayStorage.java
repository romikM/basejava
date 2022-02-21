/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    int size;

    void clear() {
        // циклично обнулили каждый эл-т, размер в ноль
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        // получили реюме r, добавили в конец хранилища, нарастили размер
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        // пробежали по хранилищу, если нашли эл-т с заданным uuid - вернули
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        // иначе null
        return null;
    }

    void delete(String uuid) {
        // пробежали по хранилищу, если нашли эл-т с заданным uuid - всем последующим уменьшаем индекс на 1 (смещаем).
        // Итог: искомое в хранилище отсутствует, пустышек нет.
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                for (int j = i; j < size - 1; j++) {
                    storage[j] = storage[j + 1];
                }
                storage[size - 1] = null; // последний эл-т в null.
                size--; // удалили эл-т - уменьшили размер.
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        // создали новый массив размера size, поэлементно перенесли в него storage.
        Resume[] allResume = new Resume[size()];
        for (int i = 0; i < size(); i++) {
            allResume[i] = storage[i];
        }
        return allResume;
    }

    int size() {
        return size;
    }
}
