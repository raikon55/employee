package br.com.mundiale.employee.json.address.request;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class AddressRequest implements Serializable {

    private static final long serialVersionUID = -7703398584186862334L;

    @Pattern(regexp = "\\d+")
    private Short number;

    @Pattern(regexp = "\\d{8}")
    private String zipCode;

    private String city;
    private String state;
    private String street;
    private String neighborhood;
}