package to.garazuj.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name="profile_comments")
@Data
@NoArgsConstructor
public class ProfileComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String content;

    @OneToOne
    private User author;

    @OneToOne(fetch = LAZY)
    private User commentedProfile;
}
