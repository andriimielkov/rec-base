package com.recruit.recbase.service;


import com.recruit.recbase.model.CandidateCard;

import java.util.List;

public interface CandidateService {

    void saveCandidate(CandidateCard candidateCard);
    List<CandidateCard> findAll();
    CandidateCard findOne(String id);
    List<CandidateCard> findByName(String name);
    CandidateCard assigneeRecruiter(String id, String name);

}
