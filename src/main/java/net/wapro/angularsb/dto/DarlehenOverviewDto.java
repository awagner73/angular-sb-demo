package net.wapro.angularsb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DarlehenOverviewDto {

    private List<DarlehenDto> darlehen = new ArrayList<>();

}
