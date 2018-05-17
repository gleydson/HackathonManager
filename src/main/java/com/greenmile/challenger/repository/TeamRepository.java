package com.greenmile.challenger.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.greenmile.challenger.bean.Team;

public interface TeamRepository extends PagingAndSortingRepository<Team, Long> {

}
