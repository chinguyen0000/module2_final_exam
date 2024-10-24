import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBookDisplay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PhoneBookManagement pbm = new PhoneBookManagement();
        List<PhoneBook> pb = pbm.getPhoneBookList();
        String phoneNumber, phoneGroup, fullName, gender, address, dob, email;
        while (true) {
            System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ ----\n" +
                    "Chọn chức năng theo số (để tiếp tục):\n" +
                    "1. Xem danh sách\n" +
                    "2. Thêm mới\n" +
                    "3. Cập nhật\n" +
                    "4. Xóa\n" +
                    "5. Tìm kiếm\n" +
                    "6. Đọc từ file\n" +
                    "7. Ghi vào file\n" +
                    "8. Thoát\n" +
                    "Chọn chức năng:");
            int choice = Integer.parseInt(sc.nextLine());


            switch (choice) {
                case 1:
                    pbm.displayContacts();
                    break;
                case 2:
                    System.out.println("Nhập số điện thoại:");
                    phoneNumber = sc.nextLine();
                    System.out.println("Nhập nhóm của danh bạ: ");
                    phoneGroup = sc.nextLine();
                    System.out.println("Nhập họ tên:");
                    fullName = sc.nextLine();
                    System.out.println("Nhập giới tính:");
                    gender = sc.nextLine();
                    System.out.println("Nhập địa chỉ:");
                    address = sc.nextLine();
                    System.out.println("Nhập ngày sinh");
                    dob = sc.nextLine();
                    System.out.println("Nhập email:");
                    email = sc.nextLine();
                    if (phoneNumber == null || phoneNumber.equals("") || !isPhoneValid(phoneNumber)) {
                        System.out.println("Điền thiếu số điện thoại hoặc không hợp lệ");
                        break;
                    } else if (phoneGroup == null || phoneGroup.equals("")) {
                        System.out.println("Điền thiếu nhóm danh bạ");
                        break;
                    } else if (fullName == null || fullName.equals("")) {
                        System.out.println("Điền thiếu họ tên");
                        break;
                    } else if (gender == null || gender.equals("")) {
                        System.out.println("Điền thiếu giới tính");
                        break;
                    } else if (address == null || address.equals("")) {
                        System.out.println("Điền thiếu địa chỉ");
                        break;
                    } else if (dob == null || dob.equals("") || !isDateValid(dob)) {
                        System.out.println("Điền thiếu ngày sinh hoặc không đúng định dạng yyyy-mm-dd");
                        break;
                    } else if (email == null || email.equals("") || !isEmailValid(email)) {
                        System.out.println("Điền thiếu email hoặc không hợp lệ ");
                        break;
                    }
                    pbm.addContact(new PhoneBook(phoneNumber, phoneGroup, fullName, gender, address, LocalDate.parse(dob), email));
                    break;
                case 3:
                    String updatePhone = null;
                    pb = pbm.getPhoneBookList();
                    boolean flag = true;
                    do {
                        System.out.println("Nhập số điện thoại cần sửa:");
                        updatePhone = sc.nextLine();
                        if (updatePhone == null || updatePhone.isEmpty()) {
                            flag = false;
                            break;
                        }
                    } while (isPhoneNumberExisted(updatePhone, pb) == 0);

                    if (!flag) {
                        break;
                    } else {
                        System.out.println("Nhập nhóm của danh bạ: ");
                        phoneGroup = sc.nextLine();
                        System.out.println("Nhập họ tên:");
                        fullName = sc.nextLine();
                        System.out.println("Nhập giới tính:");
                        gender = sc.nextLine();
                        System.out.println("Nhập địa chỉ:");
                        address = sc.nextLine();
                        System.out.println("Nhập ngày sinh");
                        dob = sc.nextLine();
                        System.out.println("Nhập email:");
                        email = sc.nextLine();
                        if (phoneGroup == null || phoneGroup.equals("")) {
                            System.out.println("Điền thiếu nhóm danh bạ");
                            break;
                        } else if (fullName == null || fullName.equals("")) {
                            System.out.println("Điền thiếu họ tên");
                            break;
                        } else if (gender == null || gender.equals("")) {
                            System.out.println("Điền thiếu giới tính");
                            break;
                        } else if (address == null || address.equals("")) {
                            System.out.println("Điền thiếu địa chỉ");
                            break;
                        } else if (dob == null || dob.equals("") || !isDateValid(dob)) {
                            System.out.println("Điền thiếu ngày sinh hoặc không đúng định dạng yyyy-mm-dd");
                            break;
                        } else if (email == null || email.equals("") || !isEmailValid(email)) {
                            System.out.println("Điền thiếu email hoặc không hợp lệ ");
                            break;
                        }
                        pbm.updateContact(isPhoneNumberExisted(updatePhone, pb),phoneGroup,fullName,gender,address,dob,email);
                    }
                    break;
                case 4:
                    String removePhone = null;
                    pb = pbm.getPhoneBookList();
                    boolean check = true;
                    do {
                        System.out.println("Nhập số điện thoại cần xóa:");
                        removePhone = sc.nextLine();
                        if (removePhone == null || removePhone.isEmpty()) {
                            check = false;
                            break;
                        }
                    } while (isPhoneNumberExisted(removePhone, pb) == 0);

                    if (!check) {
                        break;
                    } else {
                        String confirm = "null";
                        int index = isPhoneNumberExisted(removePhone, pb);
                        System.out.println("Bạn có chắc chắn muốn xóa ? Nhập \"Y\" để xác nhận");
                        confirm = sc.nextLine();
                        if (confirm.equals("Y")) {
                            pbm.removeContact(index);
                        } else {
                            break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("Nhập thông tin");
                    String search = sc.nextLine();
                    pb = pbm.getPhoneBookList();
                    for (PhoneBook phoneBook : pb) {
                        if (phoneBook.getPhoneNumber().contains(search) || phoneBook.getFullName().contains(search)) {
                            System.out.println(phoneBook.toString());
                        }
                    }
                    break;
                case 6:
                    System.out.println("Lựa chọn Đọc từ file này sẽ xóa toàn bộ danh bạ đang có trong bộ nhớ. Nhấn \"Y\" đề đồng ý");
                    String confirm = sc.nextLine();
                    if (confirm.equals("Y")) {
                        pb = pbm.getPhoneBookList();
                        try {

                            FileWriter fileWriter = new FileWriter("src/data/contacts.csv");
                            BufferedWriter writer = new BufferedWriter(fileWriter);
                            writer.write("Số điện thoại,Nhóm,Họ tên,Giới tính,Địa chỉ,Ngày sinh,Email\n");
                            for (PhoneBook phoneBook : pb) {
                                writer.write(phoneBook.toFile());
                            }
                            writer.close();
                            fileWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                    break;
                case 7:
                    break;
                case 8:
                default:
                    System.exit(0);
            }


        }
    }

    public static boolean isEmailValid(String email) {
        String regexEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isPhoneValid(String phone) {
        String regexPhone = "^0[0-9]{9}$";
        Pattern pattern = Pattern.compile(regexPhone);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean isDateValid(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            return true;
        } catch (Exception e) {
            System.out.println("Date of birth is invalid or not following format yyyy-mm-dd");
            return false;
        }
    }

    public static int isPhoneNumberExisted(String phoneNumber, List<PhoneBook> pb) {
        int count = 0;
        for (PhoneBook phoneBook : pb) {
            if (phoneBook.getPhoneNumber().equals(phoneNumber)) {
                return count;
            }
            count++;
        }
        System.out.println("Không tìm được danh bạ với số điện thoại trên");
        return 0;
    }
}
