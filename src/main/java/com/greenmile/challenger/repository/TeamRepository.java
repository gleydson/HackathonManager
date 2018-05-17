package com.greenmile.challenger.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.greenmile.challenger.bean.MemberTest;

public interface TeamRepository extends PagingAndSortingRepository<MemberTest, Long> {

}
