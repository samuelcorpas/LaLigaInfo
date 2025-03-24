--
-- PostgreSQL database dump
--

-- Dumped from database version 17.1 (Debian 17.1-1.pgdg120+1)
-- Dumped by pg_dump version 17.1 (Debian 17.1-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- Name: actualidad; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.actualidad (
    puntos integer,
    goles_favor integer,
    goles_contra integer,
    jornada integer NOT NULL,
    equipo character varying(50) NOT NULL
);


ALTER TABLE public.actualidad OWNER TO usuario;

--
-- Name: administrador; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.administrador (
    nombre character varying(20) NOT NULL,
    clave character varying(20)
);


ALTER TABLE public.administrador OWNER TO usuario;

--
-- Name: comentario; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.comentario (
    usuario character varying(50) NOT NULL,
    mensaje character varying(100) NOT NULL
);


ALTER TABLE public.comentario OWNER TO usuario;

--
-- Name: equipo; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.equipo (
    nombre character varying(50) NOT NULL,
    estadio character varying(50),
    escudo character varying(100)
);


ALTER TABLE public.equipo OWNER TO usuario;

--
-- Name: equipo_votado; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.equipo_votado (
    jornada integer NOT NULL,
    equipo character varying(50) NOT NULL,
    votos integer
);


ALTER TABLE public.equipo_votado OWNER TO usuario;

--
-- Name: jornada; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.jornada (
    numero integer NOT NULL
);


ALTER TABLE public.jornada OWNER TO usuario;

--
-- Name: jugador_votado; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.jugador_votado (
    jornada integer NOT NULL,
    jugador integer NOT NULL,
    votos integer
);


ALTER TABLE public.jugador_votado OWNER TO usuario;

--
-- Name: jugadores; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.jugadores (
    id_jugador integer NOT NULL,
    nombre character varying(50),
    peso integer,
    altura integer,
    nacionalidad character varying(50),
    posicion character varying(20),
    equipo character varying(50),
    estado character varying(20),
    titular boolean,
    imagen character varying(100)
);


ALTER TABLE public.jugadores OWNER TO usuario;

--
-- Name: partido; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.partido (
    id_partido integer NOT NULL,
    gol_local integer,
    gol_visitante integer,
    equipocasa character varying(50),
    equipofuera character varying(50),
    jornada integer,
    dia integer,
    "año" integer,
    CONSTRAINT diferente CHECK (((equipocasa)::text <> (equipofuera)::text))
);


ALTER TABLE public.partido OWNER TO usuario;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.usuario (
    nombre character varying(50),
    contrasena character varying(50),
    correo character varying(50) NOT NULL
);


ALTER TABLE public.usuario OWNER TO usuario;

--
-- Name: votaciones; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.votaciones (
    jornada integer NOT NULL,
    usuario character varying(50) NOT NULL,
    equipo character varying(50),
    jugador integer
);


ALTER TABLE public.votaciones OWNER TO usuario;

--
-- Data for Name: actualidad; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.actualidad (puntos, goles_favor, goles_contra, jornada, equipo) FROM stdin;
\.


--
-- Data for Name: administrador; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.administrador (nombre, clave) FROM stdin;
admin	12345
\.


--
-- Data for Name: comentario; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.comentario (usuario, mensaje) FROM stdin;
\.


--
-- Data for Name: equipo; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.equipo (nombre, estadio, escudo) FROM stdin;
Real Madrid	Santiago Bernabéu	/images/Real-madrid.webp
FC Barcelona	Camp Nou	/images/Barcelona.webp
Atlético de Madrid	Cívitas Metropolitano	/images/club-atletico-de-madrid.webp
Sevilla FC	Ramón Sánchez-Pizjuán	/images/Sevilla.webp
Real Betis	Benito Villamarín	/images/Betis.webp
Valencia CF	Mestalla	/images/Valencia.webp
Villarreal CF	Estadio de la Cerámica	/images/Villarreal.webp
Real Sociedad	Reale Arena	/images/Real-sociedad.webp
Athletic Club	San Mamés	/images/escudoAthletic.webp
RCD Mallorca	Son Moix	/images/Mallorca.webp
Celta de Vigo	Balaídos	/images/Celta-contorno-blanco.webp
CA Osasuna	El Sadar	/images/Osasuna.webp
RCD Espanyol	RCDE Stadium	/images/rcd-espanyol-de-barcelona.webp
Getafe CF	Coliseum Alfonso Pérez	/images/Getafe.webp
Real Valladolid	José Zorrilla	/images/real-valladolid-cf.webp
Leganés	Butarque	/images/leganes-400.webp
Rayo Vallecano	Estadio de Vallecas	/images/Rayo.webp
Girona FC	Montilivi	/images/Girona.webp
UD Las Palmas	Estadio de Gran Canaria	/images/UD-Las-Palmas.webp
Deportivo Alavés	Estadio de Mendizorroza	/images/Alaves.webp
\.


--
-- Data for Name: equipo_votado; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.equipo_votado (jornada, equipo, votos) FROM stdin;
\.


--
-- Data for Name: jornada; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.jornada (numero) FROM stdin;
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
\.


--
-- Data for Name: jugador_votado; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.jugador_votado (jornada, jugador, votos) FROM stdin;
\.


--
-- Data for Name: jugadores; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.jugadores (id_jugador, nombre, peso, altura, nacionalidad, posicion, equipo, estado, titular, imagen) FROM stdin;
0	Marc-André ter Stegen	85	187	Alemania	Portero	FC Barcelona	Disponible	t	/images/jugadores/terstegen.webp
1	Ronald Araújo	88	188	Uruguay	Defensa	FC Barcelona	Disponible	t	/images/jugadores/araujo.webp
2	Jules Koundé	78	181	Francia	Defensa	FC Barcelona	Disponible	t	/images/jugadores/kounde.webp
3	Alejandro Balde	69	175	España	Defensa	FC Barcelona	Disponible	t	/images/jugadores/balde.webp
4	Dani Olmo	82	187	España	Mediocentro	FC Barcelona	Disponible	t	/images/jugadores/daniolmo.webp
5	Pau Cubarsi	85	189	España	Defensa	FC Barcelona	Lesionado	t	/images/jugadores/cubarsi.webp
6	Pedri González	60	174	España	Mediocentro	FC Barcelona	Disponible	t	/images/jugadores/pedri.webp
7	Frenkie de Jong	74	180	Países Bajos	Mediocentro	FC Barcelona	Dudoso	f	/images/jugadores/dejong.webp
8	Gavi	68	173	España	Mediocentro	FC Barcelona	Disponible	f	/images/jugadores/gavi.webp
9	Marc Casadó	79	180	España	Mediocentro	FC Barcelona	Disponible	t	/images/jugadores/casado.webp
10	Lamine Yamal	83	182	España	Delantero	FC Barcelona	Disponible	t	/images/jugadores/lamine.webp
11	Robert Lewandowski	81	185	Polonia	Delantero	FC Barcelona	Disponible	t	/images/jugadores/lewan.webp
12	Fermin Lopez	72	184	España	Meciocentro	FC Barcelona	Disponible	t	/images/jugadores/fermin.webp
13	Raphinha	71	176	Brasil	Delantero	FC Barcelona	Disponible	t	/images/jugadores/rafa.webp
14	Ansu Fati	66	178	España	Delantero	FC Barcelona	Disponible	f	/images/jugadores/ansu.webp
15	Thibaut Courtois	96	200	Bélgica	Portero	Real Madrid	Lesionado	t	/images/jugadores/courtois.webp
16	Antonio Rüdiger	85	190	Alemania	Defensa	Real Madrid	Disponible	t	/images/jugadores/rudiger.webp
17	David Alaba	78	180	Austria	Defensa	Real Madrid	Disponible	t	/images/jugadores/alaba.webp
18	Eder Militao	79	186	Brasil	Defensa	Real Madrid	Lesionado	f	/images/jugadores/militao.webp
19	Bellingham	75	186	Inglaterra	Mediocentro	Real Madrid	Disponible	t	/images/jugadores/bellingham.webp
20	Luka Modric	66	172	Croacia	Mediocentro	Real Madrid	Disponible	t	/images/jugadores/modric.webp
21	Eduardo Camavinga	68	182	Francia	Mediocentro	Real Madrid	Disponible	t	/images/jugadores/camavinga.webp
22	Federico Valverde	80	183	Uruguay	Mediocentro	Real Madrid	Disponible	t	/images/jugadores/valverde.webp
23	Vinícius Júnior	73	176	Brasil	Delantero	Real Madrid	Disponible	t	/images/jugadores/vinicius.webp
24	Rodrygo Goes	64	174	Brasil	Delantero	Real Madrid	Disponible	t	/images/jugadores/rodrygo.webp
25	Mbappé	75	178	Francia	Delantero	Real Madrid	Disponible	t	/images/jugadores/mbappe.webp
26	Fran García	71	176	España	Defensa	Real Madrid	Disponible	t	/images/jugadores/garcia.webp
27	Dani Carvajal	73	173	España	Defensa	Real Madrid	Disponible	t	/images/jugadores/carvajal.webp
28	Brahim Díaz	68	174	España	Mediocentro	Real Madrid	Disponible	f	/images/jugadores/brahim.webp
29	Jan Oblak	87	188	Eslovenia	Portero	Atlético de Madrid	Disponible	t	/images/jugadores/oblak.webp
30	José María Giménez	85	185	Uruguay	Defensa	Atlético de Madrid	Disponible	t	/images/jugadores/gimenez.webp
31	César Azpilicueta	78	178	España	Defensa	Atlético de Madrid	Disponible	t	/images/jugadores/azpilicueta.webp
32	Nahuel Molina	70	175	Argentina	Defensa	Atlético de Madrid	Disponible	t	/images/jugadores/molina.webp
33	Rodrigo de Paul	75	180	Argentina	Mediocentro	Atlético de Madrid	Disponible	t	/images/jugadores/depaul.webp
34	Koke Resurrección	77	176	España	Mediocentro	Atlético de Madrid	Lesionado	f	/images/jugadores/koke.webp
35	Marcos Llorente	74	184	España	Mediocentro	Atlético de Madrid	Disponible	t	/images/jugadores/llorente.webp
36	Samuel Lino	69	170	Brasil	Delantero	Atlético de Madrid	Disponible	t	/images/jugadores/lino.webp
37	Antoine Griezmann	73	176	Francia	Delantero	Atlético de Madrid	Disponible	t	/images/jugadores/griezmann.webp
38	Julian Alvarez	71	170	Argentina	Delantero	Atlético de Madrid	Disponible	t	/images/jugadores/alvarez.webp
39	Sorloth	90	195	Noruega	Delantero	Atlético de Madrid	Lesionado	f	/images/jugadores/sorloth.webp
40	Le Normand	80	187	España	Defensa	Atlético de Madrid	Disponible	t	/images/jugadores/lenormand.webp
41	Axel Witsel	83	186	Bélgica	Mediocentro	Atlético de Madrid	Disponible	t	/images/jugadores/witsel.webp
42	Juan Musso	93	191	Argentina	Portero	Atlético de Madrid	Disponible	f	/images/jugadores/musso.webp
43	Rui Silva	84	191	Portugal	Portero	Real Betis	Disponible	t	/images/jugadores/rui_silva.webp
44	Marc Bartra	77	183	España	Defensa	Real Betis	Disponible	t	/images/jugadores/bartra.webp
45	Héctor Bellerín	71	178	España	Defensa	Real Betis	Disponible	t	/images/jugadores/bellerin.webp
46	Ricardo Rodríguez	82	180	Suiza	Defensa	Real Betis	Disponible	t	/images/jugadores/rodriguez.webp
47	Natan	76	182	Brasil	Defensa	Real Betis	Disponible	t	/images/jugadores/natan.webp
48	William Carvalho	88	190	Portugal	Mediocentro	Real Betis	Disponible	t	/images/jugadores/carvalho.webp
49	Marc Roca	73	184	España	Mediocentro	Real Betis	Disponible	t	/images/jugadores/roca.webp
50	Pablo Fornals	75	178	España	Mediocentro	Real Betis	Disponible	t	/images/jugadores/fornals.webp
51	Vitor Roque	70	174	Brasil	Delantero	Real Betis	Disponible	t	/images/jugadores/vitor_roque.webp
52	Chimy Ávila	74	172	Argentina	Delantero	Real Betis	Disponible	t	/images/jugadores/chimy_avila.webp
53	Ez Abde	70	173	Marruecos	Delantero	Real Betis	Disponible	t	/images/jugadores/abde.webp
54	Juanmi	68	179	España	Delantero	Real Betis	Lesionado	f	/images/jugadores/juanmi.webp
55	Bakambu	75	182	RD Congo	Delantero	Real Betis	Disponible	f	/images/jugadores/bakambu.webp
56	Diao	76	180	Senegal	Delantero	Real Betis	Disponible	f	/images/jugadores/diao.webp
57	Ørjan Nyland	85	192	Noruega	Portero	Sevilla FC	Disponible	t	/images/jugadores/nyland.webp
58	Loïc Badé	85	191	Francia	Defensa	Sevilla FC	Disponible	t	/images/jugadores/bade.webp
59	Jesús Navas	63	172	España	Defensa	Sevilla FC	Disponible	t	/images/jugadores/navas.webp
60	Marcao	86	185	Brasil	Defensa	Sevilla FC	Disponible	t	/images/jugadores/marcao.webp
61	Juanlu Sánchez	70	176	España	Defensa	Sevilla FC	Disponible	t	/images/jugadores/juanlu.webp
62	Nemanja Gudelj	82	187	Serbia	Mediocentro	Sevilla FC	Disponible	t	/images/jugadores/gudelj.webp
63	Saúl Ñíguez	74	184	España	Mediocentro	Sevilla FC	Disponible	t	/images/jugadores/saul.webp
64	Lucien Agoumé	75	185	Francia	Mediocentro	Sevilla FC	Disponible	t	/images/jugadores/agoume.webp
65	Dodi Lukébakio	75	187	Bélgica	Delantero	Sevilla FC	Disponible	t	/images/jugadores/lukebakio.webp
66	Kelechi Iheanacho	82	185	Nigeria	Delantero	Sevilla FC	Disponible	t	/images/jugadores/iheanacho.webp
67	Suso	70	176	España	Delantero	Sevilla FC	Disponible	t	/images/jugadores/suso.webp
68	Alberto Flores	80	190	España	Portero	Sevilla FC	Disponible	f	/images/jugadores/usuario.webp
69	Kike Salas	79	183	España	Defensa	Sevilla FC	Disponible	f	/images/jugadores/salas.webp
70	Sambi Lokonga	72	183	Bélgica	Mediocentro	Sevilla FC	Disponible	f	/images/jugadores/lokonga.webp
71	Álex Remiro	82	192	España	Portero	Real Sociedad	Disponible	t	/images/jugadores/remiro.webp
72	Nayef Aguerd	76	190	Marruecos	Defensa	Real Sociedad	Disponible	t	/images/jugadores/aguerd.webp
73	Hamari Traoré	64	175	Mali	Defensa	Real Sociedad	Disponible	t	/images/jugadores/traore.webp
74	Javi López	80	184	España	Defensa	Real Sociedad	Disponible	t	/images/jugadores/jlopez.webp
75	Aritz Elustondo	78	180	España	Defensa	Real Sociedad	Disponible	t	/images/jugadores/elustondo.webp
76	Martín Zubimendi	78	181	España	Mediocentro	Real Sociedad	Disponible	t	/images/jugadores/zubimendi.webp
77	Beñat Turrientes	70	181	España	Mediocentro	Real Sociedad	Disponible	t	/images/jugadores/turrientes.webp
78	Brais Méndez	72	184	España	Mediocentro	Real Sociedad	Disponible	t	/images/jugadores/mendez.webp
79	Takefusa Kubo	67	173	Japón	Delantero	Real Sociedad	Disponible	t	/images/jugadores/kubo.webp
80	Mikel Oyarzabal	78	181	España	Delantero	Real Sociedad	Disponible	t	/images/jugadores/oyarzabal.webp
81	Umar Sadiq	75	192	Nigeria	Delantero	Real Sociedad	Disponible	t	/images/jugadores/sadiq.webp
82	Ander Barrenetxea	74	178	España	Delantero	Real Sociedad	Disponible	f	/images/jugadores/barrenetxea.webp
83	Igor Zubeldia	80	181	España	Defensa	Real Sociedad	Disponible	f	/images/jugadores/zubeldia.webp
84	Luka Sucic	71	185	Croacia	Mediocentro	Real Sociedad	Disponible	f	/images/jugadores/sucic.webp
85	Diego Conde	80	188	España	Portero	Villarreal CF	Disponible	t	/images/jugadores/diego_conde.webp
86	Raúl Albiol	82	190	España	Defensa	Villarreal CF	Disponible	t	/images/jugadores/raul_albiol.webp
87	Kiko Femenía	69	176	España	Defensa	Villarreal CF	Disponible	t	/images/jugadores/kiko_femenia.webp
88	Sergi Cardona	77	185	España	Defensa	Villarreal CF	Disponible	t	/images/jugadores/cardona.webp
89	Eric Bailly	77	187	Costa de Marfil	Defensa	Villarreal CF	Disponible	t	/images/jugadores/bailly.webp
90	Dani Parejo	74	182	España	Mediocentro	Villarreal CF	Disponible	t	/images/jugadores/parejo.webp
91	Alex Baena	70	175	España	Mediocentro	Villarreal CF	Disponible	t	/images/jugadores/baena.webp
92	Ramón Terrats	73	185	España	Mediocentro	Villarreal CF	Disponible	t	/images/jugadores/terrats.webp
93	Gerard Moreno	79	180	España	Delantero	Villarreal CF	Disponible	t	/images/jugadores/gerard_moreno.webp
94	Ayoze Pérez	66	178	España	Delantero	Villarreal CF	Disponible	t	/images/jugadores/ayoze.webp
95	Yeremy Pino	65	172	España	Delantero	Villarreal CF	Disponible	t	/images/jugadores/yeremy_pino.webp
96	Júnior R.	88	194	Brasil	Portero	Villarreal CF	Disponible	f	/images/jugadores/usuario.webp
97	Juan Foyth	83	187	Argentina	Defensa	Villarreal CF	Disponible	f	/images/jugadores/foyth.webp
98	Nicolas Pépé	73	183	Costa de Marfil	Mediocentro	Villarreal CF	Disponible	f	/images/jugadores/pepe.webp
99	Giorgi Mamardashvili	88	199	Georgia	Portero	Valencia CF	Disponible	t	/images/jugadores/mamardashvili.webp
100	Cristhian Mosquera	78	188	España	Defensa	Valencia CF	Disponible	t	/images/jugadores/mosquera.webp
101	Thierry Correia	65	176	Portugal	Defensa	Valencia CF	Disponible	t	/images/jugadores/correia.webp
102	José Gayà	66	172	España	Defensa	Valencia CF	Disponible	t	/images/jugadores/gaya.webp
103	Hugo Guillamón	62	178	España	Defensa	Valencia CF	Disponible	t	/images/jugadores/guillamon.webp
104	Pepelu	74	186	España	Mediocentro	Valencia CF	Disponible	t	/images/jugadores/pepelu.webp
105	Javi Guerra	70	180	España	Mediocentro	Valencia CF	Disponible	t	/images/jugadores/guerra.webp
106	André Almeida	73	183	Portugal	Mediocentro	Valencia CF	Disponible	t	/images/jugadores/almeida.webp
107	Diego López	68	177	España	Delantero	Valencia CF	Disponible	t	/images/jugadores/diego_lopez.webp
108	Hugo Duro	71	178	España	Delantero	Valencia CF	Disponible	t	/images/jugadores/duro.webp
109	Fran Pérez	70	180	España	Delantero	Valencia CF	Disponible	t	/images/jugadores/fran_perez.webp
110	Cristian Rivero	80	190	España	Portero	Valencia CF	Disponible	f	/images/jugadores/usuario.webp
111	Dimitri Foulquier	78	183	Francia	Defensa	Valencia CF	Disponible	f	/images/jugadores/foulquier.webp
112	Luis Rioja	68	175	Españas	Mediocentro	Valencia CF	Disponible	f	/images/jugadores/rioja.webp
113	Unai Simón	88	190	España	Portero	Athletic Club	Disponible	t	/images/jugadores/unai_simon.webp
114	Yeray Álvarez	81	183	España	Defensa	Athletic Club	Disponible	t	/images/jugadores/yeray_alvarez.webp
115	Óscar de Marcos	77	182	España	Defensa	Athletic Club	Disponible	t	/images/jugadores/oscar_de_marcos.webp
116	Yuri Berchiche	73	181	España	Defensa	Athletic Club	Disponible	t	/images/jugadores/yuri_berchiche.webp
117	Dani Vivian	78	185	España	Defensa	Athletic Club	Disponible	t	/images/jugadores/dani_vivian.webp
118	Mikel Vesga	80	191	España	Mediocentro	Athletic Club	Disponible	t	/images/jugadores/mikel_vesga.webp
119	Oihan Sancet	77	187	España	Mediocentro	Athletic Club	Disponible	t	/images/jugadores/oihan_sancet.webp
120	Beñat Prados	72	180	España	Mediocentro	Athletic Club	Disponible	t	/images/jugadores/benat_prados.webp
182	Omar Alderete	80	188	Paraguay	Defensa	Getafe CF	Disponible	f	/images/jugadores/alderete.webp
121	Gorka Guruzeta	80	188	España	Delantero	Athletic Club	Disponible	t	/images/jugadores/gorka_guruzeta.webp
122	Iñaki Williams	78	186	España	Delantero	Athletic Club	Disponible	t	/images/jugadores/inaki_williams.webp
123	Nico Williams	69	181	España	Delantero	Athletic Club	Disponible	t	/images/jugadores/nico_williams.webp
124	Julen Agirrezabala	75	187	España	Portero	Athletic Club	Disponible	f	/images/jugadores/julen_agirrezabala.webp
125	Iñigo Lekue	70	180	España	Defensa	Athletic Club	Disponible	f	/images/jugadores/inigo_lekue.webp
126	Ander Herrera	73	182	España	Mediocentro	Athletic Club	Disponible	f	/images/jugadores/ander_herrera.webp
127	Iván Villar	82	187	España	Portero	Celta de Vigo	Disponible	t	/images/jugadores/villar.webp
128	Joseph Aidoo	80	183	Ghana	Defensa	Celta de Vigo	Disponible	t	/images/jugadores/aidoo.webp
129	Óscar Mingueza	73	184	España	Defensa	Celta de Vigo	Disponible	t	/images/jugadores/mingueza.webp
130	Marcos Alonso	82	189	España	Defensa	Celta de Vigo	Disponible	t	/images/jugadores/alonso.webp
131	Carl Starfelt	81	185	Suecia	Defensa	Celta de Vigo	Disponible	t	/images/jugadores/starfelt.webp
132	Luca de la Torre	68	177	Estados Unidos	Mediocentro	Celta de Vigo	Disponible	t	/images/jugadores/delatorre.webp
133	Fran Beltrán	66	170	España	Mediocentro	Celta de Vigo	Disponible	t	/images/jugadores/beltran.webp
134	Jonathan Bamba	73	175	Francia	Mediocentro	Celta de Vigo	Disponible	t	/images/jugadores/bamba.webp
135	Iago Aspas	67	176	España	Delantero	Celta de Vigo	Disponible	t	/images/jugadores/aspas.webp
136	Borja Iglesias	86	187	España	Delantero	Celta de Vigo	Disponible	t	/images/jugadores/iglesias.webp
137	Anastasios Douvikas	74	183	Grecia	Delantero	Celta de Vigo	Disponible	t	/images/jugadores/douvikas.webp
138	Vicente Guaita	80	190	España	Portero	Celta de Vigo	Disponible	f	/images/jugadores/guaita.webp
139	Carlos Domínguez	81	187	España	Defensa	Celta de Vigo	Disponible	f	/images/jugadores/cdominguez.webp
140	Ilaix Moriba	73	185	Guinea	Mediocentro	Celta de Vigo	Disponible	f	/images/jugadores/moriba.webp
141	Fernando Pacheco	81	186	España	Portero	RCD Espanyol	Disponible	f	/images/jugadores/fpacheco.webp
142	Sergi Gómez	78	185	España	Defensa	RCD Espanyol	Disponible	t	/images/jugadores/sergi_gomez.webp
143	Omar El Hilali	75	183	Marruecos	Defensa	RCD Espanyol	Disponible	t	/images/jugadores/el_hilali.webp
144	Brian Oliván	74	175	España	Defensa	RCD Espanyol	Disponible	t	/images/jugadores/olivan.webp
145	Leandro Cabrera	90	190	Uruguay	Defensa	RCD Espanyol	Disponible	t	/images/jugadores/cabrera.webp
146	Alex Kral	80	187	República Checa	Mediocentro	RCD Espanyol	Disponible	t	/images/jugadores/kral.webp
147	Álvaro Aguado	73	180	España	Mediocentro	RCD Espanyol	Disponible	t	/images/jugadores/aguado.webp
148	Pol Lozano	62	177	España	Mediocentro	RCD Espanyol	Disponible	t	/images/jugadores/lozano.webp
149	Javi Puado	74	177	España	Delantero	RCD Espanyol	Disponible	t	/images/jugadores/puado.webp
150	Jofre Carreras	68	175	España	Delantero	RCD Espanyol	Disponible	t	/images/jugadores/jofre.webp
151	Alejo Véliz	77	186	Argentina	Delantero	RCD Espanyol	Disponible	t	/images/jugadores/veliz.webp
152	Joan García	75	185	España	Portero	RCD Espanyol	Disponible	t	/images/jugadores/joan_garcia.webp
153	Fernando Calero	76	183	España	Defensa	RCD Espanyol	Disponible	f	/images/jugadores/calero.webp
154	Edu Expósito	72	178	España	Mediocentro	RCD Espanyol	Disponible	f	/images/jugadores/exposito.webp
155	Sergio Herrera	85	192	España	Portero	CA Osasuna	Disponible	t	/images/jugadores/sherrera.webp
156	Alejandro Catena	83	194	España	Defensa	CA Osasuna	Disponible	t	/images/jugadores/catena.webp
157	Rubén Peña	68	170	España	Defensa	CA Osasuna	Disponible	t	/images/jugadores/ruben_pena.webp
158	J. Cruz	75	182	España	Defensa	CA Osasuna	Disponible	t	/images/jugadores/jcruzOsa.webp
159	Enzo Boyomo	80	186	Camerún	Defensa	CA Osasuna	Disponible	t	/images/jugadores/boyomo.webp
160	Lucas Torró	80	190	España	Mediocentro	CA Osasuna	Disponible	t	/images/jugadores/torro.webp
161	Moi Gómez	70	176	España	Mediocentro	CA Osasuna	Disponible	t	/images/jugadores/moi_gomez.webp
162	Aimar Oroz	68	174	España	Mediocentro	CA Osasuna	Disponible	t	/images/jugadores/oroz.webp
163	Bryan Zaragoza	61	164	España	Delantero	CA Osasuna	Disponible	t	/images/jugadores/zaragoza.webp
164	Ante Budimir	75	190	Croacia	Delantero	CA Osasuna	Disponible	t	/images/jugadores/budimir.webp
165	Kike Barja	69	175	España	Delantero	CA Osasuna	Disponible	t	/images/jugadores/barja.webp
166	Aitor Fernández	78	182	España	Portero	CA Osasuna	Disponible	f	/images/jugadores/afernandez.webp
167	Unai García	80	183	España	Defensa	CA Osasuna	Disponible	f	/images/jugadores/unai_garcia.webp
168	Rubén García	72	171	España	Delantero	CA Osasuna	Disponible	f	/images/jugadores/ruben_garcia.webp
169	David Soria	84	192	España	Portero	Getafe CF	Disponible	t	/images/jugadores/soria.webp
170	Djené Dakonam	70	178	Togo	Defensa	Getafe CF	Disponible	t	/images/jugadores/djene.webp
171	Allan Nyom	81	189	Camerún	Defensa	Getafe CF	Disponible	t	/images/jugadores/nyom.webp
172	Juan Iglesias	75	183	España	Defensa	Getafe CF	Disponible	t	/images/jugadores/jiglesias.webp
173	Domingos Duarte	78	192	Portugal	Defensa	Getafe CF	Disponible	t	/images/jugadores/duarte.webp
174	Mauro Arambarri	73	174	Uruguay	Mediocentro	Getafe CF	Disponible	t	/images/jugadores/arambarri.webp
175	Luis Milla	68	175	España	Mediocentro	Getafe CF	Disponible	t	/images/jugadores/milla.webp
176	Carles Aleñá	73	180	España	Mediocentro	Getafe CF	Disponible	t	/images/jugadores/alena.webp
177	Álvaro Rodríguez	81	192	España	Delantero	Getafe CF	Disponible	t	/images/jugadores/arodriguez.webp
178	Borja Mayoral	74	182	España	Delantero	Getafe CF	Disponible	t	/images/jugadores/mayoral.webp
179	Carles Pérez	75	173	España	Delantero	Getafe CF	Disponible	t	/images/jugadores/carles_perez.webp
180	Christantus Uche	84	190	Nigeria	Mediocentro	Getafe CF	Disponible	f	/images/jugadores/uche.webp
181	Bertug Yildirim	83	191	Turquía	Delantero	Getafe CF	Disponible	f	/images/jugadores/yildirim.webp
183	Augusto Batalla	87	186	Argentina	Portero	Rayo Vallecano	Disponible	t	/images/jugadores/batalla.webp
185	Florian Lejeune	82	188	Francia	Defensa	Rayo Vallecano	Disponible	t	/images/jugadores/lejeune.webp
184	Iván Balliu	70	172	Albania	Defensa	Rayo Vallecano	Disponible	t	/images/jugadores/balliu.webp
187	Pacha Espino	70	175	Uruguay	Defensa	Rayo Vallecano	Disponible	t	/images/jugadores/espino.webp
186	Abdul Mumin	78	188	Ghana	Defensa	Rayo Vallecano	Disponible	t	/images/jugadores/mumin.webp
188	Óscar Valentín	70	175	España	Mediocentro	Rayo Vallecano	Disponible	t	/images/jugadores/valentin.webp
189	James Rodríguez	75	180	Colombia	Mediocentro	Rayo Vallecano	Disponible	t	/images/jugadores/james.webp
190	Unai López	69	170	España	Mediocentro	Rayo Vallecano	Disponible	t	/images/jugadores/unai_lopez.webp
191	Isi Palazón	70	169	España	Delantero	Rayo Vallecano	Disponible	t	/images/jugadores/palazon.webp
192	Sergio Camello	70	177	España	Delantero	Rayo Vallecano	Disponible	t	/images/jugadores/camello.webp
193	Álvaro García	68	173	España	Delantero	Rayo Vallecano	Disponible	t	/images/jugadores/alvaro_garcia.webp
194	Aridane Hernández	83	187	España	Defensa	Rayo Vallecano	Disponible	f	/images/jugadores/aridane.webp
195	Jorge de Frutos	75	184	España	Mediocentro	Rayo Vallecano	Disponible	f	/images/jugadores/defrutos.webp
196	Embarba	71	174	España	Delantero	Rayo Vallecano	Disponible	f	/images/jugadores/embarba.webp
197	Leo Román	82	192	España	Portero	RCD Mallorca	Disponible	t	/images/jugadores/roman.webp
198	Martin Valjent	83	190	Eslovaquia	Defensa	RCD Mallorca	Disponible	t	/images/jugadores/valjent.webp
199	Pablo Maffeo	70	173	España	Defensa	RCD Mallorca	Disponible	t	/images/jugadores/maffeo.webp
200	Toni Lato	67	171	España	Defensa	RCD Mallorca	Disponible	t	/images/jugadores/lato.webp
201	Antonio Raíllo	80	187	España	Defensa	RCD Mallorca	Disponible	t	/images/jugadores/raillo.webp
202	Dani Rodríguez	72	175	España	Mediocentro	RCD Mallorca	Disponible	t	/images/jugadores/dani_rodriguez.webp
203	Sergi Darder	73	180	España	Mediocentro	RCD Mallorca	Disponible	t	/images/jugadores/darder.webp
204	Manu Morlanes	74	178	España	Mediocentro	RCD Mallorca	Disponible	t	/images/jugadores/morlanes.webp
205	Takuma Asano	71	173	Japón	Delantero	RCD Mallorca	Disponible	t	/images/jugadores/asano.webp
206	Vedat Muriqi	94	194	Kosovo	Delantero	RCD Mallorca	Disponible	t	/images/jugadores/muriqi.webp
207	Cyle Larin	76	185	Canadá	Delantero	RCD Mallorca	Disponible	t	/images/jugadores/larin.webp
208	Dominik Greif	88	190	Eslovaquia	Portero	RCD Mallorca	Disponible	f	/images/jugadores/greif.webp
209	Samu Costa	74	177	Portugal	Defensa	RCD Mallorca	Disponible	f	/images/jugadores/samu_costa.webp
210	Abdón Prats	77	182	España	Delantero	RCD Mallorca	Disponible	f	/images/jugadores/prats.webp
211	Antonio Sivera	83	188	España	Portero	Deportivo Alavés	Disponible	t	/images/jugadores/sivera.webp
212	Abqar	80	185	Marruecos	Defensa	Deportivo Alavés	Disponible	t	/images/jugadores/abqar.webp
213	Hugo Novoa	69	182	España	Defensa	Deportivo Alavés	Disponible	t	/images/jugadores/novoa.webp
214	Nahuel Tenaglia	72	178	Argentina	Defensa	Deportivo Alavés	Disponible	t	/images/jugadores/tenaglia.webp
215	Aleksandar Sedlar	79	180	Serbia	Defensa	Deportivo Alavés	Disponible	t	/images/jugadores/sedlar.webp
216	Antonio Blanco	74	182	España	Mediocentro	Deportivo Alavés	Disponible	t	/images/jugadores/blanco.webp
217	Stoickhov	71	178	España	Mediocentro	Deportivo Alavés	Disponible	t	/images/jugadores/stoickhov.webp
218	Jon Guridi	77	184	España	Mediocentro	Deportivo Alavés	Disponible	t	/images/jugadores/guridi.webp
219	Carlos Vicente	69	175	España	Delantero	Deportivo Alavés	Disponible	t	/images/jugadores/vicente.webp
220	Kike García	83	185	España	Delantero	Deportivo Alavés	Disponible	t	/images/jugadores/kike_garcia.webp
221	Toni Martínez	84	188	España	Delantero	Deportivo Alavés	Disponible	t	/images/jugadores/toni_martinez.webp
222	Ander Guevara	74	180	España	Mediocentro	Deportivo Alavés	Disponible	f	/images/jugadores/guevara.webp
223	Asier Villalibre	80	183	España	Delantero	Deportivo Alavés	Disponible	f	/images/jugadores/villalibre.webp
224	Manu Sánchez	70	171	España	Defensa	Deportivo Alavés	Disponible	f	/images/jugadores/manu_sanchez.webp
225	Paulo Gazzaniga	90	196	Argentina	Portero	Girona FC	Disponible	t	/images/jugadores/gazzaniga.webp
226	David López	82	185	España	Defensa	Girona FC	Disponible	t	/images/jugadores/lopez.webp
227	Arnau Martínez	71	180	España	Defensa	Girona FC	Disponible	t	/images/jugadores/arnau.webp
228	Miguel Gutiérrez	67	177	España	Defensa	Girona FC	Disponible	t	/images/jugadores/gutierrez.webp
229	Alejandro Frances	70	181	España	Defensa	Girona FC	Disponible	t	/images/jugadores/frances.webp
230	Oriol Romeu	78	183	España	Mediocentro	Girona FC	Disponible	t	/images/jugadores/romeu.webp
231	Iván Martín	74	182	España	Mediocentro	Girona FC	Disponible	t	/images/jugadores/ivan_martin.webp
232	Yangel Herrera	81	184	Venezuela	Mediocentro	Girona FC	Disponible	t	/images/jugadores/yangel_herrera.webp
233	Viktor Tsygankov	70	178	Ucrania	Delantero	Girona FC	Disponible	t	/images/jugadores/tsygankov.webp
234	Cristhian Stuani	82	186	Uruguay	Delantero	Girona FC	Disponible	t	/images/jugadores/stuani.webp
235	Portu	67	174	España	Delantero	Girona FC	Disponible	t	/images/jugadores/portu.webp
236	Juan Carlos	76	187	España	Portero	Girona FC	Disponible	f	/images/jugadores/juan_carlos.webp
237	Donny van de Beek	74	184	Países Bajos	Mediocentro	Girona FC	Disponible	f	/images/jugadores/van_de_beek.webp
238	Bryan Gil	69	175	España	Delantero	Girona FC	Disponible	f	/images/jugadores/bryan_gil.webp
239	Karl Hein	85	191	Estonia	Portero	Real Valladolid	Disponible	t	/images/jugadores/hein.webp
240	Eray Cömert	80	183	Suiza	Defensa	Real Valladolid	Disponible	t	/images/jugadores/comert.webp
241	Luis Pérez	70	175	España	Defensa	Real Valladolid	Disponible	t	/images/jugadores/perez.webp
242	David Torres	74	182	España	Defensa	Real Valladolid	Disponible	t	/images/jugadores/torres.webp
243	Javi Sánchez	80	189	España	Defensa	Real Valladolid	Disponible	t	/images/jugadores/jsanchez.webp
244	Pablo Meseguer	75	184	España	Mediocentro	Real Valladolid	Disponible	t	/images/jugadores/meseguer.webp
245	Iván Sánchez	70	174	España	Mediocentro	Real Valladolid	Disponible	t	/images/jugadores/ivan_sanchez.webp
246	Kike Pérez	72	178	España	Mediocentro	Real Valladolid	Disponible	t	/images/jugadores/kike_perez.webp
247	Raúl Moro	68	169	España	Delantero	Real Valladolid	Disponible	t	/images/jugadores/moro.webp
248	Juanmi Latasa	82	188	España	Delantero	Real Valladolid	Disponible	t	/images/jugadores/latasa.webp
249	Darwin Machís	71	174	Venezuela	Delantero	Real Valladolid	Disponible	t	/images/jugadores/machis.webp
250	André Ferreira	86	193	Portugal	Portero	Real Valladolid	Disponible	f	/images/jugadores/ferreira.webp
251	Cenk Özkacar	84	190	Turquía	Defensa	Real Valladolid	Disponible	f	/images/jugadores/ozkacar.webp
252	Anuar Tuhami	73	180	España	Mediocentro	Real Valladolid	Disponible	f	/images/jugadores/anuar.webp
253	Marko Dmitrovic	91	194	Serbia	Portero	Leganés	Disponible	t	/images/jugadores/dmitrovic.webp
254	Sergio González	81	185	España	Defensa	Leganés	Disponible	t	/images/jugadores/sergio_gonzalez.webp
255	Jorge Sáenz	78	188	España	Defensa	Leganés	Disponible	t	/images/jugadores/saenz.webp
256	Valentin Rosier	73	175	Francia	Defensa	Leganés	Disponible	t	/images/jugadores/rosier.webp
257	Matija Nastasic	83	188	Serbia	Defensa	Leganés	Disponible	t	/images/jugadores/nastasic.webp
258	Renato Tapia	78	185	Perú	Mediocentro	Leganés	Disponible	t	/images/jugadores/tapia.webp
259	Oscar Rodriguez	72	174	España	Mediocentro	Leganés	Disponible	t	/images/jugadores/oscar_rodriguez.webp
260	Carlos Franquesa	70	175	España	Mediocentro	Leganés	Disponible	t	/images/jugadores/franquesa.webp
261	Juan Cruz	74	178	España	Delantero	Leganés	Disponible	t	/images/jugadores/juan_cruz.webp
262	Sebastien Haller	86	190	Costa de Marfil	Delantero	Leganés	Disponible	t	/images/jugadores/haller.webp
263	Munir El Haddadi	76	179	Marruecos	Delantero	Leganés	Disponible	t	/images/jugadores/munir.webp
264	Juan Soriano	85	192	España	Portero	Leganés	Disponible	f	/images/jugadores/soriano.webp
265	Darko Brasanac	77	180	Serbia	Mediocentro	Leganés	Disponible	f	/images/jugadores/brasanac.webp
266	Daniel Raba	71	177	España	Mediocentro	Leganés	Disponible	f	/images/jugadores/raba.webp
267	Álvaro Valles	80	186	España	Portero	UD Las Palmas	Disponible	t	/images/jugadores/valles.webp
268	Mika Marmol	74	180	España	Defensa	UD Las Palmas	Disponible	t	/images/jugadores/marmol.webp
271	Alex Suárez	72	178	España	Defensa	UD Las Palmas	Disponible	t	/images/jugadores/alex_suarez.webp
269	Álex Muñoz	72	177	España	Defensa	UD Las Palmas	Disponible	t	/images/jugadores/alex_munoz.webp
270	Herzog	78	184	Austria	Defensa	UD Las Palmas	Disponible	t	/images/jugadores/herzog.webp
272	Kirian Rodríguez	68	176	España	Mediocentro	UD Las Palmas	Disponible	t	/images/jugadores/kirian.webp
273	Enzo Loiodice	70	174	Francia	Mediocentro	UD Las Palmas	Disponible	t	/images/jugadores/loiodice.webp
274	Javi Muñoz	67	170	España	Mediocentro	UD Las Palmas	Disponible	t	/images/jugadores/javi_munoz.webp
275	Alberto Moleiro	65	172	España	Delantero	UD Las Palmas	Disponible	t	/images/jugadores/moleiro.webp
276	Sandro Ramírez	70	175	España	Delantero	UD Las Palmas	Disponible	t	/images/jugadores/ramirez.webp
277	Fabio Silva	69	175	Portugal	Delantero	UD Las Palmas	Disponible	t	/images/jugadores/fabio_silva.webp
278	Jasper Cillessen	84	185	Países Bajos	Portero	UD Las Palmas	Disponible	f	/images/jugadores/cillessen.webp
279	Marvin Park	68	178	España	Defensa	UD Las Palmas	Disponible	f	/images/jugadores/park.webp
280	Marc Cardona	73	179	España	Delantero	UD Las Palmas	Disponible	f	/images/jugadores/marc_cardona.webp
\.


--
-- Data for Name: partido; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.partido (id_partido, gol_local, gol_visitante, equipocasa, equipofuera, jornada, dia, "año") FROM stdin;
1	1	1	UD Las Palmas	RCD Mallorca	1	12	\N
2	0	0	Sevilla FC	Valencia CF	1	11	\N
3	0	2	Athletic Club	Real Madrid	1	12	\N
4	1	1	Real Sociedad	Girona FC	1	12	\N
5	3	2	Villarreal CF	Real Betis	1	13	\N
6	3	0	Atlético de Madrid	RCD Espanyol	1	14	\N
7	1	2	Getafe CF	FC Barcelona	1	13	\N
8	2	0	Real Valladolid	Deportivo Alavés	1	14	\N
9	1	1	Rayo Vallecano	Leganés	1	14	\N
10	1	0	Celta de Vigo	CA Osasuna	1	14	\N
11	1	3	Leganés	Real Madrid	2	19	\N
12	1	1	Real Sociedad	Celta de Vigo	2	19	\N
13	0	2	CA Osasuna	Athletic Club	2	19	\N
14	1	1	RCD Mallorca	Villarreal CF	2	18	\N
15	1	0	Valencia CF	UD Las Palmas	2	18	\N
16	0	0	Real Betis	Atlético de Madrid	2	20	\N
17	2	0	FC Barcelona	Real Valladolid	2	20	\N
18	4	1	Deportivo Alavés	Sevilla FC	2	21	\N
19	2	0	RCD Espanyol	Rayo Vallecano	2	21	\N
20	2	0	Girona FC	Getafe CF	2	20	\N
21	0	0	UD Las Palmas	Real Sociedad	3	27	\N
22	1	3	Valencia CF	CA Osasuna	3	27	\N
23	2	1	Celta de Vigo	Real Madrid	3	28	\N
24	0	1	Real Valladolid	Leganés	3	28	\N
25	0	0	RCD Espanyol	RCD Mallorca	3	29	\N
26	1	1	Sevilla FC	Girona FC	3	29	\N
27	3	5	Villarreal CF	FC Barcelona	3	30	\N
28	3	1	Athletic Club	Real Betis	3	30	\N
29	0	4	Getafe CF	Deportivo Alavés	3	30	\N
30	1	1	Rayo Vallecano	Atlético de Madrid	3	30	\N
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.usuario (nombre, contrasena, correo) FROM stdin;
\.


--
-- Data for Name: votaciones; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.votaciones (jornada, usuario, equipo, jugador) FROM stdin;
\.


--
-- Name: actualidad actualidad_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.actualidad
    ADD CONSTRAINT actualidad_pkey PRIMARY KEY (equipo, jornada);


--
-- Name: administrador administrador_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.administrador
    ADD CONSTRAINT administrador_pkey PRIMARY KEY (nombre);


--
-- Name: comentario comentario_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT comentario_pkey PRIMARY KEY (usuario, mensaje);


--
-- Name: equipo equipo_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.equipo
    ADD CONSTRAINT equipo_pkey PRIMARY KEY (nombre);


--
-- Name: equipo_votado equipo_votado_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.equipo_votado
    ADD CONSTRAINT equipo_votado_pkey PRIMARY KEY (equipo, jornada);


--
-- Name: jornada jornada_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.jornada
    ADD CONSTRAINT jornada_pkey PRIMARY KEY (numero);


--
-- Name: jugador_votado jugador_votado_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.jugador_votado
    ADD CONSTRAINT jugador_votado_pkey PRIMARY KEY (jugador, jornada);


--
-- Name: jugadores jugadores_nombre_key; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.jugadores
    ADD CONSTRAINT jugadores_nombre_key UNIQUE (nombre);


--
-- Name: jugadores jugadores_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.jugadores
    ADD CONSTRAINT jugadores_pkey PRIMARY KEY (id_jugador);


--
-- Name: partido partido_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.partido
    ADD CONSTRAINT partido_pkey PRIMARY KEY (id_partido);


--
-- Name: usuario usuario_nombre_key; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_nombre_key UNIQUE (nombre);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (correo);


--
-- Name: votaciones votaciones_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.votaciones
    ADD CONSTRAINT votaciones_pkey PRIMARY KEY (usuario, jornada);


--
-- Name: actualidad actualidad_equipo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.actualidad
    ADD CONSTRAINT actualidad_equipo_fkey FOREIGN KEY (equipo) REFERENCES public.equipo(nombre);


--
-- Name: actualidad actualidad_jornada_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.actualidad
    ADD CONSTRAINT actualidad_jornada_fkey FOREIGN KEY (jornada) REFERENCES public.jornada(numero);


--
-- Name: comentario comentario_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT comentario_usuario_fkey FOREIGN KEY (usuario) REFERENCES public.usuario(correo);


--
-- Name: equipo_votado equipo_votado_equipo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.equipo_votado
    ADD CONSTRAINT equipo_votado_equipo_fkey FOREIGN KEY (equipo) REFERENCES public.equipo(nombre);


--
-- Name: equipo_votado equipo_votado_jornada_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.equipo_votado
    ADD CONSTRAINT equipo_votado_jornada_fkey FOREIGN KEY (jornada) REFERENCES public.jornada(numero);


--
-- Name: jugador_votado jugador_votado_jornada_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.jugador_votado
    ADD CONSTRAINT jugador_votado_jornada_fkey FOREIGN KEY (jornada) REFERENCES public.jornada(numero);


--
-- Name: jugador_votado jugador_votado_jugador_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.jugador_votado
    ADD CONSTRAINT jugador_votado_jugador_fkey FOREIGN KEY (jugador) REFERENCES public.jugadores(id_jugador);


--
-- Name: jugadores jugadores_equipo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.jugadores
    ADD CONSTRAINT jugadores_equipo_fkey FOREIGN KEY (equipo) REFERENCES public.equipo(nombre);


--
-- Name: partido partido_equipocasa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.partido
    ADD CONSTRAINT partido_equipocasa_fkey FOREIGN KEY (equipocasa) REFERENCES public.equipo(nombre);


--
-- Name: partido partido_equipofuera_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.partido
    ADD CONSTRAINT partido_equipofuera_fkey FOREIGN KEY (equipofuera) REFERENCES public.equipo(nombre);


--
-- Name: partido partido_jornada_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.partido
    ADD CONSTRAINT partido_jornada_fkey FOREIGN KEY (jornada) REFERENCES public.jornada(numero);


--
-- Name: votaciones votaciones_equipo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.votaciones
    ADD CONSTRAINT votaciones_equipo_fkey FOREIGN KEY (equipo) REFERENCES public.equipo(nombre);


--
-- Name: votaciones votaciones_jornada_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.votaciones
    ADD CONSTRAINT votaciones_jornada_fkey FOREIGN KEY (jornada) REFERENCES public.jornada(numero);


--
-- Name: votaciones votaciones_jugador_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.votaciones
    ADD CONSTRAINT votaciones_jugador_fkey FOREIGN KEY (jugador) REFERENCES public.jugadores(id_jugador);


--
-- Name: votaciones votaciones_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.votaciones
    ADD CONSTRAINT votaciones_usuario_fkey FOREIGN KEY (usuario) REFERENCES public.usuario(correo);

INSERT INTO public.usuario (nombre, contrasena, correo) 
VALUES ('admin', '12345', 'admin');

--
-- PostgreSQL database dump complete
--

