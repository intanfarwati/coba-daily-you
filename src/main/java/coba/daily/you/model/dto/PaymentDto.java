package coba.daily.you.model.dto;

import lombok.Data;

@Data
public class PaymentDto {
    private Integer id;
    private Integer idCustomer;
    private Integer tanggalUpload;
    private String file;
    private String statusPembayaran;

}