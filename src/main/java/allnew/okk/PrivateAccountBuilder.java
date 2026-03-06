public class PrivateAccountBuilder extends AccountBuilder{
    private String name;
    private String surname;

    public PrivateAccountBuilder setName(String name){
        this.name = name;
        return this;
    }

    public PrivateAccountBuilder setSurname(String surname){
        this.surname = surname;
        return this;
    }

    public PrivateAccount build(){
        //to validate
       return new PrivateAccount(email, password, name, surname);
    }
}