package org.GIT.requestPOJO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddDataRequest {
    @JsonProperty(value = "pincode")
    public String pinCode;
    @JsonProperty(value = "salary")
    public String salary;
    @JsonProperty(value = "accountno")
    public String accountNo;
    @JsonProperty(value = "departmentno")
    public String departmentNo;
}
