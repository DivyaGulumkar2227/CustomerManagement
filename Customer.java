package Sunbase_Data.model;

public class Customer 
{
   private int id;
   private String Firtname;
   private String Lastname;
   private String Address;
   private String City;
   private String email;
   private String Phone;
   
   public Customer(int id, String firtname, String lastname, String address, String city, String email, String phone) {
	super();
	this.id = id;
	Firtname = firtname;
	Lastname = lastname;
	Address = address;
	City = city;
	this.email = email;
	Phone = phone;
}
   
public Customer(String firtname, String lastname, String address, String city, String email, String phone) {
	super();
	Firtname = firtname;
	Lastname = lastname;
	Address = address;
	City = city;
	this.email = email;
	Phone = phone;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFirtname() {
	return Firtname;
}
public void setFirtname(String firtname) {
	Firtname = firtname;
}
public String getLastname() {
	return Lastname;
}
public void setLastname(String lastname) {
	Lastname = lastname;
}
public String getAddress() {
	return Address;
}
public void setAddress(String address) {
	Address = address;
}
public String getCity() {
	return City;
}
public void setCity(String city) {
	City = city;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone() {
	return Phone;
}
public void setPhone(String phone) {
	Phone = phone;
}

   
   
}
