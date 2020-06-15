package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.DepositEntity;
import com.my.accountmanager.model.dto.DepositDTO;
import com.my.accountmanager.repository.DepositRepository;
import com.my.accountmanager.service.DepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DepositServiceImpl extends BaseCrudServiceImpl<DepositEntity, DepositRepository> implements DepositService {
    private static final Logger logger = LoggerFactory.getLogger(DepositServiceImpl.class);

    private final DepositRepository depositRepository;

    @Autowired
    public DepositServiceImpl(DepositRepository depositRepository) {
        super(depositRepository);
        this.depositRepository = depositRepository;
    }

    @Override
    public Optional<DepositDTO> getByDepositNumber(String depositNumber) {
        logger.debug(":::::Start getByDepositNumber, depositNumber: " + depositNumber);
        DepositEntity depositEntity = depositRepository.getByDepositNumber(depositNumber);
        if (depositEntity == null) {
            return Optional.empty();
        }
        logger.debug(":::::Finish getByDepositNumber");
        return Optional.of(DepositDTO.tofrom(depositEntity));
    }

    @Override
    public Optional<DepositDTO> createDeposit(DepositDTO depositDTO) {
        logger.debug(":::::Start createDeposit, depositNumber: " + depositDTO.getDepositNumber());
        DepositEntity saveDepositEntity = super.save(DepositDTO.to(depositDTO));
        if (saveDepositEntity == null) {
            return Optional.empty();
        }
        logger.debug(":::::Finish createDeposit");
        return Optional.of(DepositDTO.tofrom(saveDepositEntity));
    }

    @Override
    public Optional<DepositDTO> updateDeposit(DepositDTO depositDTO) {
        logger.debug(":::::Start updateDeposit, depositNumber: " + depositDTO.getDepositNumber());
        DepositEntity depositEntity = depositRepository.getByDepositNumber(depositDTO.getDepositNumber());
        if (depositEntity == null) {
           return Optional.empty();
        }
        depositEntity.setIsDefault(depositDTO.getIsDefault());
        depositEntity.setIsActive(depositDTO.getIsActive());
        depositEntity.setDepositNumber(depositDTO.getDepositNumber());
        save(depositEntity);
        logger.debug(":::::Finish updateDeposit");
        return Optional.of(DepositDTO.tofrom(depositEntity));
    }

    @Override
    public void deleteDeposit(String depositNumber) {
        logger.debug(":::::Start deleteDeposit, depositNumber: " + depositNumber);
        DepositEntity depositEntity = depositRepository.getByDepositNumber(depositNumber);
        if (depositEntity != null && depositEntity.getTransaction() != null) {
            delete(depositEntity);
        }
        logger.debug(":::::Finish deleteDeposit");
    }
}
