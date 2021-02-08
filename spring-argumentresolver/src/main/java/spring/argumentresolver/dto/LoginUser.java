package spring.argumentresolver.dto;

public class LoginUser {

    private Long id;
    private String identification;

    protected LoginUser() {}

    public LoginUser(final Long id, final String identification) {
        this.id = id;
        this.identification = identification;
    }

    public Long getId() {
        return id;
    }

    public String getIdentification() {
        return identification;
    }
}
