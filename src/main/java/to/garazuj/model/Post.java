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

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "post_comments",
    joinColumns = @JoinColumn(name = "post_id"),
    inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private List<Comment> comments;
    
    
    public void setComments(Comment comment) {
    	this.comments.add(comment);
    }
    
    public void deleteComment(Comment comment) {
    	this.comments.remove(comment);
    }
}
