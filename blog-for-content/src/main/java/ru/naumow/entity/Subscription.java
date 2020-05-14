package ru.naumow.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sub")
public class Subscription {

    @EmbeddedId
    private SubscriptionId subId;

    private Integer level;

}
