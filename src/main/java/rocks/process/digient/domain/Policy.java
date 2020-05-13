package rocks.process.digient.domain;

import org.springframework.core.style.ToStringCreator;

public class Policy {

    private String pId;
    private String cId;
    private String aId;
    private String pDate;
    private String productId;
    private String carId;
    private String status;
    private String risk;
    private Long retention;

    public Policy() {
    }

    public Policy(String pId, String cId, String aId, String pDate, String productId, String carId, String status, String risk, Long retention) {
        super();
        this.pId = pId;
        this.cId = cId;
        this.aId = aId;
        this.pDate = pDate;
        this.productId = productId;
        this.carId = carId;
        this.status = status;
        this.risk = risk;
        this.retention = retention;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getCId() {
        return cId;
    }

    public void setCId(String cId) {
        this.cId = cId;
    }

    public String getAId() {
        return aId;
    }

    public void setAId(String aId) {
        this.aId = aId;
    }

    public String getPDate() {
        return pDate;
    }

    public void setPDate(String pDate) {
        this.pDate = pDate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return new ToStringCreator(this).append("pId", pId).append("cId", cId).append("aId", aId).append("pDate", pDate).append("productId", productId).append("carId", carId).append("status", status).append("risk", risk).append("retention", retention).toString();
    }

}