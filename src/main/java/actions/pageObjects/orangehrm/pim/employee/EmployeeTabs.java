package actions.pageObjects.orangehrm.pim.employee;

import actions.commons.BasePage;
import org.openqa.selenium.WebDriver;
import actions.pageObjects.orangehrm.PageGenerator;
import interfaces.pageUIs.orangehrm.pim.employee.EmployeeTabsPUI;

public class EmployeeTabs extends BasePage {
    private WebDriver driver;

    public EmployeeTabs(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public PersonalDetailsPO openPersonalDetailsPage() {
        waitForElementClickable(driver, EmployeeTabsPUI.PERSONAL_DETAILS_LINK);
        clickToElement(driver, EmployeeTabsPUI.PERSONAL_DETAILS_LINK);
        return PageGenerator.getPage(PersonalDetailsPO.class, driver);
    }

    public ContactDetailsPO openContactDetailsPage() {
        waitForElementClickable(driver, EmployeeTabsPUI.CONTACT_DETAILS_LINK);
        clickToElement(driver, EmployeeTabsPUI.CONTACT_DETAILS_LINK);
        return PageGenerator.getPage(ContactDetailsPO.class, driver);
    }

    public EmergencyContactsPO openEmergencyContactsPage() {
        waitForElementClickable(driver, EmployeeTabsPUI.EMERGENCY_CONTACTS_LINK);
        clickToElement(driver, EmployeeTabsPUI.EMERGENCY_CONTACTS_LINK);
        return PageGenerator.getPage(EmergencyContactsPO.class, driver);
    }

//    public DependentsPO openDependentsPage() {
//        waitForElementClickable(driver, EmployeeTabsPUI.DEPENDENTS_LINK);
//        clickToElement(driver, EmployeeTabsPUI.DEPENDENTS_LINK);
//        return PageGenerator.getDependentsPage(driver);
//    }
//    public ImmigrationPO openImmigrationPage() {
//        waitForElementClickable(driver, EmployeeTabsPUI.IMMIGRATION_LINK);
//        clickToElement(driver, EmployeeTabsPUI.IMMIGRATION_LINK);
//        return PageGenerator.getImmigrationPage(driver);
//    }
//    public JobsPO openJobsPage() {
//        waitForElementClickable(driver, EmployeeTabsPUI.JOBS_LINK);
//        clickToElement(driver, EmployeeTabsPUI.JOBS_LINK);
//        return PageGenerator.getJobsPage(driver);
//    }
//    public SalaryPO openSalaryPage() {
//        waitForElementClickable(driver, EmployeeTabsPUI.SALARY_LINK);
//        clickToElement(driver, EmployeeTabsPUI.SALARY_LINK);
//        return PageGenerator.getSalaryPage(driver);
//    }
//    public ReportToPO openReportToPage() {
//        waitForElementClickable(driver, EmployeeTabsPUI.REPORT_TO_LINK);
//        clickToElement(driver, EmployeeTabsPUI.REPORT_TO_LINK);
//        return PageGenerator.getReportToPage(driver);
//    }
//    public QualificationsPO openQualificationsPage() {
//        waitForElementClickable(driver, EmployeeTabsPUI.QUALIFICATIONS_LINK);
//        clickToElement(driver, EmployeeTabsPUI.QUALIFICATIONS_LINK);
//        return PageGenerator.getQualificationsPage(driver);
//    }
//    public MembershipsPO openMembershipsPage() {
//        waitForElementClickable(driver, EmployeeTabsPUI.MEMBERSHIPS_LINK);
//        clickToElement(driver, EmployeeTabsPUI.MEMBERSHIPS_LINK);
//        return PageGenerator.getMembershipsPage(driver);
//    }
}