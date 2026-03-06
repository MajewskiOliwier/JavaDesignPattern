package allnew.okk;

public class PrivateAccountBuilder extends AccountBuilder<PrivateAccountBuilder>{
    private String name;
    private String surname;

    @Override
    protected PrivateAccountBuilder self() {
        return this;
    }

    public PrivateAccountBuilder Name(String name){
        this.name = name;
        return this;
    }

    public PrivateAccountBuilder Surname(String surname){
        this.surname = surname;
        return this;
    }

    public PrivateAccount build(){
        //to validate
       return new PrivateAccount(email, password, name, surname);
    }
}