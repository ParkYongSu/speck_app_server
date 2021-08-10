package paidagogos.speck.login;

public class UserInfo {
    private String email;
    private String bornTime;
    private String sex;
    private String nickname;
    private String phoneNumber;
    private String profile;
    private Integer characterIndex;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBornTime() { return bornTime; }

    public void setBornTime(String bornTime) { this.bornTime = bornTime; }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getCharacterIndex() {
        return characterIndex;
    }

    public void setCharacterIndex(Integer characterIndex) {
        this.characterIndex = characterIndex;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
