package project.model.AdminModel;

public class Admin{

    private String adminName;

    private int Adminid;

    public Admin(String adminName,int Adminid){
        this.adminName=adminName;
        this.Adminid=Adminid;
    }

    public String geName(){
        return adminName;
    }

    public void setName(String adminName){
        this.adminName=adminName;
    }
    
}