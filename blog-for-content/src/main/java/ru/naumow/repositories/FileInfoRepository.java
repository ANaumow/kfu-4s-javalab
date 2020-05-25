package ru.naumow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.naumow.entity.FileInfo;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {

}
