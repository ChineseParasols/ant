package com.antexploration.ant.Interactive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRecording {

    private String heaven;//天数

    private Integer status;//0:未签到 1:已签到

}
