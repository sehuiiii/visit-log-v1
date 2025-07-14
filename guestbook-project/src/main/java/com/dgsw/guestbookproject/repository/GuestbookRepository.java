package com.dgsw.guestbookproject.repository;

import com.dgsw.guestbookproject.domain.GuestbookEntry;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GuestbookRepository extends JpaRepository<GuestbookEntry, Long> {
}
