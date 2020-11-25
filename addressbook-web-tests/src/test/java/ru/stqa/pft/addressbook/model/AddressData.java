package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="addressbook")
public class AddressData {
    @Id
    @Column(name="id")
    private int id= Integer.MAX_VALUE;
    @Expose
    @Column(name="firstname")
    private String firstName;
    private String middleName;
    @Expose
    @Column(name="lastname")
    private String lastName;
    @Expose
    @Type(type = "text")
    @Column(name="address")
    private String address;
    @Expose
    @Type(type = "text")
    private String email;
    @Type(type = "text")
    private String email2;
    @Type(type = "text")
    private String email3;
    @Expose
    @Type(type = "text")
    @Column(name="home")
    private String home;
    @Expose
    @Type(type = "text")
    @Column(name="mobile")
    private String mobile;
    @Expose
    @Type(type = "text")
    @Column(name="work")
    private String work;
    @Transient
    private String allPhones;
    @Transient
    private String allEmails;



    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private static Set<GroupData> groups = new HashSet<GroupData>();

    @Override
    public String toString() {
        return "AddressData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Column(name="photo")
    @Type(type = "text")
    private String photo;


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

    public String getHome() { return home; }

    public String getMobile() { return mobile; }

    public String getWork() { return work; }

    public String getAllPhones() { return allPhones; }

    public String getEmail2() { return email2; }

    public String getEmail3() { return email3; }

    public String getAllEmails() { return allEmails; }

    public static Groups getGroups() { return new Groups(groups); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressData that = (AddressData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    //public File getPhoto() { return new File(photo); }



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

    public AddressData withHome(String home) {
        this.home = home;
        return this;
    }

    public AddressData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public AddressData withWork(String work) {
        this.work = work;
        return this;
    }

    public AddressData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public AddressData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }


    public AddressData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public AddressData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public AddressData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public AddressData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }

}
