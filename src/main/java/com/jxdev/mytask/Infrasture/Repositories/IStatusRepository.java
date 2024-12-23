package com.jxdev.mytask.Infrasture.Repositories;

import com.jxdev.mytask.Entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IStatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByName(String name);

}
