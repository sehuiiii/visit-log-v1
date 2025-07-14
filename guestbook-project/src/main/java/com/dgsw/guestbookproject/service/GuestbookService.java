package com.dgsw.guestbookproject.service;

import com.dgsw.guestbookproject.domain.GuestbookEntry;
import com.dgsw.guestbookproject.dto.GuestbookRequestDTO;
import com.dgsw.guestbookproject.dto.GuestbookResponseDTO;
import com.dgsw.guestbookproject.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestbookService {
    private final GuestbookRepository repository;

    // 방명록 리스트 조회
    public List<GuestbookResponseDTO> getAllEntries() {
        return repository.findAll().stream()
                .map(entry -> GuestbookResponseDTO.builder()
                        .id(entry.getId())
                        .writer(entry.getWriter())
                        .message(entry.getMessage())
                        .createdAt(entry.getCreatedAt().toString())
                        .build())
                .collect(Collectors.toList());
    }

    // 방명록 작성
    public GuestbookResponseDTO createEntry(GuestbookRequestDTO dto) {
        GuestbookEntry entry = GuestbookEntry.builder()
                .writer(dto.getWriter())
                .message(dto.getMessage())
                .password(dto.getPassword())
                .build();
        GuestbookEntry saved = repository.save(entry);
        return GuestbookResponseDTO.builder()
                .id(saved.getId())
                .writer(saved.getWriter())
                .message(saved.getMessage())
                .createdAt(saved.getCreatedAt().toString())
                .build();
    }

    // 방명록 상세 조회
    public GuestbookResponseDTO getEntry(Long id) {
        GuestbookEntry entry = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
        return GuestbookResponseDTO.builder()
                .id(entry.getId())
                .writer(entry.getWriter())
                .message(entry.getMessage())
                .createdAt(entry.getCreatedAt().toString())
                .build();
    }

    // 방명록 수정 (컨트롤러에서 password를 별도 파라미터로 받는 경우)
    public GuestbookResponseDTO updateEntry(Long id, GuestbookRequestDTO dto, String password) {
        // 기존 엔티티 조회
        GuestbookEntry entry = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));

        // 비밀번호 검증
        if (!entry.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        // 엔티티 업데이트 (writer는 수정하지 않음)
        entry.setMessage(dto.getMessage());

        // 저장 및 반환
        GuestbookEntry updatedEntry = repository.save(entry);
        return GuestbookResponseDTO.builder()
                .id(updatedEntry.getId())
                .writer(updatedEntry.getWriter())
                .message(updatedEntry.getMessage())
                .createdAt(updatedEntry.getCreatedAt().toString())
                .build();
    }

    // 방명록 삭제 (비밀번호 체크 포함)
    public void deleteEntry(Long id, String password) {
        GuestbookEntry entry = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
        if (!entry.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }
        repository.delete(entry);
    }
}