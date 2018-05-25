package com.greenmile.challenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenmile.challenger.bean.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByEmail(String email);
}
