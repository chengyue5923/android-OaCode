package com.mogujie.tt.DB.entity;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here

import android.text.TextUtils;

import com.mogujie.tt.config.DBConstant;
import com.mogujie.tt.imservice.entity.SearchElement;
import com.mogujie.tt.utils.pinyin.PinYin.PinYinElement;
// KEEP INCLUDES END

/**
 * Entity mapped to table UserInfo.
 */
public class UserEntity extends PeerEntity {

    private int gender;
    /**
     * Not-null value.
     */
    private String pinyinName;
    /**
     * Not-null value.
     */
    private String realName;
    /**
     * Not-null value.
     */
    private String phone;
    /**
     * Not-null value.
     */
    private String email;
    private int departmentId;
    private int status;

    // KEEP FIELDS - put your custom fields here
    private PinYinElement pinyinElement = new PinYinElement();
    private SearchElement searchElement = new SearchElement();
    // KEEP FIELDS END

    public UserEntity() {
    }

    public UserEntity(Long id) {
        this.id = id;
    }

    public UserEntity(Long id, int peerId, int gender, String mainName, String pinyinName, String realName, String avatar, String phone, String email, int departmentId, int status, int created, int updated) {
        this.id = id;
        this.peerId = peerId;
        this.gender = gender;
        this.mainName = mainName;
        this.pinyinName = pinyinName;
        this.realName = realName;
        this.avatar = avatar;
        this.phone = phone;
        this.email = email;
        this.departmentId = departmentId;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPeerId() {
        return peerId;
    }

    public void setPeerId(int peerId) {
        this.peerId = peerId;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    /**
     * Not-null value.
     */
    public String getMainName() {
        return mainName;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    /**
     * Not-null value.
     */
    public String getPinyinName() {
        return pinyinName;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setPinyinName(String pinyinName) {
        this.pinyinName = pinyinName;
    }

    /**
     * Not-null value.
     */
    public String getRealName() {
        return realName;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * Not-null value.
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Not-null value.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Not-null value.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getUpdated() {
        return updated;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }

    // KEEP METHODS - put your custom methods here


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", peerId=" + peerId +
                ", gender=" + gender +
                ", mainName='" + mainName + '\'' +
                ", pinyinName='" + pinyinName + '\'' +
                ", realName='" + realName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", departmentId=" + departmentId +
                ", status=" + status +
                ", created=" + created +
                ", updated=" + updated +
                ", pinyinElement=" + pinyinElement +
                ", searchElement=" + searchElement +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;

        UserEntity entity = (UserEntity) o;

        if (departmentId != entity.departmentId) return false;
        if (gender != entity.gender) return false;
        if (peerId != entity.peerId) return false;
        if (status != entity.status) return false;
        if (avatar != null ? !avatar.equals(entity.avatar) : entity.avatar != null) return false;
        if (email != null ? !email.equals(entity.email) : entity.email != null) return false;
        if (mainName != null ? !mainName.equals(entity.mainName) : entity.mainName != null)
            return false;
        if (phone != null ? !phone.equals(entity.phone) : entity.phone != null) return false;
        if (pinyinName != null ? !pinyinName.equals(entity.pinyinName) : entity.pinyinName != null)
            return false;
        if (realName != null ? !realName.equals(entity.realName) : entity.realName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = peerId;
        result = 31 * result + gender;
        result = 31 * result + (mainName != null ? mainName.hashCode() : 0);
        result = 31 * result + (pinyinName != null ? pinyinName.hashCode() : 0);
        result = 31 * result + (realName != null ? realName.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + departmentId;
        result = 31 * result + status;
        return result;
    }

    public PinYinElement getPinyinElement() {
        return pinyinElement;
    }


    public SearchElement getSearchElement() {
        return searchElement;
    }

    public String getSectionName() {
        if (TextUtils.isEmpty(pinyinElement.pinyin)) {
            return "";
        }
        return pinyinElement.pinyin.substring(0, 1);
    }

    @Override
    public int getType() {
        return DBConstant.SESSION_TYPE_SINGLE;
    }

    // KEEP METHODS END

}
