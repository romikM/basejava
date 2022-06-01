create table if not exists resume
(
    uuid char
(
    36
) not null
    constraint resume_pk
    primary key,
    full_name text not null
    );

create table if not exists contact
(
    id
    serial
    constraint
    contact_pk
    primary
    key,
    type
    text
    not
    null,
    value
    text
    not
    null,
    resume_uuid
    char
(
    36
) not null
    constraint contact_resume_uuid_fk
    references resume
    on delete cascade
    );

create unique index contact_uuid_type_index
    on contact (resume_uuid, type);

create table if not exists section
(
    id
    serial
    primary
    key,
    resume_uuid
    char
(
    36
) not null references resume on delete cascade,
    type text not null,
    content text not null
    );
create unique index if not exists section_idx
    on section (resume_uuid, type);