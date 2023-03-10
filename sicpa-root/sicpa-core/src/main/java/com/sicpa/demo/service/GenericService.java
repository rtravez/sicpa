package com.sicpa.demo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sicpa.demo.exception.ExceptionManager;
import com.sicpa.demo.repository.IGenericRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public abstract class GenericService<T, ID extends Serializable> implements IGenericService<T, ID> {

	protected IGenericRepository<T, ID> genericRepository;

	protected GenericService() {
	}

	protected GenericService(IGenericRepository<T, ID> genericRepository) {
		this.genericRepository = genericRepository;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = ExceptionManager.class)
	public T save(T entity) throws ExceptionManager {
		try {
			return genericRepository.save(entity);
		} catch (Exception e) {
			log.error("save: ", e);
			throw new ExceptionManager().new GettingException("Error al guardar el registro");
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = ExceptionManager.class)
	public List<T> saveAll(List<T> entities) throws ExceptionManager {
		try {
			return genericRepository.saveAll(entities);
		} catch (Exception e) {
			log.error("save: ", e);
			throw new ExceptionManager().new GettingException("Error al guardar los registros");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<T> findById(ID id) throws ExceptionManager {
		try {
			return genericRepository.findById(id);
		} catch (Exception e) {
			log.error("findById: ", e);
			throw new ExceptionManager().new FindingException("Error al buscar el registro");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsById(ID id) throws ExceptionManager {
		try {
			return genericRepository.existsById(id);
		} catch (Exception e) {
			log.error("existsById: ", e);
			throw new ExceptionManager().new FindingException("Error al buscar el registro");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll() throws ExceptionManager {
		try {
			return genericRepository.findAll();
		} catch (Exception e) {
			log.error("findAll: ", e);
			throw new ExceptionManager().new FindingException("Error al buscar los registros");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAllById(List<ID> ids) throws ExceptionManager {
		try {
			return genericRepository.findAllById(ids);
		} catch (Exception e) {
			log.error("findAllById: ", e);
			throw new ExceptionManager().new FindingException("Error al buscar los registros");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() throws ExceptionManager {
		try {
			return genericRepository.count();
		} catch (Exception e) {
			log.error("count: ", e);
			throw new ExceptionManager().new FindingException("Error al contar los registros");
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = ExceptionManager.class)
	public void deleteById(ID id) throws ExceptionManager {
		try {
			genericRepository.deleteById(id);
		} catch (Exception e) {
			log.error("deleteById: ", e);
			throw new ExceptionManager().new GettingException("Error al eliminar el registro");
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = ExceptionManager.class)
	public void delete(T entity) throws ExceptionManager {
		try {
			genericRepository.delete(entity);
		} catch (Exception e) {
			log.error("delete: ", e);
			throw new ExceptionManager().new GettingException("Error al eliminar el registro");
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = ExceptionManager.class)
	public void deleteAllById(List<ID> ids) throws ExceptionManager {
		try {
			genericRepository.deleteAllById(ids);
		} catch (Exception e) {
			log.error("deleteAllById: ", e);
			throw new ExceptionManager().new GettingException("Error al eliminar los registros");
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = ExceptionManager.class)
	public void deleteAll(List<T> entities) throws ExceptionManager {
		try {
			genericRepository.deleteAll(entities);
		} catch (Exception e) {
			log.error("deleteAll: ", e);
			throw new ExceptionManager().new GettingException("Error al eliminar los registros");
		}
	}
}
