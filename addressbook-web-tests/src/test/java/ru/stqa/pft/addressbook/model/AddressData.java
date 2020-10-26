package ru.stqa.pft.addressbook.model;

public class AddressData {
    private int id= Integer.MAX_VALUE;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String email;
    private String number;


    @Override
    public String toString() {
        return "AddressData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public int getId() { return id; }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() { return middleName; }

    public String getLastName() {
        return lastName;
    }


    public String getAddress() { return address; }

    public String getEmail() { return email; }

    public String getNumber() { return number; }



    public AddressData withId(int id) {
        this.id = id;
        return this;
    }

    public AddressData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AddressData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public AddressData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }


    public AddressData withAddress(String address) {
        this.address = address;
        return this;
    }


    public AddressData withEmail(String email) {
        this.email = email;
        return this;
    }

    public AddressData withNumber(String number) {
        this.number = number;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressData that = (AddressData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
