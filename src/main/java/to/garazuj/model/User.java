package to.garazuj.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NaturalId;


import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})
@Data
@NoArgsConstructor
public class User{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private byte[] profileImage;

    @NotBlank
    @Size(min=3, max = 50)
    private String firstName;

    @NotBlank
    @Size(min=3, max = 50)
    private String lastName;
    
    @NotBlank
    @Size(min=3, max = 50)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min=6, max = 100)
    @JsonIgnore
    private String password;

    @ManyToMany
    @JoinTable(name = "user_roles", 
    	joinColumns = @JoinColumn(name = "user_id"), 
    	inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany
    @JoinTable(name = "user_cars",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    @JsonManagedReference
    private List<Car> cars = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany
    @JoinTable(name = "user_comments",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private List<Comment> comments = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany
    @JoinTable(name = "user_history",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "history_id"))
    @JsonManagedReference
    private List<History> history = new ArrayList<>();
    
    public User(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}