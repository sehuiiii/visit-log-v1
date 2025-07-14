package com.dgsw.guestbookproject.controller;

import com.dgsw.guestbookproject.dto.GuestbookRequestDTO;
import com.dgsw.guestbookproject.dto.GuestbookResponseDTO;
import com.dgsw.guestbookproject.service.GuestbookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/entries")
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService service;

    @Operation(summary = "새 방명록 작성")
    @PostMapping
    public GuestbookResponseDTO create(@RequestBody @Valid GuestbookRequestDTO dto) {
        return service.createEntry(dto);
    }

    @Operation(summary = "방명록 수정 (비밀번호 필요)")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody @Valid GuestbookRequestDTO dto,
            @RequestParam String password) {

        try {
            // 디버깅 로그
            System.out.println("=== 수정 요청 정보 ===");
            System.out.println("ID: " + id);
            System.out.println("Password: " + password);
            System.out.println("Writer: " + dto.getWriter());
            System.out.println("Message: " + dto.getMessage());

            GuestbookResponseDTO result = service.updateEntry(id, dto, password);
            return ResponseEntity.ok(result);

        } catch (RuntimeException e) {
            System.out.println("에러 발생: " + e.getMessage());
            e.printStackTrace();

            Map<String, String> error = new HashMap<>();
            if (e.getMessage().contains("Entry not found")) {
                error.put("error", "방명록을 찾을 수 없습니다.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            } else if (e.getMessage().contains("Invalid password")) {
                error.put("error", "비밀번호가 틀렸습니다.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }

            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @Operation(summary = "전체 방명록 조회")
    @GetMapping
    public List<GuestbookResponseDTO> getAll() {
        return service.getAllEntries();
    }

    @Operation(summary = "방명록 상세 조회")
    @GetMapping("/{id}")
    public GuestbookResponseDTO get(@PathVariable Long id) {
        return service.getEntry(id);
    }

    @Operation(summary = "방명록 삭제 (비밀번호 필요)")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestParam String password) {
        service.deleteEntry(id, password);
        return ResponseEntity.ok("Deleted");
    }
}