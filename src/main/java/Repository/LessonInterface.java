package Repository;

import Entity.Lesson;

public interface LessonInterface extends BaseRepository<Lesson>{

    public Lesson findByName(String name);
}
