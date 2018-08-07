package mars.chatroom.bean;

/**
 * Created by hluo on 2017/4/25.
 */

public class Group {
    /**
     * 圈子头像
     */
    private String gIcon;
    /**
     * 圈子标题
     */
    private String gName;
    /**
     * 圈子人数
     */
    private int gPerson;
    /**
     * 圈子介绍
     */
    private String gDes;

    public Group(String gIcon, String gName, int gPerson, String gDes) {
        this.gIcon = gIcon;
        this.gName = gName;
        this.gPerson = gPerson;
        this.gDes = gDes;
    }

    public String getgIcon() {
        return gIcon;
    }

    public void setgIcon(String gIcon) {
        this.gIcon = gIcon;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public int getgPerson() {
        return gPerson;
    }

    public void setgPerson(int gPerson) {
        this.gPerson = gPerson;
    }

    public String getgDes() {
        return gDes;
    }

    public void setgDes(String gDes) {
        this.gDes = gDes;
    }

    @Override
    public String toString() {
        return "Group{" +
                "gIcon='" + gIcon + '\'' +
                ", gName='" + gName + '\'' +
                ", gPerson=" + gPerson +
                ", gDes='" + gDes + '\'' +
                '}';
    }
}
