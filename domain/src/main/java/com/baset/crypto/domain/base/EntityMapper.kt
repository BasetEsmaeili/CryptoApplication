package com.baset.crypto.domain.base

interface EntityMapper<Entity, DomainEntity> {
    fun mapToEntity(entity: DomainEntity): Entity
    fun mapFromEntity(entity: Entity): DomainEntity
}