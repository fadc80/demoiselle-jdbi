--
-- Postgres 9.5.4
-- Simple Contact List Database 
-- Initialization Script
---

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;

--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: DATABASE simple contact list;
--

DROP DATABASE IF EXISTS simple_contact_list;

CREATE DATABASE simple_contact_list;

\connect simple_contact_list

--
-- Name: contact; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE contact (
    id bigint NOT NULL,
    first_name character varying(100),
    last_name character varying(100)
);


ALTER TABLE contact OWNER TO postgres;

--
-- Name: contact_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contact_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE contact_id_seq OWNER TO postgres;

--
-- Name: contact_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contact_id_seq OWNED BY contact.id;


--
-- Name: phone_number; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE phone_number (
    contact_id bigint,
    it character varying(20)
);


ALTER TABLE phone_number OWNER TO postgres;

--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contact ALTER COLUMN id SET DEFAULT nextval('contact_id_seq'::regclass);


--
-- Data for Name: contact; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY contact (id, first_name, last_name) FROM stdin;
1	George	Washington
2	John	Adams
3	Thomas	Jefferson
\.


--
-- Name: contact_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contact_id_seq', 3, true);


--
-- Data for Name: phone_number; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY phone_number (contact_id, it) FROM stdin;
\.


--
-- Name: contact_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contact
    ADD CONSTRAINT contact_pkey PRIMARY KEY (id);


--
-- Name: phone_number_contact_it_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY phone_number
    ADD CONSTRAINT phone_number_contact_it_fkey FOREIGN KEY (contact_id) REFERENCES contact(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: contact; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE contact FROM PUBLIC;
REVOKE ALL ON TABLE contact FROM postgres;
GRANT ALL ON TABLE contact TO postgres;


--
-- Name: contact_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE contact_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE contact_id_seq FROM postgres;
GRANT ALL ON SEQUENCE contact_id_seq TO postgres;


--
-- Name: phone_number; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE phone_number FROM PUBLIC;
REVOKE ALL ON TABLE phone_number FROM postgres;
GRANT ALL ON TABLE phone_number TO postgres;


--
-- PostgreSQL database dump complete
--

