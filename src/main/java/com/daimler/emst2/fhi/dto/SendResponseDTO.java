package com.daimler.emst2.fhi.dto;

import java.util.List;

import com.daimler.emst2.fhi.model.Protocol;

public class SendResponseDTO {
    public SendungDTO sendung;
    public List<String> errorMsgs;
    public Protocol protocol;
}
