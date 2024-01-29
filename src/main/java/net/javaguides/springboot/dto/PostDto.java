package net.javaguides.springboot.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {



    private Long id;

    @NotEmpty (message = "Post title should not be empty")
    private String title;

    private String url;

    @NotEmpty
    private String content;

    @NotEmpty

    private String shortDescription;


    private LocalDateTime createdOn;


    private LocalDateTime updatedOn;
}
