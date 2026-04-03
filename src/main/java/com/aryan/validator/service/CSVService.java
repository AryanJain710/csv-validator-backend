package com.aryan.validator.service;

import com.aryan.validator.model.ValidationError;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class CSVService {

    public List<ValidationError> validateCSV(InputStream inputStream) {
        List<ValidationError> errors = new ArrayList<>();
        Set<String> tags = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            int rowNumber = 1;

            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");

                String tag = columns.length > 0 ? columns[0].trim() : "";

                if (tag.isEmpty()) {
                    errors.add(new ValidationError(rowNumber, "Missing Tag"));
                } else if (tags.contains(tag)) {
                    errors.add(new ValidationError(rowNumber, "Duplicate Tag: " + tag));
                } else {
                    tags.add(tag);
                }

                if (!tag.isEmpty() && !tag.startsWith("P-")) {
                    String suggestion = "P-" + tag.replaceAll("^[A-Za-z]-?", "");
                    errors.add(new ValidationError(
                            rowNumber,
                            "Invalid Tag: " + tag + " → Suggested: " + suggestion
                    ));
                }

                rowNumber++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return errors;
    }
}