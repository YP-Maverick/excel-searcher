package ru.maverick.excelsearcher.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.maverick.excelsearcher.exceptions.FileProcessingException;
import ru.maverick.excelsearcher.exceptions.InvalidInputException;
import ru.maverick.excelsearcher.utils.QuickSorter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final FileNumberReader fileNumberReader;

    public int findNthMinFromFile(String filePath, int n) {
        try {
            List<Integer> numbers = fileNumberReader.readNumbers(filePath, n);

            validateInput(numbers, n);

            List<Integer> sortedNumbers = QuickSorter.quickSort(numbers);
            return sortedNumbers.get(n - 1);  // Возвращаем n-ный минимальный элемент

        } catch (IOException e) {
            throw new FileProcessingException("Ошибка при чтении файла: " + filePath + "с ошибкой" + e.getMessage());
        }
    }

    private void validateInput(List<Integer> numbers, int n) {
        if (numbers == null || numbers.isEmpty()) {
            throw new InvalidInputException("Файл пуст или содержит некорректные данные");
        }
        if (n < 1 || n > numbers.size()) {
            throw new InvalidInputException(
                    String.format("Некорректное значение N: %d (допустимый диапазон 1-%d)", n, numbers.size())
            );
        }
    }
}