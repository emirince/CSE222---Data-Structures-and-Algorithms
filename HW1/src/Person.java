public class Person{

    private String name;
    private String surname;
    private String address;
    private String phone;
    private int ID;

    public Person(){}
    public Person(String person_name, String person_surname, String person_address, String person_phone, String person_ID){
        name = person_name;
        surname = person_surname;
        address = person_address;
        phone = person_phone;
        ID = Integer.parseInt(person_ID); 
    } 
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getAddress(){
        return address;
    }
    public String getPhone(){
        return phone;
    }
    public int getID(){
        return ID;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setID(int ID){
        this.ID = ID;
    }
}