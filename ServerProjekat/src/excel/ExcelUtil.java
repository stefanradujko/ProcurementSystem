/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author Stefan
 */
public class ExcelUtil {

    private FileInputStream fis;
    private FileOutputStream fileOut;
    private Workbook wb;
    private Sheet sh;
    private Cell cell;
    private Row row;
    private CellStyle cellstyle;
    private Color mycolor;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();
    private static ExcelUtil instanca;
    private int brNar;

    public static ExcelUtil getInstanca() throws Exception{
        if(instanca == null){
            instanca = new ExcelUtil();
        }
        return instanca;
    }

    public void setExcelFile() throws Exception {
            File f = new File("Narudžbenice.xlsx");

            if (!f.exists()) {
                f.createNewFile();
                System.out.println("Uspešno kreiran excel fajl!");
            }
            FileInputStream fis = new FileInputStream("Narudžbenice.xlsx");
            wb = WorkbookFactory.create(fis);
            //int brojSheetova = wb.getNumberOfSheets() + 1;
            String SheetName = "Narudžbenica broj " + brNar + "";
            sh = wb.getSheet(SheetName);
            //sh = wb.getSheetAt(0); //0 - index of 1st sheet
            if (sh == null) {
                sh = wb.createSheet(SheetName);
            }
            this.excelFilePath = "Narudžbenice.xlsx";

            //adding all the column header names to the map 'columns'
          /*  sh.getRow(0).forEach(cell ->{
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });*/
    }

    public void setCellData(String text, int rownum, int colnum) throws Exception {
            row  = sh.getRow(rownum);
            if(row ==null)
            {
                row = sh.createRow(rownum);
            }
            cell = row.getCell(colnum);

            if (cell == null) {
                cell = row.createCell(colnum);
            }
            boolean provera = true;
            for(int i = 0; i < text.length(); i++){
                if(!Character.isDigit(text.charAt(i))){
                    provera = false;
                }
            }
            if(provera){
                int broj = Integer.parseInt(text);
                cell.setCellValue(broj);
            } else{
                if(colnum == 2 && rownum != 0 && rownum != 3 && rownum != 4){
                    double broj = Double.parseDouble(text);
                    cell.setCellValue(broj);
                } else{
                    cell.setCellValue(text); 
                }
            }
            fileOut = new FileOutputStream(excelFilePath);
            wb.write(fileOut);
            setColumnWidth();
            fileOut.flush();
            fileOut.close();
    }
    
    public void setColumnWidth(){
        for(int i = 0; i < 6; i++){
            sh.autoSizeColumn(i);
        }
    }
    
    public void postaviBrojNar(int broj){
        brNar = broj;
    }

    void otvoriFajl() throws Exception {
        Desktop desktop = Desktop.getDesktop();
        File f = new File("Narudžbenice.xlsx");
        desktop.open(f);
    }
}