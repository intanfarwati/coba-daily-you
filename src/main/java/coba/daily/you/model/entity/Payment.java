package coba.daily.you.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name= Payment.TABLE_NAME)
@Data
public class Payment {
    public static final String TABLE_NAME = "t_payment";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME, sequenceName = "t_payment_seq")

    @Column(name = "id_pembayaran")
    private Integer id;

    private String tanggalPembayaran;

    @Column(name = "bukti_url")
    private String file;
}

