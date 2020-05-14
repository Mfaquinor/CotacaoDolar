package com.cateno.repositories;

import com.cateno.models.DollarQuote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DollarQuoteRepository extends JpaRepository<DollarQuote, String> {
}
