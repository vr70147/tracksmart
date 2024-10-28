CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL CHECK (role IN ('parent', 'teacher')),
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE parents (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE teachers (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    class_id UUID,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE children (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    parent_id UUID,
    FOREIGN KEY (parent_id) REFERENCES parents(id)
);

CREATE TABLE parent_children (
    parent_id UUID,
    child_id UUID,
    PRIMARY KEY (parent_id, child_id),
    FOREIGN KEY (parent_id) REFERENCES parents(id),
    FOREIGN KEY (child_id) REFERENCES children(id)
);

CREATE TABLE classes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE class_children (
    class_id UUID,
    child_id UUID,
    PRIMARY KEY (class_id, child_id),
    FOREIGN KEY (class_id) REFERENCES classes(id),
    FOREIGN KEY (child_id) REFERENCES children(id)
);