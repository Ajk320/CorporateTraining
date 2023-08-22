INSERT INTO USER (id,email, password, role)
SELECT 1, "admin@admin.com", "$2a$10$bPmKC5MhhxqVYfyKuHeD8OX0eSnSyEyleXmrVNs9CoBvPSW9JQqs2", 1 FROM DUAL WHERE
NOT EXISTS (SELECT 1 FROM user where email = "admin@admin.com");

INSERT INTO USER (id,email, password, role)
SELECT 2, "clarkkent@krypton.com", "$2a$10$.EWi5440kUJbwe6L3VpiSuIqr9kO4qIaTLMcpKce2OdFeVsKSEqXy", 0 FROM DUAL WHERE
NOT EXISTS (SELECT 1 FROM user where email = "clarkkent@krypton.com");

