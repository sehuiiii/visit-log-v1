// GuestbookRequestDTO.java
package com.dgsw.guestbookproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestbookRequestDTO {

    @Schema(description = "작성자 이름", example = "세희")
    @NotBlank(message = "작성자는 필수입니다.")
    private String writer;

    @Schema(description = "방명록 메시지", example = "안녕하세요!")
    @NotBlank(message = "메시지를 입력해주세요.")
    private String message;

    @Schema(description = "비밀번호 (수정/삭제에 필요)", example = "1234")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
