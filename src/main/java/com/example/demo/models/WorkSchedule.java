package com.example.demo.models;

import com.example.demo.enums.WorkingDays;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class WorkSchedule extends BaseEntity {
    @ManyToOne
    @JoinColumn(
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_shop_work_schedule"),
            nullable = false
    )
    private AutoShop      shop;
//    @Column(columnDefinition = "varchar(10)[]")
    @Enumerated(EnumType.STRING)
//    @Type(
//            type = "com.vladmihalcea.hibernate.type.array.ListArrayType",
//            parameters = {
//                    @Parameter(
//                            name = ListArrayType.SQL_ARRAY_TYPE,
//                            value = "sensor_state"
//                    )
//            }
//    )
//    @Column(
//            name = "working_days",
//            columnDefinition = "working_days[]"
//    )
    private WorkingDays workingDays;
    private LocalDateTime hourBegin;
    private LocalDateTime hourEnd;
}
