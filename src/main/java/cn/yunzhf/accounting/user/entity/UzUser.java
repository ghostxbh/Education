package cn.yunzhf.accounting.user.entity;


import java.io.Serializable;
import java.util.Date;

public class UzUser implements Serializable{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5237814705692404250L;

	private Integer id;

    private String name;

    private String code;

    private String password;

    private String phone;

    private String email;

    private String idcard;

    private String sex;

    private Date born;

    private Integer createrId;

    private String createTime;

    private Integer updaterId;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Integer updaterId) {
        this.updaterId = updaterId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String toString() {
		return "UzUser [id=" + id + ", name=" + name + ", code=" + code + ", password=" + password + ", phone=" + phone
				+ ", email=" + email + ", idcard=" + idcard + ", sex=" + sex + ", born=" + born + ", createrId="
				+ createrId + ", createTime=" + createTime + ", updaterId=" + updaterId + ", updateTime=" + updateTime
				+ "]";
	}
    
    
    
}