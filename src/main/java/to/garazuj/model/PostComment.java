package to.garazuj.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name="post_comments")
@Data
@NoArgsConstructor
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String content;

    @OneToOne
    private User author;

    @OneToOne(fetch = LAZY)
    private Post post;
}
