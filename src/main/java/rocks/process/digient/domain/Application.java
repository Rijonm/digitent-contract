package rocks.process.digient.domain;

import org.springframework.core.style.ToStringCreator;

public class Application {

    private String aId;
    private String cId;
    private String aDate;
    private String productId;
    private String carId;
    private Long age;
    private Long kw;
    private Boolean licenseRevocation;
    private Long carPrice;
    private String status;
    private String decision;
    private String risk;
    private Long retention;

    public Application() {
    }

    public Application(String aId, String cId, String aDate, String productId, String carId, Long age, Long kw, Boolean licenseRevocation, Long carPrice, String status, String decision, String risk, Long retention) {
        super();
        this.aId = aId;
        this.cId = cId;
        this.aDate = aDate;
        this.productId = productId;
        this.carId = carId;
        this.age = age;
        this.kw = kw;
        this.licenseRevocation = licenseRevocation;
        this.carPrice = carPrice;
        this.status = status;
        this.decision = decision;
        this.risk = risk;
        this.retention = retention;
    }

    public String getAId() {
        return aId;
    }

    public void setAId(String aId) {
        this.aId = aId;
    }

    public String getCId() {
        return cId;
    }

    public void setCId(String cId) {
        this.cId = cId;
    }

    public String getADate() {
        return aDate;
    }

    public void setADate(String aDate) {
        this.aDate = aDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getKw() {
        return kw;
    }

    public void setKw(Long kw) {
        this.kw = kw;
    }

    public Boolean getLicenseRevocation() {
        return licenseRevocation;
    }

    public void setLicenseRevocation(Boolean licenseRevocation) {
        this.licenseRevocation = licenseRevocation;
    }

    public Long getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Long carPrice) {
        this.carPrice = carPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public Long getRetention() {
        return retention;
    }

    public void setRetention(Long retention) {
        this.retention = retention;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this).append("aId", aId).append("cId", cId).append("aDate", aDate).append("productId", productId).append("carId", carId).append("age", age).append("kw", kw).append("licenseRevocation", licenseRevocation).append("carPrice", carPrice).append("status", status).append("decision", decision).append("risk", risk).append("retention", retention).toString();
    }

}