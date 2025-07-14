// GuestbookResponseDTO.java
package com.dgsw.guestbookproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestbookResponseDTO {

    @Schema(description = "방명록 고유 ID", example = "1")
    private Long id;

    @Schema(description = "작성자 이름", example = "세희")
    private String writer;

    @Schema(description = "방명록 메시지", example = "안녕하세요!")
    private String message;

    @Schema(description = "작성 시간", example = "2025-06-24T14:12:00")
    private String createdAt;
}
