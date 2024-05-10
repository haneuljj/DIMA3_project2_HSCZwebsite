package net.kdigital.web_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.entity.CustomerEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CustomerDTO {
	private String userId;
	private String userPwd;
	private String userName;
	private String userRole;
	private String phone;
	private String email;
	private String companyName;
	private String companyRegion;
	private int likeTotal;
	private int ccaNum;
	private String selfInfo;
	
	public static CustomerDTO toDTO(CustomerEntity customerEntity) {
		return CustomerDTO.builder()
				.userId(customerEntity.getUserId())
				.userPwd(customerEntity.getUserPwd())
				.userName(customerEntity.getUserName())
				.userRole(customerEntity.getUserRole())
				.phone(customerEntity.getPhone())
				.email(customerEntity.getEmail())
				.companyName(customerEntity.getCompanyName())
				.companyRegion(customerEntity.getCompanyRegion())
				.likeTotal(customerEntity.getLikeTotal())
				.ccaNum(customerEntity.getCcaNum())
				.selfInfo(customerEntity.getSelfInfo())
				.build();
	}
	
	public CustomerDTO(String userName, int likeTotal, String companyName, String companyRegion, String phone,
			String email) {
		super();
		this.userName= userName;
		this.likeTotal = likeTotal;
		this.companyName = companyName;
		this.companyRegion = companyRegion;
		this.phone = phone;
		this.email =email;
	}
	
}
