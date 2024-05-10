package net.kdigital.web_project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.web_project.dto.CustomerDTO;
import net.kdigital.web_project.entity.AnswerEntity;
import net.kdigital.web_project.entity.CustomerEntity;
import net.kdigital.web_project.entity.CustomerLikeEntity;
import net.kdigital.web_project.repository.AnswerRepository;
import net.kdigital.web_project.repository.CustomerLikeRepository;
import net.kdigital.web_project.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

	public final CustomerLikeRepository customerLikeRepository;
	public final AnswerRepository answerRepository;
	public final CustomerRepository customerRepository;
	public final BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * 회원가입
	 * 
	 */
	public boolean joinProc(CustomerDTO customerDTO) {
		boolean isExistCustomer = customerRepository.existsById(customerDTO.getUserId());
		if (isExistCustomer)
			return false;

		// 비번을 암호화
		customerDTO.setUserPwd(bCryptPasswordEncoder.encode(customerDTO.getUserPwd()));

		// DTO를 Entity로 변경
		CustomerEntity customerEntity = CustomerEntity.toEntity(customerDTO);
		log.info("{}", customerEntity.toString());
		customerRepository.save(customerEntity);
		return true;
	}

	public CustomerEntity findCustomerByUserId(String replyWriter) {
		Optional<CustomerEntity> entity = customerRepository.findById(replyWriter);

		if (entity.isPresent()) {
			CustomerEntity customerEntity = entity.get();
			return customerEntity;
		}

		return null;
	}

	@Transactional
	public CustomerDTO increaseTotalLike(CustomerEntity customer) {
		if (customer != null) {
			customer.setLikeTotal(customer.getLikeTotal() + 1); // likeTotal 증가
			customerRepository.save(customer); // 데이터베이스에 변경 사항 저장
			return CustomerDTO.toDTO(customer); // DTO 변환 후 반환
		}
		return null;
	}

	public Page<CustomerDTO> findAllUserCCA(Pageable pageable) {
		Page<CustomerEntity> entities = customerRepository.findAllUserCCA(pageable);

		Page<CustomerDTO> dtoList = entities.map(cca -> new CustomerDTO(
				cca.getUserName(),
				cca.getLikeTotal(),
				cca.getCompanyName(),
				cca.getCompanyRegion(),
				cca.getPhone(),
				cca.getEmail()));
		System.out.println(dtoList);
		return dtoList;

	}

	public boolean findByUserId(String userId) {

		boolean customerEntity = customerRepository.existsById(userId);

		return customerEntity;
	}

	@Transactional
	public CustomerDTO updateUser(String username, CustomerDTO customerDTO) {
		Optional<CustomerEntity> originalEntity = customerRepository.findByUserId(username);

		CustomerEntity customerEntity = originalEntity.get();

		customerEntity.setUserName(customerDTO.getUserName());
		customerEntity.setPhone(customerDTO.getPhone());
		customerEntity.setEmail(customerDTO.getEmail());
		customerEntity.setCompanyName(customerDTO.getCompanyName());
		customerEntity.setCompanyRegion(customerDTO.getCompanyRegion());
		customerEntity.setSelfInfo(customerDTO.getSelfInfo());
		customerEntity.setUserId(customerDTO.getUserId());

		return CustomerDTO.toDTO(customerEntity);
	}

	public boolean checkIsAlreadyLiked(Long replyNum, String username) {
		AnswerEntity answerEntity = answerRepository.findById(replyNum).get();

		boolean isLIkeExist = customerLikeRepository.existsByUserIdAndAnswerEntity(username, answerEntity);

		return !isLIkeExist;
	}

	public void insertCustomerLike(Long replyNum, String username) {
		CustomerLikeEntity entity = new CustomerLikeEntity();
		AnswerEntity answerEntity = answerRepository.findById(replyNum).get();

		entity.setUserId(username);
		entity.setAnswerEntity(answerEntity);

		customerLikeRepository.save(entity);
	}

	@Transactional
	public void increaseLikeTotal(String replyWriter) {
		CustomerEntity customerEntity = customerRepository.findById(replyWriter).get();

		customerEntity.setLikeTotal(customerEntity.getLikeTotal() + 1);
	}

	public List<CustomerDTO> selectTop3CCA() {
		List<CustomerEntity> entityList = customerRepository.findTop3ByLikeTotal();

		List<CustomerDTO> dtoList = new ArrayList<>();
		entityList.forEach((entitiy) -> dtoList.add(CustomerDTO.toDTO(entitiy)));

		return dtoList;
	}

}
