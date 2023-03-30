package trevo.voll.api.image;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_image")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Lob
    @Column(name = "image", length = 1000)
    @JsonIgnore
    private byte[] imageData;



}
