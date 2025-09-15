package com.mallppang.member;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long>{
	@EntityGraph(attributePaths = {"memberRoleList"})
	@Query("SELECT m FROM Member m WHERE m.email = :email")
	Member getWithRoles(@Param("email") String email);
	
	Member findByEmailAndPassword(String email, String password);
	Optional<Member> findByEmail(String email);
}
