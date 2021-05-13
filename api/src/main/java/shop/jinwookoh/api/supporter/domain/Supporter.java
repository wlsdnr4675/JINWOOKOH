package shop.jinwookoh.api.supporter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "supporters")
@NoArgsConstructor
@Data
public class Supporter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supporter_id")
    private Long supporterId;
    @Column(unique = true, nullable = false)
    private String username;
    @Size(min = 8, message = "Minimum Passsword Length: 8 characters")
    private String password;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(name = "supporter_email")
    private String supporterEmail;
    @Column(name = "supporter_number")
    private String supporterPhoneNumber;

    public String toString() {
        return username + "," + password + "," + name + "," + supporterEmail + "," + supporterPhoneNumber;
    }
}