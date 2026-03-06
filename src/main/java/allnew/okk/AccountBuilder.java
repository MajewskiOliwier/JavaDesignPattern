public static class AccountBuilder{
    private String email;
    private String password;

    public AccountBuilder email(String email){
        this.email = email;
        return this;
    }

    public AccountBuilder password(String password){
        this.password = password;
        return this;
    }
}