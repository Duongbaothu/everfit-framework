package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;

import commons.GlobalConstants;

public class ExcelConfig {
    private Sheet currentSheet;
    private Workbook workbook;
    private FormulaEvaluator evaluator;
    private String testDataExcelPath = GlobalConstants.getGlobalConstants().getDataTestPath() + "Client.xlsx" ;
    private Map<String, Integer> columns;

    public static ExcelConfig getExcelData() {
        return new ExcelConfig();
    }

    public ExcelConfig() {
        try (FileInputStream fis = new FileInputStream(testDataExcelPath)) {
            workbook = WorkbookFactory.create(fis);
            evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToSheet(String name) {
        columns = new HashMap<>();
        currentSheet = workbook.getSheet(name);
        currentSheet.getRow(0).forEach(cell -> {
            columns.put(cell.getStringCellValue(), cell.getColumnIndex());
        });
    }

    public String getCellData(String columnName, int row) {
        var dataRow = currentSheet.getRow(row);
        Cell cell = dataRow.getCell(columns.get(columnName));
        return getCellDataAsString(cell);
    }

    private String getCellDataAsString(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
            case FORMULA -> {
                evaluator.evaluateFormulaCell(cell);
                yield getCellDataAsString(evaluator.evaluateInCell(cell));
            }
            default -> "";
        };
    }

    public void refreshAllFormulas() {
        if (workbook != null && evaluator != null) {
            evaluator.evaluateAll();  // Chỉ cập nhật giá trị công thức trong bộ nhớ
            System.out.println("Formulas refreshed in memory.");
        } else {
            System.err.println("Workbook or evaluator is not initialized.");
        }
    }

    public void close() {
        try {
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
