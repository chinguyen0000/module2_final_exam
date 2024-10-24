import java.time.LocalDate;

public class PhoneBook {
    private String phoneNumber;
    private String phoneGroup;
    private String fullName;
    private String gender;
    private String address;
    private LocalDate dateOfBirth;
    private String email;

    public PhoneBook(String phoneNumber, String phoneGroup, String fullName, String gender, String address) {
        this.phoneNumber = phoneNumber;
        this.phoneGroup = phoneGroup;
        this.fullName = fullName;
        this.gender = gender;
        this.address = address;
        this.dateOfBirth = LocalDate.parse("1980-01-01");
    }

    public PhoneBook(String phoneNumber, String phoneGroup, String fullName, String gender, String address, LocalDate dateOfBirth, String email) {
        this.phoneNumber = phoneNumber;
        this.phoneGroup = phoneGroup;
        this.fullName = fullName;
        this.gender = gender;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneGroup() {
        return phoneGroup;
    }

    public void setPhoneGroup(String phoneGroup) {
        this.phoneGroup = phoneGroup;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return this.phoneNumber + ", " + this.phoneGroup + ", " + this.fullName + ", " + this.gender + ", " + this.address;
    }

    public String toFile() {
        return this.phoneNumber + "," + this.phoneGroup + "," + this.fullName + "," + this.gender + "," + this.address + "," + this.dateOfBirth + "," + this.email + "\n";
    }
}
