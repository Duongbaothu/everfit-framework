package testData;

public class EmployeeInfo {

    private String firstName = "John";
    private String lastName = "Brown";
    private String EmployeeID;

    private String driverLicenseNumber = "D08954796";
    private String licenseExpiryDate = "2024-01-03";
    private String nationality = "American";
    private String maritalStatus = "Married";
    private String employeeDateOfBirth = "1998-03-02";
    private String genderStatus = "Male";

    private String street1 = "2406 Valley Street";
    private String city = "Camden";
    private String state = "New Jersey";
    private String zip = "08102";
    private String country = "United States";
    private String mobile = "856-898-7280";

    private String emergencyContactName = "Anna Faris";
    private String emergencyRelationship = "Wife";
    private String emergencyContactMobile = "678-399-4725";

    private String dependentName = "Rosy Brown";
    private String dependentRelationship = "Child";
    private String dependentDateOfBirth = "2022-07-04";

    private String documentType = "Passport";
    private String docNumber = "422016774";
    private String issuedDate = "2017-24-12";
    private String expiryDate = "2027-23-12";
    private String issuedBy = "United States";
    private String eligibleReviewDate = "2023-23-12";

    private String joinedDate = "2022-07-04";
    private String jobCategory = "Officials and Managers";
    private String contractStartDate = "2022-09-04";
    private String contractEndDate = "2025-09-04";
    private String employmentStatus;

    private String salaryComponent = "Lunch Allowance";
    private String payFrequency = "Monthly";
    private String currency = "United States Dollar";
    private String amount1 = "500";
    private String accountNumber = "74498674";
    private String accountType = "Checking";
    private String routingNumber = "913661442";
    private String amount2 = "300";

    private String supervisorName = firstName + " " + lastName;
    private String reportingMethod = "Direct";
    private String subordinateName = firstName + " " + lastName;

    private String company = "FPT shop";
    private String jobTitleEmployee;
    private String workFrom = "2020-12-23";
    private String workTo = "2022-12-23";

    private String workEmail;
    private String dateAdded;
    private String image;

    public static EmployeeInfo getEmployee(){
        return new EmployeeInfo();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    public void setLicenseExpiryDate(String licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEmployeeDateOfBirth() {
        return employeeDateOfBirth;
    }

    public void setEmployeeDateOfBirth(String employeeDateOfBirth) {
        this.employeeDateOfBirth = employeeDateOfBirth;
    }

    public String getGenderStatus() {
        return genderStatus;
    }

    public void setGenderStatus(String genderStatus) {
        this.genderStatus = genderStatus;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyRelationship() {
        return emergencyRelationship;
    }

    public void setEmergencyRelationship(String emergencyRelationship) {
        this.emergencyRelationship = emergencyRelationship;
    }

    public String getEmergencyContactMobile() {
        return emergencyContactMobile;
    }

    public void setEmergencyContactMobile(String emergencyContactMobile) {
        this.emergencyContactMobile = emergencyContactMobile;
    }

    public String getDependentName() {
        return dependentName;
    }

    public void setDependentName(String dependentName) {
        this.dependentName = dependentName;
    }

    public String getDependentRelationship() {
        return dependentRelationship;
    }

    public void setDependentRelationship(String dependentRelationship) {
        this.dependentRelationship = dependentRelationship;
    }

    public String getDependentDateOfBirth() {
        return dependentDateOfBirth;
    }

    public void setDependentDateOfBirth(String dependentDateOfBirth) {
        this.dependentDateOfBirth = dependentDateOfBirth;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public String getEligibleReviewDate() {
        return eligibleReviewDate;
    }

    public void setEligibleReviewDate(String eligibleReviewDate) {
        this.eligibleReviewDate = eligibleReviewDate;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getJobTitleEmployee() {
        return jobTitleEmployee;
    }

    public void setJobTitleEmployee(String jobTitleEmployee) {
        this.jobTitleEmployee = jobTitleEmployee;
    }

    public String getWorkFrom() {
        return workFrom;
    }

    public void setWorkFrom(String workFrom) {
        this.workFrom = workFrom;
    }

    public String getWorkTo() {
        return workTo;
    }

    public void setWorkTo(String workTo) {
        this.workTo = workTo;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public String getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(String contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getSalaryComponent() {
        return salaryComponent;
    }

    public void setSalaryComponent(String salaryComponent) {
        this.salaryComponent = salaryComponent;
    }

    public String getPayFrequency() {
        return payFrequency;
    }

    public void setPayFrequency(String payFrequency) {
        this.payFrequency = payFrequency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount1() {
        return amount1;
    }

    public void setAmount1(String amount1) {
        this.amount1 = amount1;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public String getAmount2() {
        return amount2;
    }

    public void setAmount2(String amount2) {
        this.amount2 = amount2;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getReportingMethod() {
        return reportingMethod;
    }

    public void setReportingMethod(String reportingMethod) {
        this.reportingMethod = reportingMethod;
    }

    public String getSubordinateName() {
        return subordinateName;
    }

    public void setSubordinateName(String subordinateName) {
        this.subordinateName = subordinateName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
