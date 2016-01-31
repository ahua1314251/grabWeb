package grab.dal.model;

public class CompanyInfoWithBLOBs extends CompanyInfo {
    private String businessScope;

    private String allInfo;

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope == null ? null : businessScope.trim();
    }

    public String getAllInfo() {
        return allInfo;
    }

    public void setAllInfo(String allInfo) {
        this.allInfo = allInfo == null ? null : allInfo.trim();
    }
}