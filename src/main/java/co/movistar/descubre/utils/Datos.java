package co.movistar.descubre.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class Datos {

    String archivoExcel = "DatosUsuario.xlsx";

    public List<Login> obtenerLogin(String hojaExcel) {
        List<Login> listlogin = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(archivoExcel));
            XSSFWorkbook libro = new XSSFWorkbook(file);
            XSSFSheet hoja = libro.getSheet(hojaExcel);
            int rows = hoja.getPhysicalNumberOfRows();
            for (int i = 1; i < rows; i++) {
                Row row = hoja.getRow(i);
                DataFormatter formatter = new DataFormatter();
                listlogin.add(new Login(row.getCell(0).getStringCellValue(), formatter.formatCellValue(row.getCell(1)), row.getCell(2).toString(), row.getCell(3).toString(), row.getCell(4).toString(), row.getCell(5).toString(), row.getCell(6).toString()));

            }
            libro.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listlogin;
    }


}
