package OOPAssignment.Assignment2;

public class Person {
    private String name;
    private String nrcno;
    private String address;
    private String phone;

    public Person() {

    }

    public Person(String name, String nrcno, String address, String phone) {
        this.name = name;
        this.nrcno = nrcno;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNrcno() {
        return nrcno;
    }

    public void setNrcno(String nrcno) {
        this.nrcno = nrcno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void showInfo() {
        System.out.println("Name: " + name);
        System.out.println("Nrcno: " + nrcno);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println();
    }

    public void showIdentificationInfo() {
        System.out.println("Division/State: " + nrcno.substring(0, nrcno.indexOf("/")));
        System.out.println("City: " + nrcno.substring(nrcno.indexOf("/") + 1, nrcno.lastIndexOf("(")));
        System.out.println("Nrc No: " + nrcno.substring(nrcno.lastIndexOf(")") + 1));
        System.out.println();
    }
}
