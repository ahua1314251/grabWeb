package grab.dal.model;

import java.util.Date;

public class CompanyInfo {
    private Integer keyId;

    private Long id;

    private String registerId;

    private String orgId;

    private String name;

    private String creditCode;

    private String regStatus;

    private String companyType;

    private String legalPersonName;

    private String regCapital;

    private String address;

    private Date registerDate;

    private String registerOrg;

    private Date estiblishTime;

    private Date endDate;

    private String humannames;

    private Integer orginalScore;

    private Integer score;

    private String categoryCode;

    private String industry;

    private String trademarks;

    private Integer type;

    private String base;

    private Date updateTime;

    private String path;

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId == null ? null : registerId.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus == null ? null : regStatus.trim();
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName == null ? null : legalPersonName.trim();
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital == null ? null : regCapital.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterOrg() {
        return registerOrg;
    }

    public void setRegisterOrg(String registerOrg) {
        this.registerOrg = registerOrg == null ? null : registerOrg.trim();
    }

    public Date getEstiblishTime() {
        return estiblishTime;
    }

    public void setEstiblishTime(Date estiblishTime) {
        this.estiblishTime = estiblishTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getHumannames() {
        return humannames;
    }

    public void setHumannames(String humannames) {
        this.humannames = humannames == null ? null : humannames.trim();
    }

    public Integer getOrginalScore() {
        return orginalScore;
    }

    public void setOrginalScore(Integer orginalScore) {
        this.orginalScore = orginalScore;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getTrademarks() {
        return trademarks;
    }

    public void setTrademarks(String trademarks) {
        this.trademarks = trademarks == null ? null : trademarks.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base == null ? null : base.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}