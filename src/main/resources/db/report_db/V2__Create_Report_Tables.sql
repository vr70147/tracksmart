CREATE TABLE daily_reports (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    child_id UUID NOT NULL,
    teacher_id UUID,
    date DATE NOT NULL,
    activities TEXT,
    behavior_notes TEXT,
    FOREIGN KEY (child_id) REFERENCES children(id),
    FOREIGN KEY (teacher_id) REFERENCES teachers(id)
);

CREATE TABLE progress_reports (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    child_id UUID NOT NULL,
    skill VARCHAR(255),
    level INTEGER CHECK (level BETWEEN 1 AND 5),
    comments TEXT,
    date_assessed DATE NOT NULL,
    FOREIGN KEY (child_id) REFERENCES children(id)
);