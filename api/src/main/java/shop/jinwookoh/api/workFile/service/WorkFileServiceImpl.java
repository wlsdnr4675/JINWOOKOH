package shop.jinwookoh.api.workFile.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.jinwookoh.api.workFile.repository.WorkFileRespository;

@Service
@RequiredArgsConstructor
public class WorkFileServiceImpl implements WorkFileService {
    private final WorkFileRespository repo;

}
