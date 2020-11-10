package com.wooribank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wooribank.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
