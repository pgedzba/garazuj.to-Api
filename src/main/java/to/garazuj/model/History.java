package to.garazuj.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="history")
@Data
@NoArgsConstructor
public class History {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Car car;
    
    @ManyToOne
    private User user;
    
    @NotNull
    private Float price;
    
    @NotNull
    private Date date;
    
    @NotBlank
    private String description;
    

}
