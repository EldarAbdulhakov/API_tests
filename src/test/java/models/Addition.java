package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class Addition {

    private Integer id;

    @Builder.Default
    private String additional_info = "Дополнительные сведения";

    @Builder.Default
    private Integer additional_number = 123;

//    public Addition(String additional_info, Integer additional_number) {
//        this.additional_info = additional_info;
//        this.additional_number = additional_number;
//    }
//
//    public Addition() {
//    }
//
//    public String getAdditional_info() {
//        return additional_info;
//    }
//
//    public void setAdditional_info(String additional_info) {
//        this.additional_info = additional_info;
//    }
//
//    public Integer getAdditional_number() {
//        return additional_number;
//    }
//
//    public void setAdditional_number(Integer additional_number) {
//        this.additional_number = additional_number;
//    }
}
