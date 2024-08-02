package com.cracks.api.modelos;




import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Data
@EqualsAndHashCode(callSuper = true)

public class OwnerInterestUser extends OwnerInterest {

    @ManyToOne
    private User user;
}
