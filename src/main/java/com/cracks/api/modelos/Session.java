package com.cracks.api.modelos;



import java.time.LocalDate;
import java.util.Date;

import com.cracks.api.interfaces.SessionRol;
import com.cracks.api.interfaces.TypeLogin;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Session")
public class Session {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User users;

    @Column
    private Boolean verified=true;

    @Column()
    private String verifiedContact;
    
    @Column
    private Boolean closeAcount=false;

    @Column(nullable=false)
    private String passwrd;

    @Column()
    private boolean closeSession=false;

    @Column()
    private boolean blockAcount=false;

    @Enumerated(EnumType.STRING)
    private TypeLogin typeLogin=TypeLogin.MANUAL;

    @Enumerated(EnumType.STRING)
    private SessionRol rol;


    
    @Column
    private LocalDate reigsterDate;

    public Session(User u,String psw){
        users=u;
        passwrd=psw;
        reigsterDate=LocalDate.now();
        rol=SessionRol.USER;
    }

}
