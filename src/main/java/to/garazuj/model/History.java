package to.garazuj.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="history")
@Data
@NoArgsConstructor
public class History {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
	@ManyToOne
    @JoinTable(name = "car_history",
    joinColumns = @JoinColumn(name = "history_id"),
    inverseJoinColumns = @JoinColumn(name = "car_id"))
    private Car car;
    
    @ManyToOne
    @JsonIgnore
    @JoinTable(name = "user_history",
    joinColumns = @JoinColumn(name = "history_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;
    
    @NotNull
    private Float price;
    
    @NotNull
    private Date date;
    
    @NotBlank
    private String description;
    

}
