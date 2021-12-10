package daepoid.stockManager.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Client {

    @Id
    @GeneratedValue
    @Column(name = "client_id")
    private Long id;

    // 회사명
    private String company;

    // 담당자
    private String name;

    // 회사 번호
    private String phoneNumber;
}
