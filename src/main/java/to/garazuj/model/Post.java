package to.garazuj.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name="posts")
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=5, max = 100)
    private String title;

    @NotBlank @Lob
    private String content;
    
    @NotBlank
    @Size(min=50, max = 500)
    private String shortDescription;

    @OneToOne
    private User author;

}
