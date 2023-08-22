DELETE FROM USER WHERE email IN ("admin@admin.com", "clarkkent@krypton.com");
INSERT INTO USER (email, password, role)VALUES("admin@admin.com", "$2a$10$bPmKC5MhhxqVYfyKuHeD8OX0eSnSyEyleXmrVNs9CoBvPSW9JQqs2", 1);
INSERT INTO USER (email, password, role)VALUES("clarkkent@krypton.com", "$2a$10$.EWi5440kUJbwe6L3VpiSuIqr9kO4qIaTLMcpKce2OdFeVsKSEqXy", 0);
