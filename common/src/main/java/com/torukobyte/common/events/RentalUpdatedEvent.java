package com.torukobyte.common.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RentalUpdatedEvent {

   private String oldCarId;
   private String newCarId;
   private String message;
}
