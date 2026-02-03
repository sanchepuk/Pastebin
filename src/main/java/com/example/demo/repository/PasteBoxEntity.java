package com.example.demo.repository;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "paste")
public class PasteBoxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "data", nullable = false)
    String data;
    @Column(name = "hash", unique = true)
    String hash;
    @Column(name = "expirationTime", nullable = false)
    LocalDateTime expirationTime;
    @Column(name = "isPublic", nullable = false)
    boolean isPublic;

    public PasteBoxEntity() {
    }

    public PasteBoxEntity(String data, LocalDateTime expirationTime, boolean isPublic) {
        this.id = null;
        this.data = data;
        this.hash = null;
        this.expirationTime = expirationTime;
        this.isPublic = isPublic;
    }

    public PasteBoxEntity(Long id, String data, String hash, LocalDateTime expirationTime, boolean isPublic) {
        this.id = id;
        this.data = data;
        this.hash = hash;
        this.expirationTime = expirationTime;
        this.isPublic = isPublic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
    @PostPersist
    public void generateHash() {
        this.hash = Long.toHexString(this.id);
    }
}
