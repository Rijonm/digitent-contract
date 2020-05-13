package rocks.process.digient.domain;

import org.springframework.core.style.ToStringCreator;

public class Customer {

    private String cId;
    private String cName;
    private String email;

    public Customer() {
    }

    public Customer(String cId, String cName, String email) {
        super();
        this.cId = cId;
        this.cName = cName;
        this.email = email;
    }

    public String getCId() {
        return cId;
    }

    public void setCId(String cId) {
        this.cId = cId;
    }

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this).append("cId", cId).append("cName", cName).append("email", email).toString();
    }

}