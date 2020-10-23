package ru.stqa.pft.addressbook.model;

public class AddressData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickname;
    private final String title;
    private final String company;
    private final String address;
    private final String homeNumber;
    private final String mobileNumber;
    private final String workNumber;
    private final String faxNumber;
    private final String email;
    private final String secondEmail;
    private final String thirdEmail;
    private final String homepage;
    private final String bDay;
    private final String bMonth;
    private final String bYear;
    private final String aday;
    private final String amonth;
    private final String ayear;
    private String group;
    private final String address2;
    private final String phone2;
    private final String notes;

    public AddressData(String firstName, String middleName, String lastName, String nickname, String title, String company, String address, String homeNumber, String mobileNumber, String workNumber, String faxNumber, String email, String secondEmail, String thirdEmail, String homepage, String bDay, String bMonth, String bYear, String aday, String amonth, String ayear,String group,String address2, String phone2, String notes) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.homeNumber = homeNumber;
        this.mobileNumber = mobileNumber;
        this.workNumber = workNumber;
        this.faxNumber = faxNumber;
        this.email = email;
        this.secondEmail = secondEmail;
        this.thirdEmail = thirdEmail;
        this.homepage = homepage;
        this.bDay = bDay;
        this.bMonth = bMonth;
        this.bYear = bYear;
        this.aday = aday;
        this.amonth = amonth;
        this.ayear = ayear;
        this.group = group;
        this.address2 = address2;
        this.phone2 = phone2;
        this.notes = notes;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getSecondEmail() {
        return secondEmail;
    }

    public String getThirdEmail() {
        return thirdEmail;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getbDay() {
        return bDay;
    }

    public String getbMonth() {
        return bMonth;
    }

    public String getbYear() {
        return bYear;
    }

    public String getAday() {
        return aday;
    }

    public String getAmonth() {
        return amonth;
    }

    public String getAyear() {
        return ayear;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getNotes() {
        return notes;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressData that = (AddressData) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
