package org.raikon.employee.json.address.response;

import org.raikon.employee.json.address.request.AddressRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AddressResponse extends AddressRequest {

    private static final long serialVersionUID = 3119837193500406982L;

    private Integer id;
}
