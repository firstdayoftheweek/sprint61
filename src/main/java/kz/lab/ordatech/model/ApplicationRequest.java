package kz.lab.ordatech.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_requests")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String userName;

    private String courseName;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String commentary;

    @Column(name = "phoneNumber")
    private String phone;

    @Column(name = "handled")
    private boolean handled;
}
