package constsw.grupoum.courses.adapter.mapper.mongo;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import constsw.grupoum.courses.domain.entity.Book;
import constsw.grupoum.courses.domain.entity.BookRef;
import constsw.grupoum.courses.domain.entity.Course;
import constsw.grupoum.courses.domain.entity.CourseSyllabus;
import constsw.grupoum.courses.domain.entity.SyllabusUnit;
import constsw.grupoum.courses.domain.entity.UnitTopic;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MongoEntitiesMapper {

    MongoEntitiesMapper INSTANCE = Mappers.getMapper(MongoEntitiesMapper.class);

    constsw.grupoum.courses.adapter.entity.mongo.Book toMongoBook(Book book);

    Book toBook(constsw.grupoum.courses.adapter.entity.mongo.Book book);

    Collection<Book> toBookCollection(Collection<constsw.grupoum.courses.adapter.entity.mongo.Book> books);

    constsw.grupoum.courses.adapter.entity.mongo.BookRef toMongoBookRef(BookRef bookRef);

    BookRef toBookRef(constsw.grupoum.courses.adapter.entity.mongo.BookRef bookRef);

    constsw.grupoum.courses.adapter.entity.mongo.Course toMongoCourse(Course course);

    Course toCourse(constsw.grupoum.courses.adapter.entity.mongo.Course course);

    constsw.grupoum.courses.adapter.entity.mongo.CourseSyllabus toMongoCourseSyllabus(
            CourseSyllabus courseSyllabus);

    CourseSyllabus toCourseSyllabus(
            constsw.grupoum.courses.adapter.entity.mongo.CourseSyllabus courseSyllabus);

    constsw.grupoum.courses.adapter.entity.mongo.SyllabusUnit toMongoSyallabusUnit(
            SyllabusUnit syllabusUnit);

    SyllabusUnit toSyllabusUnit(
            constsw.grupoum.courses.adapter.entity.mongo.SyllabusUnit syllabusUnit);

    constsw.grupoum.courses.adapter.entity.mongo.UnitTopic toMongoUnitTopic(UnitTopic unitTopic);

    UnitTopic toUnitTopic(constsw.grupoum.courses.adapter.entity.mongo.UnitTopic unitTopic);

}
