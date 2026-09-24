package com.fernandez.basketball.matchapi.match.repository;

import com.fernandez.basketball.matchapi.match.document.MatchDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepository extends MongoRepository<MatchDocument, String> {}
