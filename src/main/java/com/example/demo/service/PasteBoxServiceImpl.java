package com.example.demo.service;

import com.example.demo.api.request.PasteBoxRequest;
import com.example.demo.api.request.PublicStatus;
import com.example.demo.api.response.PasteBoxResponse;
import com.example.demo.api.response.PasteBoxUrlResponse;
import com.example.demo.config.Config;
import com.example.demo.repository.PasteBoxEntity;
import com.example.demo.repository.PasteBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PasteBoxServiceImpl implements PasteBoxService{
    @Autowired
    private Config config;
    @Autowired
    private final PasteBoxRepository repository;

    public PasteBoxServiceImpl(PasteBoxRepository repository) {
        this.repository = repository;
    }

    @Override
    public PasteBoxResponse getByHash(String hash) {
        PasteBoxEntity pasteBoxEntity = repository.findByHash(hash).get();

        return new PasteBoxResponse(pasteBoxEntity.getData(), pasteBoxEntity.isPublic() ? PublicStatus.PUBLIC : PublicStatus.UNLISTED);
    }

    @Override
    public List<PasteBoxResponse> getFirstPublicPasteBoxes() {
        return repository.findByIsPublicTrueAndExpirationTimeAfterOrderByIdDesc(LocalDateTime.now(), PageRequest.of(0, config.publicListSize())).stream()
                .map(entity -> new PasteBoxResponse(entity.getData(), entity.isPublic() ? PublicStatus.PUBLIC : PublicStatus.UNLISTED))
                .toList();
    }

    @Override
    public PasteBoxUrlResponse create(PasteBoxRequest request) {
        PasteBoxEntity pasteBoxEntity = new PasteBoxEntity(
                request.getData(),
                LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()),
                request.getPublicStatus() == PublicStatus.PUBLIC);
        repository.save(pasteBoxEntity);
        return new PasteBoxUrlResponse(pasteBoxEntity.getHash());
    }
    @Transactional
    public PasteBoxEntity createPaste(String data, LocalDateTime expirationTime, boolean isPublic) {
        PasteBoxEntity paste = new PasteBoxEntity(data, expirationTime, isPublic);
        paste = repository.save(paste);
        paste.setHash(Long.toHexString(paste.getId()));
        return repository.save(paste);
    }
}
