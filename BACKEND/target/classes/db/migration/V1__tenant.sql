create table if not exists public.categorie
(
    id      bigserial
        primary key,
    libelle text
);

create table if not exists public.article
(
    article_id    bigserial
        primary key,
    designation   text,
    image         oid,
    is_perishable boolean,
    status        varchar(15),
    categorie_id  bigint not null
        constraint article_categorie_key_constraint
            references public.categorie
);

create table if not exists public.fonction
(
    id           bigserial
        primary key,
    default_page integer,
    nom_fonction text
);

create table if not exists public.fonctionnalite
(
    id  bigserial
        primary key,
    nom text
);

create table if not exists public.fonction_autorisation
(
    fonction_id          bigint not null
        constraint fkq7n56ttiak9qet37qbka9tkmh
            references public.fonction,
    status               bigint,
    autorisation_map_key bigint not null
        constraint fkg488nhw14s1aldc3bydktadc2
            references public.fonctionnalite,
    primary key (fonction_id, autorisation_map_key)
);

create table if not exists public.materiel_transport
(
    id            bigserial
        primary key,
    is_location   boolean,
    reference     text,
    type_materiel varchar(255)
);

create table if not exists public.person
(
    id      bigserial
        primary key,
    adresse text,
    email   text,
    nom     text,
    num_tel text,
    photo   oid
);

create table if not exists public.filiale
(
    active boolean not null,
    id     bigint  not null
        primary key
        constraint fkhwcw0k2805bhh24ipfo7fhd5j
            references public.person
);

create table if not exists public.caisse
(
    id            bigserial
        primary key,
    mode_payement varchar(255),
    value         double precision,
    filiale_id    bigint
        constraint fkfi0bmo3ltebryjb2qfu9v9ybq
            references public.filiale
);

create table if not exists public.client_fournisseur
(
    type       integer not null,
    id         bigint  not null
        primary key
        constraint fkb8wtu380s9rc417n0vdf9ijeo
            references public.person,
    filiale_id bigint  not null
        constraint fk8gfv4wed4y8gg2y1hg5ot6s83
            references public.filiale
);

create table if not exists public.fonction_filiale
(
    filiale_id bigint
        constraint fkojblgjae933vkoi93xypxib4n
            references public.filiale,
    id         bigint not null
        primary key
        constraint fkd2hew22h3qdb19pb51mp2fvby
            references public.fonction
);

create table if not exists public.inventory_alert
(
    article_id bigint not null
        constraint fk_article_id
            references public.article
            on delete cascade,
    filiale_id bigint not null
        constraint fknscwajb2pg27h37thymgn90jt
            references public.filiale,
    quantite   double precision,
    primary key (article_id, filiale_id)
);

create table if not exists public.magasin
(
    id_magasin  bigserial
        primary key,
    adresse     text,
    nom_magasin text,
    filiale_id  bigint
        constraint magasin_filiale_key_constraint
            references public.filiale
);

create table if not exists public.person_morale
(
    cif  text,
    nif  text,
    rcs  text,
    stat text,
    id   bigint not null
        primary key
        constraint fkkpahk86li2icc3so6pcmf40h
            references public.person
);

create table if not exists public.personne_physique
(
    cin                    text,
    date_delivrance        date,
    lieu_delivrance        text,
    sexe                   varchar(255),
    situation_matrimoniale varchar(255),
    id                     bigint not null
        primary key
        constraint fk1wt7xn3y93ecfo331ahvo2ird
            references public.person,
    fonction_id            bigint
        constraint user_fonction_key_constraint
            references public.fonction
);

create table if not exists public._user
(
    enabled    boolean,
    password   text,
    username   text,
    id         bigint not null
        primary key
        constraint fk378jh9bywdkcmkkcx2pujjplp
            references public.personne_physique,
    filiale_id bigint
        constraint user_filiale_key_constraint
            references public.filiale
);

create table if not exists public.info_filiale_caisse
(
    id                bigserial
        primary key,
    date              date,
    description       varchar(255),
    mode_payement     varchar(255),
    montant_operation double precision,
    operation_caisse  varchar(255),
    reference         text,
    filiale_id        bigint
        constraint fk216rqgw3u6o30dcr79curuhf5
            references public.filiale,
    user_id           bigint
        constraint fkt09390mitpvlwwkeeyv9h31d3
            references public._user
);

create table if not exists public.info_caisse_magasin
(
    magasin_id_magasin bigint
        constraint fkmc4yvtklffai4ffs2drjtudcr
            references public.magasin,
    id                 bigint not null
        primary key
        constraint fkhei30itrdhm5xrgcavhpli0yg
            references public.info_filiale_caisse
);

create table if not exists public.materiel_responsable
(
    responsable_id bigint
        constraint fkk9nc1h9d4p50ffq0s07962hs7
            references public.personne_physique,
    id             bigint not null
        primary key
        constraint fktfhtpg17bmdgm898b1bb3urnb
            references public.materiel_transport
);

create table if not exists public.payement
(
    id               bigserial
        primary key,
    date_payement    date,
    is_valid         boolean,
    mode_payement    integer,
    montant_payement double precision,
    montant_restant  double precision,
    ifc_id           bigint
        constraint fkbvp9mp3pjymly51xyiubpata3
            references public.info_filiale_caisse,
    user_id          bigint
        constraint fkq88evq9tx2p13ss7va4bhlu20
            references public._user
);

create table if not exists public.trajet
(
    id          bigserial
        primary key,
    depart      varchar(255),
    destination varchar(255),
    distance    double precision
);

create table if not exists public.transfert
(
    id                bigserial
        primary key,
    chauffeur         text,
    magasin_dest_id   bigint
        constraint transfert_magasin_receveur_key_constraint
            references public.magasin,
    magasin_source_id bigint
        constraint transfert_magasin_origine_key_constraint
            references public.magasin
);

create table if not exists public.transfert_article
(
    article_id   bigint not null
        constraint ta_article_key_constraint
            references public.article,
    transfert_id bigint not null
        constraint ta_transfert_key_constraint
            references public.transfert,
    date         timestamp,
    quantite     double precision,
    primary key (article_id, transfert_id)
);

create table if not exists public.trosa
(
    id            bigserial
        primary key,
    date          date,
    date_echeance date,
    description   text,
    mode_payement integer,
    montant       double precision,
    reference     text,
    reste         double precision,
    type_trosa    integer,
    cf_id         bigint
        constraint fk39x2taxfbjx67kctge329f4qi
            references public.client_fournisseur
);

create table if not exists public.payement_trosa
(
    trosa_id bigint
        constraint fkb2526f59dc0c9oml61dam6ye9
            references public.trosa,
    id       bigint not null
        primary key
        constraint fk5u3q6yc86k8foce909bd28f2n
            references public.payement
);

create table if not exists public.trosa_payements
(
    trosa_id     bigint not null
        constraint fk5tvd0q6owdj5oeevkwwoqr3jh
            references public.trosa,
    payements_id bigint not null
        constraint uk_bi7hj23qmxw0uy3vfawi9gs8u
            unique
        constraint fk5a1fb6pi4ltlfj9q468f9mexc
            references public.payement
);

create table if not exists public.unite
(
    id          bigserial
        primary key,
    code        text,
    designation text
);

create table if not exists public.article_unite
(
    id              bigserial
        primary key,
    barcode         varchar(255),
    niveau          integer          not null,
    poids           double precision not null,
    quantite_niveau double precision not null,
    article_id      bigint
        constraint fk_article_id
            references public.article
            on delete cascade,
    unite_id        bigint
        constraint fk_unite_id
            references public.unite
            on delete cascade
);

create table if not exists public.info_article_magasin
(
    id                             bigserial
        primary key,
    date                           date,
    description                    text,
    montant_article                double precision,
    quantite_ajout                 double precision,
    quantite_stock_apres_operation double precision,
    reference                      text,
    type_operation                 varchar(255),
    article_id                     bigint
        constraint fk_article_id
            references public.article
            on delete cascade,
    magasin_id                     bigint
        constraint fk_magasin_id
            references public.magasin
            on delete cascade,
    unite_id                       bigint
        constraint fk_unite_id
            references public.unite
            on delete cascade,
    user_id                        bigint
        constraint fkmq7t1rb4f0x9tvei5np310gv8
            references public._user
);

create table if not exists public.approv
(
    id                      bigserial
        primary key,
    date_peremption         date,
    description             text,
    montant_approv          double precision,
    quantite_peremption     double precision,
    info_article_magasin_id bigint
        constraint fkee9l9phtchq0t6ydgwvj94em7
            references public.info_article_magasin
);

create table if not exists public.approvisionement_fournisseur
(
    fournisseur_id bigint
        constraint fk7ek5k7lqe5ir888sy1dmvnhkr
            references public.client_fournisseur,
    id             bigint not null
        primary key
        constraint fkm4wotbvl3uojnskj2e8cm7s2f
            references public.approv
);

create table if not exists public.peremption
(
    id                  bigserial
        primary key,
    date_peremption     date,
    quantite_peremption double precision,
    article_id          bigint
        constraint fk_article_id
            references public.article
            on delete cascade,
    magasin_id          bigint
        constraint fk_magasin_id
            references public.magasin
            on delete cascade,
    unite_id            bigint
        constraint fk_unite_id
            references public.unite
            on delete cascade
);

create table if not exists public.prix_article_filiale
(
    id                  bigserial
        primary key,
    date_enregistrement timestamp,
    prix_vente          double precision,
    article_id          bigint
        constraint fk_article_id
            references public.article
            on delete cascade,
    filiale_id          bigint
        constraint fk_filiale_id
            references public.filiale
            on delete cascade,
    unite_id            bigint
        constraint fk_unite_id
            references public.unite
            on delete cascade,
    user_id             bigint
        constraint fk_user_id
            references public._user
);

create table if not exists public.sortie
(
    id                      bigserial
        primary key,
    info_article_magasin_id bigint
        constraint fkc94a1h5i9rpp20hx5emeesscs
            references public.info_article_magasin
);

create table if not exists public.stock
(
    article_id bigint not null
        constraint fk_stock_article_id
            references public.article
            on delete cascade,
    magasin_id bigint not null
        constraint fk_stock_magasin_id
            references public.magasin
            on delete cascade,
    unite_id   bigint not null
        constraint fk_stock_unite_id
            references public.unite
            on delete cascade,
    count      double precision,
    primary key (article_id, magasin_id, unite_id)
);

create table if not exists public.transfert_info
(
    transfert_id                 bigint not null
        constraint fk6wo1relp2s61u7xtrih7vc2k8
            references public.transfert,
    info_article_magasin_list_id bigint not null
        constraint uk_los6chfe359tfxa6bxjpa089h
            unique
        constraint fkcb76basile3u07xevpbq221g4
            references public.info_article_magasin
);

create table if not exists public.user_magasin
(
    user_id    bigint not null
        constraint fk_um_user_id
            references public.personne_physique,
    magasin_id bigint not null
        constraint fk_um_magasin_id
            references public.magasin
            on delete cascade
);

create table if not exists public.vente
(
    id                                 bigserial
        primary key,
    date                               date,
    is_concerned_by_invoice_regulation boolean,
    is_payement_mode_changed           boolean,
    montant_avec_remise                double precision,
    montant_vente                      double precision,
    ref_vente                          varchar(255),
    client_id                          bigint
        constraint fk_client_id
            references public.client_fournisseur,
    filiale_id                         bigint not null
        constraint fkl1974he4065ewa9p14ygunqql
            references public.filiale,
    user_id                            bigint
        constraint fkmrrjkylypib7vm3w0g7yagjpx
            references public._user
);

create table if not exists public.avoir
(
    id        bigserial
        primary key,
    date      date,
    montant   double precision,
    ref_avoir text,
    info_id   bigint
        constraint fklxyeflsx4nsd24pu55b21983v
            references public.info_filiale_caisse,
    vente_id  bigint
        constraint avoir_vente_key_constraint
            references public.vente
);

create table if not exists public.avoir_info_article_magasin
(
    avoir_id                bigint not null
        constraint fknpq71662t998nxr6im4qumbel
            references public.avoir,
    info_article_magasin_id bigint not null
        constraint uk_7rx02dwwnpsbg8cebsm2l5iox
            unique
        constraint fkt3ywq6qas4o4r8f5t8lajtjje
            references public.info_article_magasin
);

create table if not exists public.info_vente
(
    article_id bigint not null
        constraint info_vente_article_key_constraint
            references public.article,
    vente_id   bigint not null
        constraint info_vente_vente_key_constraint
            references public.vente,
    date_vente timestamp,
    prix_vente double precision,
    quantite   double precision,
    reference  varchar(255),
    primary key (article_id, vente_id)
);

create table if not exists public.livraison
(
    id            bigserial
        primary key,
    numero_bon    text,
    statut_voyage varchar(255),
    vente_id      bigint
        constraint livraison_vente_key_constraint
            references public.vente
);

create table if not exists public.livraison_materiel_transport
(
    mat_trans_id bigint
        constraint fkbhshi8ku04cyvqncbo0mqv4mw
            references public.materiel_transport,
    livraison_id bigint not null
        primary key
        constraint fkhq0l73v1wwnhaehguiysr39dn
            references public.livraison
);

create table if not exists public.payement_vente
(
    vente_id bigint
        constraint fkbvl3nwk74cdeunyr28343tfmp
            references public.vente,
    id       bigint not null
        primary key
        constraint fkja4c97ku8ehkk3efiny6d88h4
            references public.payement
);

create table if not exists public.vente_info_article_magasin
(
    vente_id                bigint not null
        constraint fkl3393ropsrxx0fpudinv40s52
            references public.vente,
    info_article_magasin_id bigint not null
        constraint uk_1s5er6fxs8luu8hqefkoe5t34
            unique
        constraint fk73gflwri91ipwthq9qt64gqk6
            references public.info_article_magasin
);

create table if not exists public.voyage
(
    id                       bigserial
        primary key,
    date_arrive              date,
    date_creation            date,
    date_depart              date,
    description              varchar(255),
    reference                text,
    statut_voyage            integer,
    materiel_de_transport_id bigint
        constraint voyage_materiel_transport_key_contraint
            references public.materiel_transport,
    trajet_id                bigint
        constraint fkfkkl28of6k6b0yk0h7bh7aipc
            references public.trajet
);

create table if not exists public.embarquement_article
(
    id         bigserial
        primary key,
    date       timestamp,
    quantite   double precision,
    article_id bigint
        constraint ea_article_key_constraint
            references public.article,
    user_id    bigint
        constraint voyage_responsable_key_constraint
            references public._user,
    voyage_id  bigint
        constraint ea_voyage_key_constraint
            references public.voyage
);

create table if not exists public.info_article_voyage
(
    id                    bigserial
        primary key,
    date                  date,
    date_peremption       date,
    description           text,
    is_transfered         boolean,
    num_facture           varchar(255),
    poids                 double precision,
    prix_achat            double precision,
    prix_transport        double precision,
    prix_vente            double precision,
    quantite_ajout        double precision,
    reference             varchar(255),
    article_id            bigint
        constraint fk_article_id
            references public.article
            on delete cascade,
    fournisseur_id        bigint
        constraint fko2fb7g8vsh5vocredoqcwyp4a
            references public.client_fournisseur,
    materiel_transport_id bigint
        constraint fkoe2hmnqy8rwcfelkjy6k0r6vc
            references public.materiel_transport,
    unite_id              bigint
        constraint fk_unite_id
            references public.unite
            on delete cascade,
    user_id               bigint
        constraint fkiwkpeqnbvxaqo3qi5eppym45b
            references public._user,
    voyage_id             bigint
        constraint fkqbiamwi8sfb5hw0hrtlxgl38j
            references public.voyage
);

create table if not exists public.voyage_article_fournisseur
(
    fournisseur_id bigint
        constraint fkliq46bnw1ol82h1dadl1hkjni
            references public.client_fournisseur,
    va_id          bigint not null
        primary key
        constraint fkeh2o7ims06p0l0ybatyxxxy83
            references public.embarquement_article
);

create table if not exists public.voyage_article_magasin
(
    magasin_id bigint
        constraint fkm3q21a6hmx6usjxps4fwroy4t
            references public.magasin,
    va_id      bigint not null
        primary key
        constraint fkjyfq8ygywkh1w58u8v1jykkh4
            references public.embarquement_article
);

create procedure mettre_a_jour_date_peremption(IN id_magasin bigint, IN id_article bigint, IN id_unite bigint, IN new_date timestamp without time zone, IN old_date timestamp without time zone)
    language plpgsql
as $$
DECLARE
    row RECORD;
BEGIN

    for row in select ap1.id from approv ap1 join info_article_magasin iam on iam.id = ap1.info_article_magasin_id where iam.magasin_id=id_magasin and iam.article_id= id_article and iam.unite_id = id_unite and ap1.date_peremption =old_date
        loop
            update approv set  date_peremption =  new_date where id = row.id;
        end loop;

END;
$$;

alter procedure mettre_a_jour_date_peremption(bigint, bigint, bigint, timestamp, timestamp) owner to postgres;

create procedure mettre_a_jour_quantite_en_peremption(IN id_magasin bigint, IN id_article bigint, IN id_unite bigint, IN nouveau_quantite double precision)
    language plpgsql
as $$
DECLARE
    row RECORD;
    QUANTITE_AJOUT_TEMP double precision = 0;
BEGIN

    QUANTITE_AJOUT_TEMP := nouveau_quantite;

    FOR row IN select ap.id,ap.quantite_peremption from approv ap join info_article_magasin iam on iam.id = ap.info_article_magasin_id
               where iam.magasin_id =id_magasin and iam.unite_id=id_unite and iam.article_id = id_article and ap.quantite_peremption > 0 order by ap.date_peremption asc
        LOOP

            if QUANTITE_AJOUT_TEMP > 0 then

                if QUANTITE_AJOUT_TEMP > row.quantite_peremption then

                    update approv set quantite_peremption = 0 where id = row.id;

                end if;

                if QUANTITE_AJOUT_TEMP < row.quantite_peremption then

                    update approv set quantite_peremption = ( row.quantite_peremption - QUANTITE_AJOUT_TEMP)  where id = row.id;

                end if;

                QUANTITE_AJOUT_TEMP := ( QUANTITE_AJOUT_TEMP - row.quantite_peremption );

            end if;

        END LOOP;
end;
$$;

alter procedure mettre_a_jour_quantite_en_peremption(bigint, bigint, bigint, double precision) owner to postgres;

create function before_insert_on_info_article_unite_magasin() returns trigger
    language plpgsql
as $$
DECLARE
    quantite_en_stock_actuelement DOUBLE PRECISION = 0.0;
    quantite_niveau_unite DOUBLE PRECISION = 0.0;
    nouveau_quantite_en_stock DOUBLE PRECISION =0.0;
    primary_unite_id BIGINT =0;
    item_count INT =0;
    nombre_quantite_alert INT = 0;
    ALERT_FILIALE_ID BIGINT = 0;
BEGIN
    -- recuperer l'unite primaire de l'article
    SELECT au.unite_id into primary_unite_id FROM article_unite au where article_id = new.article_id and au.niveau = 1;

    -- RECUPERATION DE LA QUANTITE NIVEAU
    SELECT au.quantite_niveau into quantite_niveau_unite FROM  article_unite au WHERE au.article_id = new.article_id AND au.unite_id = new.unite_id;

    -- RECUPERER LE NOMBRE EN STOCK
    SELECT count(article_id) into item_count FROM  stock  WHERE article_id = new.article_id AND unite_id = primary_unite_id AND magasin_id = new.magasin_id;

    -- RECUPERER LE STOCK ACTUEL
    SELECT count into quantite_en_stock_actuelement FROM  stock  WHERE article_id = new.article_id AND unite_id = primary_unite_id AND magasin_id = new.magasin_id;

    if item_count = 0 then

        nouveau_quantite_en_stock := new.quantite_ajout*quantite_niveau_unite;

        new.quantite_stock_apres_operation := new.quantite_ajout;

        -- INSERTION DANS LA TABLE STOCK

        insert into stock(article_id,unite_id,magasin_id,count) values (NEW.article_id,primary_unite_id,NEW.magasin_id,nouveau_quantite_en_stock);

        -- INITIALISATION DE LA QUANTITE EN ALERT DE CHAQUE ARTICLE ET FILIALE

        select m.filiale_id into ALERT_FILIALE_ID from magasin m where m.id_magasin=new.magasin_id;

        select count(ia.filiale_id) into nombre_quantite_alert from inventory_alert ia where ia.article_id = new.article_id and ia.filiale_id = ALERT_FILIALE_ID;

        if nombre_quantite_alert = 0 then
            insert into inventory_alert(article_id, filiale_id, quantite) values (new.article_id,ALERT_FILIALE_ID,0.0);
        end if;

    end if;

    if item_count > 0 then

        if new.type_operation = 'ENTRE' or new.type_operation like '%TRANSFERT%' or new.type_operation = 'AVOIR' then

            nouveau_quantite_en_stock := quantite_en_stock_actuelement + (new.quantite_ajout*quantite_niveau_unite) ;

            new.quantite_stock_apres_operation := (quantite_en_stock_actuelement/quantite_niveau_unite) + new.quantite_ajout;


            if new.type_operation like '%TRANSFERT%' AND  new.type_operation like '%VERS%' then

                nouveau_quantite_en_stock := quantite_en_stock_actuelement - (new.quantite_ajout*quantite_niveau_unite);

                new.quantite_stock_apres_operation :=  (quantite_en_stock_actuelement/quantite_niveau_unite) - quantite_niveau_unite;
            end if;

            -- INITIALISATION DE LA QUANTITE EN ALERT DE CHAQUE ARTICLE ET FILIALE

            if new.type_operation = 'ENTRE' then

                select m.filiale_id into ALERT_FILIALE_ID from magasin m where m.id_magasin=new.magasin_id;

                select count(ia.filiale_id) into nombre_quantite_alert from inventory_alert ia where ia.article_id = new.article_id and ia.filiale_id = ALERT_FILIALE_ID;

                if nombre_quantite_alert = 0 then
                    insert into inventory_alert(article_id, filiale_id, quantite) values (new.article_id,ALERT_FILIALE_ID,0.0);
                end if;

            end if;

        end if;

        if new.type_operation = 'VENTE' or new.type_operation = 'SORTIE'then

            nouveau_quantite_en_stock := quantite_en_stock_actuelement - (new.quantite_ajout*quantite_niveau_unite) ;

            new.quantite_stock_apres_operation :=  (quantite_en_stock_actuelement/quantite_niveau_unite) - new.quantite_ajout;

        end if;

        if new.type_operation = 'INVENTAIRE' then

            nouveau_quantite_en_stock := new.quantite_ajout;

            new.quantite_stock_apres_operation := new.quantite_ajout;

            -- ENREGISTRER L'ANNULATION
            insert into info_article_magasin (date, description, quantite_ajout,quantite_stock_apres_operation,reference, type_operation, article_id, magasin_id, unite_id, user_id)

            values (new.date,'Modification de la quantité en stock suite a un inventaire',quantite_en_stock_actuelement,new.quantite_ajout,new.reference,'ANNULATION',new.article_id,new.magasin_id,new.unite_id,new.user_id);

        end if;

        -- Mis-a-jour du stock

        update stock set count = nouveau_quantite_en_stock where article_id = NEW.article_id AND unite_id = primary_unite_id AND magasin_id = NEW.magasin_id;

        quantite_en_stock_actuelement :=0;

    end if;

    -- MIS A JOUR DU QUANTITE PEREMPTION

    if     new.type_operation = 'VENTE'
        or (new.type_operation like '%TRANSFERT%' AND  new.type_operation like '%VERS%')
        or  new.type_operation = 'SORTIE'  then

        call mettre_a_jour_quantite_en_peremption(new.magasin_id,new.article_id,new.unite_id,new.quantite_ajout);

    end if;
    RETURN NEW; --ignored since this is after trigger
END;
$$;

alter function before_insert_on_info_article_unite_magasin() owner to postgres;

create trigger info_article_trigger
    before insert
    on info_article_magasin
    for each row
execute procedure before_insert_on_info_article_unite_magasin();

create function on_insert_caisse_filiale() returns trigger
    language plpgsql
as $$
DECLARE
    montant_caisse double precision=0.0;
    nombre_element INT = 0.0;
BEGIN

    select count(c.id) into nombre_element from caisse c where c.filiale_id = new.filiale_id and c.mode_payement = new.mode_payement;

    if nombre_element = 0 then

        insert into caisse (value,filiale_id,mode_payement)  values (new.montant_operation,new.filiale_id,new.mode_payement);

    end if;

    if nombre_element >= 1 then


        if new.operation_caisse = 'FACTURE' or new.operation_caisse = 'ENCAISSEMENT' then

            update caisse c set value = value + new.montant_operation where c.filiale_id = new.filiale_id and c.mode_payement = new.mode_payement ;

        end if;

        if new.operation_caisse = 'DECAISSEMENT' or new.operation_caisse = 'AVOIR' then
            if montant_caisse > 0.0 then
                update caisse c set value = caisse.value - new.montant_operation where c.filiale_id = new.filiale_id and c.mode_payement = new.mode_payement;
            end if;
        end if;
    end if;
    RETURN NEW; --ignored since this is after trigger
END;
$$;

alter function on_insert_caisse_filiale() owner to postgres;

create trigger on_insert_on_info_filiale_caisse
    before insert
    on info_filiale_caisse
    for each row
execute procedure on_insert_caisse_filiale();

insert into fonctionnalite(nom) values ('menu-dashboard');
insert into fonctionnalite(nom) values ('menu-article');
insert into fonctionnalite(nom) values ('menu-vente');
insert into fonctionnalite(nom) values ('menu-detail-vente');
insert into fonctionnalite(nom) values ('menu-magasin');
insert into fonctionnalite(nom) values ('menu-stock');
insert into fonctionnalite(nom) values ('menu-facture');
insert into fonctionnalite(nom) values ('menu-prix');
insert into fonctionnalite(nom) values ('menu-embarquement');
insert into fonctionnalite(nom) values ('menu-peremption');
insert into fonctionnalite(nom) values ('menu-client');
insert into fonctionnalite(nom) values ('menu-fournisseur');
insert into fonctionnalite(nom) values ('menu-operation');
insert into fonctionnalite(nom) values ('menu-operation-liste');
insert into fonctionnalite(nom) values ('menu-operation-entree');
insert into fonctionnalite(nom) values ('menu-operation-sortie');
insert into fonctionnalite(nom) values ('menu-operation-transfert');
insert into fonctionnalite(nom) values ('menu-livraison');
insert into fonctionnalite(nom) values ('menu-caisse');
insert into fonctionnalite(nom) values ('menu-utilisateur');
insert into fonctionnalite(nom) values ('menu-choix-magasin');
insert into fonctionnalite(nom) values ('menu-autorisation');
insert into fonctionnalite(nom) values ('menu-transports');

insert into fonctionnalite(nom) values ('crud-article');
insert into fonctionnalite(nom) values ('crud-magasin');
insert into fonctionnalite(nom) values ('crud-prix');
insert into fonctionnalite(nom) values ('crud-facture');
insert into fonctionnalite(nom) values ('crud-peremption');
insert into fonctionnalite(nom) values ('crud-fournisseur');
insert into fonctionnalite(nom) values ('crud-embarquement');
insert into fonctionnalite(nom) values ('crud-caisse');
insert into fonctionnalite(nom) values ('crud-utilisateur');
insert into fonctionnalite(nom) values ('crud-client');

insert into fonctionnalite(nom) values ('notification-facture');
insert into fonctionnalite(nom) values ('notification-livraison');
insert into fonctionnalite(nom) values ('notification-commande');
insert into fonctionnalite(nom) values ('option-configuration systeme');

insert into fonctionnalite(nom) values ('option-bloquer compte client');
insert into fonctionnalite(nom) values ('impression-facture');
insert into fonctionnalite(nom) values ('impression-livraison');

insert into fonctionnalite(nom) values ('imprimer-facture');
insert into fonctionnalite(nom) values ('imprimer-avoir');
insert into fonctionnalite(nom) values ('imprimer-cheque');
insert into fonctionnalite(nom) values ('imprimer-any maka vola');
insert into fonctionnalite(nom) values ('imprimer-credit');
insert into fonctionnalite(nom) values ('imprimer-recette depense');
insert into fonctionnalite(nom) values ('imprimer-recu any maka vola');
insert into fonctionnalite(nom) values ('imprimer-recu credit');
insert into fonctionnalite(nom) values ('imprimer-Bon entre');
insert into fonctionnalite(nom) values ('imprimer-Bon sortie');
insert into fonctionnalite(nom) values ('imprimer-Bon transfert');
insert into fonctionnalite(nom) values ('imprimer-Bon changement code article');


