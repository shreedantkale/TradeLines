package com.trading.view;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GoogleSheets {

    private static final String APPLICATION_NAME = "Nifty50 Data Fetcher";
    private static final String SPREADSHEET_ID = "1pmb9FnuNSOX-MtMlYfl9TH5Z7aBjvbCEJEvhO5NGKrA"; // Replace with your spreadsheet ID
    private static final String RANGE = "Sheet1!B2:D2"; // Specify the range to update

    public static Sheets getSheetsService() throws IOException {
        // Load the JSON key file
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream("C:\\\\\\\\Users\\\\\\\\hp\\\\\\\\Downloads\\\\\\\\cogent-range-447716-s5-fd9a0b2a90ca.json"))
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));

        // Create and return Sheets API client
        return new Sheets.Builder(
                credential.getTransport(),
                credential.getJsonFactory(),
                credential
        ).setApplicationName(APPLICATION_NAME).build();
    }

    public static void updateSheet(String high, String low, String close) {
        try {
            // Authenticate and initialize Sheets API service
            Sheets sheetsService = getSheetsService();

            // Prepare data to update
            ValueRange body = new ValueRange().setValues(
                Collections.singletonList(Arrays.asList(high, low, close))
            );

            // Update Google Sheets
            sheetsService.spreadsheets().values()
                    .update(SPREADSHEET_ID, RANGE, body)
                    .setValueInputOption("RAW")
                    .execute();

            System.out.println("Data successfully inserted into Google Sheets!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void fetchSupportValues(String range) {
        try {
            // Authenticate and initialize Sheets API service
            Sheets sheetsService = getSheetsService();

            // Fetch data from the specified range
            ValueRange response = sheetsService.spreadsheets().values()
                    .get(SPREADSHEET_ID, range)
                    .execute();

            // Retrieve the fetched values
            List<List<Object>> values = response.getValues();
            if (values != null && !values.isEmpty()) {
                String[] labels = {"S1", "S2", "S3", "S4"}; // Labels for the values
                int index = 0;

                for (List<Object> row : values) {
                    for (Object cellValue : row) {
                        System.out.println(labels[index] + ": " + cellValue);
                        index++;
                    }
                }
            } else {
                System.out.println("No data found in the specified range.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
        public static void fetchResistancetValues(String range) {
            try {
                // Authenticate and initialize Sheets API service
                Sheets sheetsService = getSheetsService();

                // Fetch data from the specified range
                ValueRange response = sheetsService.spreadsheets().values()
                        .get(SPREADSHEET_ID, range)
                        .execute();

                // Retrieve the fetched values
                List<List<Object>> values = response.getValues();
                if (values != null && !values.isEmpty()) {
                    String[] labels = {"R1", "R2", "R3", "R4"}; // Labels for the values
                    int index = 0;

                    for (List<Object> row : values) {
                        for (Object cellValue : row) {
                            System.out.println(labels[index] + ": " + cellValue);
                            index++;
                        }
                    }
                } else {
                    System.out.println("No data found in the specified range.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


}
