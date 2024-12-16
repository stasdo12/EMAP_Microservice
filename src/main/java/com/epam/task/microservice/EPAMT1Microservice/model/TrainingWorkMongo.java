    package com.epam.task.microservice.EPAMT1Microservice.model;

    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.mapping.Document;
    import org.springframework.data.mongodb.core.mapping.Field;

    import java.util.ArrayList;
    import java.util.List;

    @Document(collection = "training_work_mongo")
    @Data
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class TrainingWorkMongo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private String id;
        private String username;
        private String firstName;
        private String lastName;
        private boolean isActive;

        @Field("years")
        private List<TrainingYearMongo> years;


        public List<TrainingYearMongo> getYears() {
            if (years == null) {
                years = new ArrayList<>();
            }
            return years;
        }

    }

