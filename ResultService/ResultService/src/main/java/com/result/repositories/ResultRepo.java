package com.result.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.result.entities.Result;

public interface ResultRepo extends JpaRepository<Result,Long> {

}
