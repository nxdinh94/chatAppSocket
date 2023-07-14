
package Model;

public class Model_user {
    private String userName;
    private String gender;
    private String yob;

    public Model_user() {
    }

    public Model_user(String userName, String gender, String yob) {
        this.userName = userName;
        this.gender = gender;
        this.yob = yob;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getYob() {
        return yob;
    }

    public void setYob(String yob) {
        this.yob = yob;
    }

    @Override
    public String toString() {
        return "Model_user{" + "userName=" + userName + ", gender=" + gender + ", yob=" + yob + '}';
    }
    
}
