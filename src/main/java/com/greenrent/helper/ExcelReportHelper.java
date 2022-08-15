package com.greenrent.helper;

import com.greenrent.domain.Car;
import com.greenrent.domain.Reservation;
import com.greenrent.domain.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelReportHelper {

    static String SHEET_USER="Users";
    static String[] USER_HEADERS={"id", "FirstName", "LastName", "PhoneNumber", "Email", "Address", "ZipCode", "Roles"};


    static String SHEET_CAR="Cars";
    static String [] CAR_HEADERS= {"id","Model","Doors","Seats","Luggage","Transmission","AirConditioning","Age","PricePerHour","FuelType"};


    static String SHEET_RESERVATION="Reservations";
    static String [] RESERVATION_HEADERS=
            {"id","CarId","CarModel","CustomerId","CustomerFullName","CustomerPhoneNumber","PickupTime","DropOffTime","PickUpLocation","DropOffLocation","Status"};



    public static ByteArrayInputStream getusersExcelReport(List<User> users) throws IOException {
        Workbook workBook = new XSSFWorkbook();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Sheet sheet = workBook.createSheet(SHEET_USER);

        Row headerRow = sheet.createRow(0);

        for (int column=0; column<USER_HEADERS.length; column++){
            Cell cell = headerRow.createCell(column);
            cell.setCellValue(USER_HEADERS[column]);
        }
        int rowId=1;

        for (User user: users){
            Row row = sheet.createRow(rowId++);

            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getFirstName());
            row.createCell(2).setCellValue(user.getLastName());
            row.createCell(3).setCellValue(user.getPhoneNumber());
            row.createCell(4).setCellValue(user.getEmail());
            row.createCell(5).setCellValue(user.getAddress());
            row.createCell(6).setCellValue(user.getZipCode());
            row.createCell(7).setCellValue(user.getRoles().toString());
        }
        workBook.write(out);

        workBook.close();

        return new ByteArrayInputStream(out.toByteArray());
    }


    public static ByteArrayInputStream getCarsExcelReport(List<Car> cars) throws IOException {
        Workbook workBook=new XSSFWorkbook();

        ByteArrayOutputStream out=new ByteArrayOutputStream();

        Sheet sheet=workBook.createSheet(SHEET_CAR);

        Row headerRow= sheet.createRow(0);

        for(int column=0;column<CAR_HEADERS.length;column++) {
            Cell cell=headerRow.createCell(column);
            cell.setCellValue(CAR_HEADERS[column]);
        }

        int rowId=1;

        for(Car car: cars) {
            Row row= sheet.createRow(rowId++);

            row.createCell(0).setCellValue(car.getId());
            row.createCell(1).setCellValue(car.getModel());
            row.createCell(2).setCellValue(car.getDoors());
            row.createCell(3).setCellValue(car.getSeats());
            row.createCell(4).setCellValue(car.getLuggage());
            row.createCell(5).setCellValue(car.getTransmission());
            row.createCell(6).setCellValue(car.getAirConditioning());
            row.createCell(7).setCellValue(car.getAge());
            row.createCell(8).setCellValue(car.getPricePerHour());
            row.createCell(9).setCellValue(car.getFuelType());

        }

        workBook.write(out);

        workBook.close();

        return new ByteArrayInputStream(out.toByteArray());

    }


    public static ByteArrayInputStream getReservationExcelReport(List<Reservation> reservations) throws IOException {
        Workbook workBook=new XSSFWorkbook();

        ByteArrayOutputStream out=new ByteArrayOutputStream();

        Sheet sheet=workBook.createSheet(SHEET_RESERVATION);

        Row headerRow= sheet.createRow(0);

        for(int column=0;column<RESERVATION_HEADERS.length;column++) {
            Cell cell=headerRow.createCell(column);
            cell.setCellValue(RESERVATION_HEADERS[column]);
        }

        int rowId=1;



        for(Reservation r: reservations) {
            Row row= sheet.createRow(rowId++);

            row.createCell(0).setCellValue(r.getId());
            row.createCell(1).setCellValue(r.getCarId().getId());
            row.createCell(2).setCellValue(r.getCarId().getModel());
            row.createCell(3).setCellValue(r.getUserId().getId());
            row.createCell(4).setCellValue(r.getUserId().getFirstName()+" "+r.getUserId().getLastName());
            row.createCell(5).setCellValue(r.getUserId().getPhoneNumber());
            row.createCell(6).setCellValue(r.getPickUpTime().toString());
            row.createCell(7).setCellValue(r.getDropOffTime().toString());
            row.createCell(8).setCellValue(r.getPickUpLocation().toString());
            row.createCell(9).setCellValue(r.getDropOffLocation().toString());
            row.createCell(10).setCellValue(r.getStatus().toString());

        }

        workBook.write(out);

        workBook.close();

        return new ByteArrayInputStream(out.toByteArray());

    }

}
