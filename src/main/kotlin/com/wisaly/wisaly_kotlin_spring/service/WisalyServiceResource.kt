package com.wisaly.wisaly_kotlin_spring.service

import com.wisaly.wisaly_kotlin_spring.dtos.modelDto.WisalyServiceDto
import com.wisaly.wisaly_kotlin_spring.models.businessPages.ServicePlan
import com.wisaly.wisaly_kotlin_spring.repository.UserRepository
import com.wisaly.wisaly_kotlin_spring.repository.WisalyService.ServicePlanRepository
import com.wisaly.wisaly_kotlin_spring.repository.WisalyService.WisalyServiceRepository
import com.wisaly.wisaly_kotlin_spring.utils.objectMappers.WisalyServiceMapper
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class WisalyServiceResource(
    private val userRepository: UserRepository,
    private val wisalyServiceMapper: WisalyServiceMapper,
    private val wisalyServiceRepository: WisalyServiceRepository,
    private val servicePlanRepository: ServicePlanRepository
) {

    fun addService(service: WisalyServiceDto, user_id: Long) {
        val author = userRepository.findById(user_id).orElse(null)
        service.businessName = author
        val serviceEntity = wisalyServiceMapper.toEntity(service)

        //TODO

    }

    fun addPlans(serviceId: Long, user_id: Long, plans: List<ServicePlan>):WisalyServiceDto {
        val res = servicePlanRepository.saveAll(plans);
        val myService = wisalyServiceRepository.findById(serviceId).orElse(null)
        res.forEach { myService.plans.add(it) }
        val sendRes = wisalyServiceRepository.save(myService);
        return wisalyServiceMapper.fromEntity(sendRes);
    }

    fun getAllServices(size:Int, page:Int):List<WisalyServiceDto>{
        val getAll = wisalyServiceRepository.findAll(PageRequest.of(page,size));
        val allRes = mutableListOf<WisalyServiceDto>()
        getAll.forEach{allRes.add(wisalyServiceMapper.basicFromEntity(it))}
        return allRes;
    }


    fun getService(serviceId:Long, userId:Long):WisalyServiceDto {
            val res = wisalyServiceRepository.findById(serviceId).orElseGet(null)
            return wisalyServiceMapper.fromEntity(res);
    }
}