package com.recruit.recbase.service;

import com.recruit.recbase.dao.CandidateDao;
import com.recruit.recbase.model.CandidateCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    CandidateDao candidateDao;

    @Override
    public void saveCandidate(CandidateCard candidateCard) {
        candidateDao.saveCandidate(candidateCard);
    }

    @Override
    public List<CandidateCard> findAll() {
        return candidateDao.findAll();
    }

    @Override
    public CandidateCard findOne(String id) {
        return candidateDao.findOne(id);
    }

    @Override
    public List<CandidateCard> findByName(String name) {
        return candidateDao.findByName(name);
    }

    @Override
    public CandidateCard assigneeRecruiter(String id, String name) {
        return candidateDao.assigneeRecruiter(id, name);
    }
}
