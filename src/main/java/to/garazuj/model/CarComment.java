package to.garazuj.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name="car_comments")
@Data
@NoArgsConstructor
public class CarComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String content;

    @OneToOne
    private User author;

    @OneToOne(fetch = LAZY)
    private Car car;

    @OneToMany(fetch = LAZY)
    @JoinTable(name = "car_to_comments",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private List<CarComment> carComments = new ArrayList<>();
}
