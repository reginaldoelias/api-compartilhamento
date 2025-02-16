PGDMP  7                     }            sharing    17.2 (Debian 17.2-1.pgdg120+1)    17.0 *    `           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            a           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            b           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            c           1262    32779    sharing    DATABASE     r   CREATE DATABASE sharing WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';
    DROP DATABASE sharing;
                     postgres    false            �            1259    81935    a4_download_count_seq    SEQUENCE     ~   CREATE SEQUENCE public.a4_download_count_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.a4_download_count_seq;
       public               postgres    false            �            1259    114706    form_download_count_seq    SEQUENCE     �   CREATE SEQUENCE public.form_download_count_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.form_download_count_seq;
       public               postgres    false            �            1259    90134    grafica_download_count_seq    SEQUENCE     �   CREATE SEQUENCE public.grafica_download_count_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.grafica_download_count_seq;
       public               postgres    false            �            1259    139275    pd_download_count_seq    SEQUENCE     ~   CREATE SEQUENCE public.pd_download_count_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.pd_download_count_seq;
       public               postgres    false            �            1259    73743    relatorios_download_count_seq    SEQUENCE     �   CREATE SEQUENCE public.relatorios_download_count_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.relatorios_download_count_seq;
       public               postgres    false            �            1259    98339    tb_a4    TABLE     `  CREATE TABLE public.tb_a4 (
    id character varying(255) NOT NULL,
    ano character varying(255),
    created_at timestamp(6) without time zone,
    download integer,
    file character varying(255),
    filename character varying(255),
    image character varying(255),
    image_filename character varying(255),
    title character varying(255)
);
    DROP TABLE public.tb_a4;
       public         heap r       postgres    false            �            1259    114699    tb_formularios    TABLE     x  CREATE TABLE public.tb_formularios (
    id character varying(255) NOT NULL,
    codigo character varying(255),
    created_at timestamp(6) without time zone,
    download integer,
    especialidade character varying(255),
    file character varying(255),
    filename character varying(255),
    title character varying(255),
    updated_at timestamp(6) without time zone
);
 "   DROP TABLE public.tb_formularios;
       public         heap r       postgres    false            �            1259    98346 
   tb_grafica    TABLE     �  CREATE TABLE public.tb_grafica (
    id character varying(255) NOT NULL,
    created_at timestamp(6) without time zone,
    download integer,
    file_url character varying(255),
    filename character varying(255),
    image character varying(255),
    image_url character varying(255),
    tipo character varying(255),
    title character varying(255),
    categoria character varying(255)
);
    DROP TABLE public.tb_grafica;
       public         heap r       postgres    false            �            1259    98332    tb_institucional    TABLE     �  CREATE TABLE public.tb_institucional (
    id character varying(255) NOT NULL,
    ano character varying(255),
    categoria character varying(255),
    data_final date,
    data_inicial date,
    download integer,
    extension character varying(255),
    file character varying(255),
    filename character varying(255),
    tipo character varying(255),
    title character varying(255),
    created_at timestamp(6) without time zone
);
 $   DROP TABLE public.tb_institucional;
       public         heap r       postgres    false            �            1259    131083    tb_planodia    TABLE     �  CREATE TABLE public.tb_planodia (
    id character varying(255) NOT NULL,
    created_at timestamp(6) without time zone,
    download integer,
    file character varying(255),
    filename character varying(255),
    title character varying(255),
    status character varying(255),
    ano character varying(255),
    texto character varying(255),
    users_downloads character varying(255)[],
    users_sent character varying(255)[],
    enviados integer
);
    DROP TABLE public.tb_planodia;
       public         heap r       postgres    false            �            1259    122891    tb_sinalizacao    TABLE     �  CREATE TABLE public.tb_sinalizacao (
    id character varying(255) NOT NULL,
    altura character varying(255),
    codigo character varying(255),
    created_at timestamp(6) without time zone,
    download integer,
    file character varying(255),
    filename character varying(255),
    medida character varying(255),
    tipo character varying(255),
    title character varying(255),
    updated_at timestamp(6) without time zone
);
 "   DROP TABLE public.tb_sinalizacao;
       public         heap r       postgres    false            �            1259    57362    user_login_count_seq    SEQUENCE     }   CREATE SEQUENCE public.user_login_count_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.user_login_count_seq;
       public               postgres    false            �            1259    49163    tb_user    TABLE     �  CREATE TABLE public.tb_user (
    id character varying(255) NOT NULL,
    cel character varying(255),
    created_at timestamp(6) without time zone,
    email character varying(255),
    funcao character varying(255),
    login_at timestamp(6) without time zone,
    name character varying(255),
    nip character varying(255),
    om character varying(255),
    password character varying(255),
    perfil character varying(255),
    posto character varying(255),
    sigla character varying(255),
    status character varying(255),
    tel character varying(255),
    username character varying(255),
    vitrine character varying(255),
    login_count integer DEFAULT nextval('public.user_login_count_seq'::regclass),
    pd boolean,
    CONSTRAINT tb_user_perfil_check CHECK (((perfil)::text = ANY ((ARRAY['ADMINISTRADOR'::character varying, 'FORMULARIO'::character varying, 'SINALIZACAO'::character varying, 'GESTOR'::character varying])::text[])))
);
    DROP TABLE public.tb_user;
       public         heap r       postgres    false    218            �            1259    106507 	   tb_videos    TABLE     ?  CREATE TABLE public.tb_videos (
    id character varying(255) NOT NULL,
    ano character varying(255),
    content_type character varying(255),
    create_at timestamp(6) without time zone,
    download integer,
    file character varying(255),
    filename character varying(255),
    title character varying(255)
);
    DROP TABLE public.tb_videos;
       public         heap r       postgres    false            �            1259    106514    videos_download_count_seq    SEQUENCE     �   CREATE SEQUENCE public.videos_download_count_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.videos_download_count_seq;
       public               postgres    false            U          0    98339    tb_a4 
   TABLE DATA           l   COPY public.tb_a4 (id, ano, created_at, download, file, filename, image, image_filename, title) FROM stdin;
    public               postgres    false    223   �9       Y          0    114699    tb_formularios 
   TABLE DATA           |   COPY public.tb_formularios (id, codigo, created_at, download, especialidade, file, filename, title, updated_at) FROM stdin;
    public               postgres    false    227   �:       V          0    98346 
   tb_grafica 
   TABLE DATA           |   COPY public.tb_grafica (id, created_at, download, file_url, filename, image, image_url, tipo, title, categoria) FROM stdin;
    public               postgres    false    224   }<       T          0    98332    tb_institucional 
   TABLE DATA           �   COPY public.tb_institucional (id, ano, categoria, data_final, data_inicial, download, extension, file, filename, tipo, title, created_at) FROM stdin;
    public               postgres    false    222   {=       \          0    131083    tb_planodia 
   TABLE DATA           �   COPY public.tb_planodia (id, created_at, download, file, filename, title, status, ano, texto, users_downloads, users_sent, enviados) FROM stdin;
    public               postgres    false    230   S>       [          0    122891    tb_sinalizacao 
   TABLE DATA           �   COPY public.tb_sinalizacao (id, altura, codigo, created_at, download, file, filename, medida, tipo, title, updated_at) FROM stdin;
    public               postgres    false    229   �?       O          0    49163    tb_user 
   TABLE DATA           �   COPY public.tb_user (id, cel, created_at, email, funcao, login_at, name, nip, om, password, perfil, posto, sigla, status, tel, username, vitrine, login_count, pd) FROM stdin;
    public               postgres    false    217   Z@       W          0    106507 	   tb_videos 
   TABLE DATA           f   COPY public.tb_videos (id, ano, content_type, create_at, download, file, filename, title) FROM stdin;
    public               postgres    false    225   �B       d           0    0    a4_download_count_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.a4_download_count_seq', 3, true);
          public               postgres    false    220            e           0    0    form_download_count_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.form_download_count_seq', 1, false);
          public               postgres    false    228            f           0    0    grafica_download_count_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.grafica_download_count_seq', 9, true);
          public               postgres    false    221            g           0    0    pd_download_count_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.pd_download_count_seq', 7, true);
          public               postgres    false    231            h           0    0    relatorios_download_count_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.relatorios_download_count_seq', 17, true);
          public               postgres    false    219            i           0    0    user_login_count_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.user_login_count_seq', 37, true);
          public               postgres    false    218            j           0    0    videos_download_count_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.videos_download_count_seq', 1, true);
          public               postgres    false    226            �           2606    98345    tb_a4 tb_a4_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.tb_a4
    ADD CONSTRAINT tb_a4_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.tb_a4 DROP CONSTRAINT tb_a4_pkey;
       public                 postgres    false    223            �           2606    114705 "   tb_formularios tb_formularios_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.tb_formularios
    ADD CONSTRAINT tb_formularios_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.tb_formularios DROP CONSTRAINT tb_formularios_pkey;
       public                 postgres    false    227            �           2606    98352    tb_grafica tb_grafica_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.tb_grafica
    ADD CONSTRAINT tb_grafica_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.tb_grafica DROP CONSTRAINT tb_grafica_pkey;
       public                 postgres    false    224            �           2606    98338 &   tb_institucional tb_institucional_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.tb_institucional
    ADD CONSTRAINT tb_institucional_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.tb_institucional DROP CONSTRAINT tb_institucional_pkey;
       public                 postgres    false    222            �           2606    131089    tb_planodia tb_planodia_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.tb_planodia
    ADD CONSTRAINT tb_planodia_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.tb_planodia DROP CONSTRAINT tb_planodia_pkey;
       public                 postgres    false    230            �           2606    122897 "   tb_sinalizacao tb_sinalizacao_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.tb_sinalizacao
    ADD CONSTRAINT tb_sinalizacao_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.tb_sinalizacao DROP CONSTRAINT tb_sinalizacao_pkey;
       public                 postgres    false    229            �           2606    49170    tb_user tb_user_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.tb_user
    ADD CONSTRAINT tb_user_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.tb_user DROP CONSTRAINT tb_user_pkey;
       public                 postgres    false    217            �           2606    106513    tb_videos tb_videos_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.tb_videos
    ADD CONSTRAINT tb_videos_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.tb_videos DROP CONSTRAINT tb_videos_pkey;
       public                 postgres    false    225            U     x���Kn�0�9�/0f� �T]v�u���cJDLD�&�Az���"��nfa}�����P���������*�Ѷ�e
�9�P�\��Fa���4���}���*!+�B6V�oL���3u�;:P�=�Ժ4��R�w��1��b"{^>�yɁ@pK�4�*Kl�{���į����4��T(%ňО���/��F�K}ED}�LS4F��Ȫb�h�Oc:�;��T���<>J
t�ݲ�[��u�� 7�}���Tx�ؿ�����_V�on<�Y����      Y   �  x���;n�0Ekj� %R����HR�r`؝�'��&�Ri����^´����$� �R�;����\��e�JQ+�ּ� �i�[�$I7w�b���S.rnV7����P���ã���4�SS���
n
V��0��އ�n��|,{7`,~ٹ�:��"�g�b������0�࠘lO.@nW�?|�����oP~�B��	:�&�L�,j-i�d�d�`9�Ȥ��F�)s�0�c^�N9��˄M�\��Kg�ݍ~� S*���*G���y�y=_E-z��$_��+q�]�r#D�ާ=R�zN[Sw�JX�*���6e�)�dBh�.S���:�v���b�8F׺�}�_&vq�O��9@��:o��E��nN	ǽ?�����}�3帇��I�}\f��gR~�$�q�=Y�� N3�      V   �   x���AN�0E��)|�q�xl���X�����t��M�F�)�.��{��������x��Y�詃@���hˢэm@Gib2��S���'q��)յ	�2��2�4��p�e�s]���l!�x��/�x�3_/���E�a����Qo�ac��֊�.��*?�T�s��w@X-��!j]6=j�}��h�cjlB�����O��mXFx�e��پ�G�a\�=�(�U���Y�X�TUUW�&      T   �   x�}�Mj�0���)tI#�Tv�݄B��f�Q�[��r�\,q�"��݃��~򒪦�UP���꠲
����uT4"��{0�o#����@s�+��4���Xa����x��H'qN)���.׶֠�����˔8����`N�dƥpx��_sh��|j�t��k�^���=c�u�OF*�>l����8n��β���\*      \   ?  x���AN�0E��)� �c�I�Ċ
�m$4�'���v��A,�� �\'�+$k���z�[����4��zMѦ�,ʅ�(��4�+��ȃ�'i�E$2Y!�"�l�fb�Yp����c�%L�3Q�<�q'�J5��ZY�@K"8��Y��O7��������c:;n��P���2�4���O�>��`Z���0_@�QI��\����������l4�Y#�~~,�#N_�`�#6��;jh#��A�Zpfo	��Y���)�ܠ{�=��z3��&-q�TZy��*���B��-���~�s����j��H�����VA��9a��0��J��      [   �   x�}�A� D�x
/ |>�Ȯ��� b$�j�����&]����Ō����D+��1P=P����xҀ[�wY�E�}&��I�*A%J��@ sΛ�\��D����04�^6��uv�S�>q7{��!e�G�y݃�l'rA�y���Ny��.�=+��� F�      O   C  x����V�@���Sd�]L�[23���QB0AA���RQn��ⲾXA�=�sں���7��7'(�S���X�jF$�bY������Ȣ������j!���DҖ�e>-���*��t�Z�i=�z�)���-`xǄ��eR�4@�2I�\?�a�+���X=�8,�2���'D�`t�;<ܻZw����C9`�s��S��}o���]_�r�Xϼ�磺b��z�D�U�ϧ��8 j]nk��ெ�� ��^
 g��û�F�E!yfÜPY�(�IJa�ڔ%$�X��o�ŤI%�X��<��8ݖk=?�����,A������뭮���^�FT&y��F�[D�Ob�ܑ�h6pV�۰�0�������&z�fv���D���(���(��y3���#�h��`zm�-����ahS����S����8�&�GHb	7�lY�E���D����t���K��z܈���T���?����pT䄱zs��$���{{k�����nww��o�<���Nq&�}1aӛ��r�}1)/�X����Y����ꛪ
(�ƭ�h4~� i      W   �   x�m��� ���

p�OD��_P02�QcO�#�E�<�ego��ct�b�gZI�,t�U�+Q���f �&G�!�i��4�!P�׺�p��D�6d���K��r�X[� ��M�6ѿ�w>�i���5�<�?m�zz��}�[�n����VG}���\�hyQ_��C     