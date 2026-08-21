package lk.rasheeda.web.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "refresh_token",
indexes = {
        @Index(columnList = "token",unique = true),
        @Index(columnList = "username")
})
@NamedQueries({
        @NamedQuery(name = "RefreshToken.findByToken",
                query = "SELECT rf FROM RefreshToken rf WHERE rf.token = :token AND rf.expireAt > :now"),

        @NamedQuery(name = "RefreshToken.deleteToken",
                query = "DELETE FROM RefreshToken rf WHERE rf.token= :token"),

        @NamedQuery(name = "RefreshToken.deleteExpiredToken",
                query = "DELETE FROM RefreshToken rf WHERE rf.expireAt < :now")
})
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;

    private String username;

    private Instant expireAt;

    private Instant createdAt = Instant.now();

    public RefreshToken() {
    }

    public RefreshToken(String token, String username, Instant expireAt) {
        this.token = token;
        this.username = username;
        this.expireAt = expireAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Instant getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Instant expireAt) {
        this.expireAt = expireAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

}
