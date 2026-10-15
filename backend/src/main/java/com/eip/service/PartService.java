package com.eip.service;

import com.eip.dto.request.PartRequest;
import com.eip.dto.response.PartResponse;

import java.util.List;

public interface PartService
{
    PartResponse createPart(PartRequest request);

    PartResponse getPartById(Long id);

    List<PartResponse> getAllParts();

    PartResponse updatePart(Long id, PartRequest request);

    void deletePart(Long id);
}
