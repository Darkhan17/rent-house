package kz.kbtu.renthouse.mapper;

import javax.annotation.processing.Generated;
import kz.kbtu.renthouse.domain.dto.hobby.CreateHobbyDTO;
import kz.kbtu.renthouse.repository.entity.Hobby;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-13T00:29:20+0600",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class HobbyMapperImpl implements HobbyMapper {

    @Override
    public Hobby map(CreateHobbyDTO createHobbyDTO) {
        if ( createHobbyDTO == null ) {
            return null;
        }

        Hobby hobby = new Hobby();

        hobby.setName( createHobbyDTO.getName() );
        hobby.setDescription( createHobbyDTO.getDescription() );

        return hobby;
    }
}
