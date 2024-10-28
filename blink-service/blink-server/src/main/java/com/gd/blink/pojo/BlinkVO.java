package com.gd.blink.pojo;


import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gd.user.dto.UserDTO;
import lombok.Data;


@Data
public class BlinkVO {

	@JsonUnwrapped
	BlinkView blink;

	UserDTO user;

}
