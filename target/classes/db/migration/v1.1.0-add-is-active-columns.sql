-- ======================================
-- Changeset 20250823-1 : Ajout colonnes manquantes
-- ======================================

ALTER TABLE book_loans ADD COLUMN is_active BOOLEAN DEFAULT TRUE NOT NULL;
ALTER TABLE cities ADD COLUMN is_active BOOLEAN DEFAULT TRUE NOT NULL;
ALTER TABLE libraries ADD COLUMN is_active BOOLEAN DEFAULT TRUE NOT NULL;

ALTER TABLE permissions ADD COLUMN code VARCHAR(20) NOT NULL;
ALTER TABLE permissions ADD COLUMN is_active BOOLEAN DEFAULT TRUE NOT NULL;

ALTER TABLE permissions DROP CONSTRAINT IF EXISTS UK7lcb6glmvwlro3p2w2cewxtvd;
ALTER TABLE permissions ADD CONSTRAINT UK7lcb6glmvwlro3p2w2cewxtvd UNIQUE(code);
