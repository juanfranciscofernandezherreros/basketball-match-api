package com.fernandez.basketball.matchapi.match.repository;

import com.fernandez.basketball.matchapi.match.document.PointByPointQuarterDocument;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PointByPointQuarterRepository
        extends MongoRepository<PointByPointQuarterDocument, String> {

    List<PointByPointQuarterDocument> findByMatchIdOrderByQuarterAsc(String matchId);
}
