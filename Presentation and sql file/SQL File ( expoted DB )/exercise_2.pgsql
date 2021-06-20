--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3 (Debian 13.3-1.pgdg100+1)
-- Dumped by pg_dump version 13.3 (Debian 13.3-1.pgdg100+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: admin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admin (
    "ADMIN_userid" integer NOT NULL,
    username character varying(45) NOT NULL,
    password character varying(45) NOT NULL
);


ALTER TABLE public.admin OWNER TO postgres;

--
-- Name: appointment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.appointment (
    "appointmentID" integer NOT NULL,
    date date,
    "time" time without time zone,
    "doctorAMKA" bigint,
    doctor_name character varying(45),
    doctor_surname character varying(45),
    doctor_specialty character varying(45),
    patient_username character varying(45)
);


ALTER TABLE public.appointment OWNER TO postgres;

--
-- Name: doctor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.doctor (
    "doctorAMKA" bigint NOT NULL,
    "ADMIN_userid" integer NOT NULL,
    username character varying(45) NOT NULL,
    password character varying(45) NOT NULL,
    specialty character varying(45) NOT NULL,
    surname character varying(45),
    name character varying(45)
);


ALTER TABLE public.doctor OWNER TO postgres;

--
-- Name: patient; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.patient (
    "patientAMKA" bigint NOT NULL,
    username character varying(45) NOT NULL,
    password character varying(45) NOT NULL,
    name character varying(45) NOT NULL,
    surname character varying(45)
);


ALTER TABLE public.patient OWNER TO postgres;

--
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.admin ("ADMIN_userid", username, password) FROM stdin;
12345	1337admin	1337h4xx0r
\.


--
-- Data for Name: appointment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.appointment ("appointmentID", date, "time", "doctorAMKA", doctor_name, doctor_surname, doctor_specialty, patient_username) FROM stdin;
1	2021-06-05	11:00:00	12345678901	markos	markou	pathologist	mitsos1
2	2021-01-31	10:00:00	12345678901	markos	markou	pathologist	mitsos1
3	2021-06-13	17:00:00	12345678901	markos	markou	pathologist	mitsos1
\.


--
-- Data for Name: doctor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.doctor ("doctorAMKA", "ADMIN_userid", username, password, specialty, surname, name) FROM stdin;
12345678901	12345	markmark	mark^2	pathologist	markou	markos
\.


--
-- Data for Name: patient; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.patient ("patientAMKA", username, password, name, surname) FROM stdin;
12395323	mitsos1	mitsos516	Mitsos	Petrou
\.


--
-- Name: admin admin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY ("ADMIN_userid");


--
-- Name: appointment appointment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_pkey PRIMARY KEY ("appointmentID");


--
-- Name: doctor doctor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT doctor_pkey PRIMARY KEY ("doctorAMKA", "ADMIN_userid");


--
-- Name: patient patient_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.patient
    ADD CONSTRAINT patient_pkey PRIMARY KEY ("patientAMKA");


--
-- Name: doctor unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT "unique" UNIQUE (username);


--
-- Name: patient unique1; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.patient
    ADD CONSTRAINT unique1 UNIQUE (username);


--
-- Name: admin unique3; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT unique3 UNIQUE (username);


--
-- Name: doctor unique5; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT unique5 UNIQUE ("doctorAMKA");


--
-- Name: doctor fk3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT fk3 FOREIGN KEY ("ADMIN_userid") REFERENCES public.admin("ADMIN_userid") ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: appointment fka1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fka1 FOREIGN KEY ("doctorAMKA") REFERENCES public.doctor("doctorAMKA") ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: appointment fka2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fka2 FOREIGN KEY (patient_username) REFERENCES public.patient(username) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

