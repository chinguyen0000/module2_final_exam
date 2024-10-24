import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookManagement {
    public final String filePath = "src/data/memory.csv";


    public void removeContact(int count) {
        List<PhoneBook> phoneBookList = this.getPhoneBookList();
        phoneBookList.remove(count);
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write("Số điện thoại,Nhóm,Họ tên,Giới tính,Địa chỉ,Ngày sinh,Email\n");
            for (PhoneBook phoneBook : phoneBookList) {
                writer.write(phoneBook.toFile());
            }
            writer.close();
            fileWriter.close();
            System.out.println("Xóa thành công");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateContact(int index, String phoneGroup, String fullname, String gender, String address, String dob, String email) {
        List<PhoneBook> phoneBookList = this.getPhoneBookList();
        PhoneBook updatePB = phoneBookList.get(index);
        updatePB.setPhoneGroup(phoneGroup);
        updatePB.setFullName(fullname);
        updatePB.setGender(gender);
        updatePB.setAddress(address);
        updatePB.setDateOfBirth(LocalDate.parse(dob));
        updatePB.setEmail(email);
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write("Số điện thoại,Nhóm,Họ tên,Giới tính,Địa chỉ,Ngày sinh,Email\n");
            for (PhoneBook pb : phoneBookList) {
                writer.write(pb.toFile());
            }
            writer.close();
            fileWriter.close();
            System.out.println("Sửa thành công");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addContact(PhoneBook phoneBook) {
        List<PhoneBook> phoneBookList = this.getPhoneBookList();
        phoneBookList.add(phoneBook);
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write("Số điện thoại,Nhóm,Họ tên,Giới tính,Địa chỉ,Ngày sinh,Email\n");
            for (PhoneBook pb : phoneBookList) {
                writer.write(pb.toFile());
            }
            writer.close();
            fileWriter.close();
            System.out.println("Thêm thành công");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayContacts() {
        List<PhoneBook> phoneBookList = this.getPhoneBookList();
        for (PhoneBook phoneBook : phoneBookList) {
            System.out.println(phoneBook.toString());
        }
    }

    public List<PhoneBook> getPhoneBookList() {
        List<PhoneBook> phoneBookList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(this.filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            String temp;
            String[] array;
            reader.readLine();
            while ((temp = reader.readLine()) != null) {
                array = temp.split(",");
                phoneBookList.add(new PhoneBook(array[0], array[1], array[2], array[3], array[4], LocalDate.parse(array[5]), array[6]));
            }
            reader.close();
            fileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return phoneBookList;
    }

    public static void main(String[] args) {
        PhoneBookManagement pb = new PhoneBookManagement();
        System.out.println(pb.getPhoneBookList().size());
    }
}
