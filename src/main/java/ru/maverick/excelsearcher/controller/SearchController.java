package ru.maverick.excelsearcher.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.maverick.excelsearcher.service.SearchService;

@Controller
@RequiredArgsConstructor
@Tag(name = "Searching", description = "Поиск по локальным XLSX файлам")
public class SearchController {

    private final SearchService searchService;

    @PostMapping(value = "/findMin")
    @Operation(summary = "Поиск N-ного минимального числа в XLSX файле")
    public ResponseEntity<Integer> findNthMin(
            @RequestParam(
                    value = "filePath",
                    defaultValue = "src/main/resources/xlsx/file.xlsx",
                    required = false
            ) String filePath,
            @RequestParam("n") int n) {
        return ResponseEntity.ok(searchService.findNthMinFromFile(filePath, n));
    }
}
