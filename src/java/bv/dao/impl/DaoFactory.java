package bv.dao.impl;

import bv.dao.*;
import static bv.util.Constantes.*;

/**
 *
 * @author virtual
 */
public class DaoFactory {

    private DaoFactory() {
    }

    public AudienciaDao getAudienciaDao(int tipo) {
        AudienciaDao dao;
        switch (tipo) {
            case AUDIENCIA:
                dao = new AudienciaDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public AutorDao getAutorDao(int tipo) {
        AutorDao dao;
        switch (tipo) {
            case AUTOR:
                dao = new AutorDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public BibliotecaDao getBibliotecaDao(int tipo) {
        BibliotecaDao dao;
        switch (tipo) {
            case BIBLIOTECA:
                dao = new BibliotecaDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public ColeccionDao getColeccionDao(int tipo) {
        ColeccionDao dao;
        switch (tipo) {
            case COLECCION:
                dao = new ColeccionDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public ContribuidorDao getContribuidorDao(int tipo) {
        ContribuidorDao dao;
        switch (tipo) {
            case CONTRIBUIDOR:
                dao = new ContribuidorDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public DocumentalDao getDocumentalDao(int tipo) {
        DocumentalDao dao;
        switch (tipo) {
            case DOCUMENTAL:
                dao = new DocumentalDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public EditorialDao getEditorialDao(int tipo) {
        EditorialDao dao;
        switch (tipo) {
            case EDITORIAL:
                dao = new EditorialDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public EspacialDao getEspacialDao(int tipo) {
        EspacialDao dao;
        switch (tipo) {
            case ESPACIAL:
                dao = new EspacialDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public FormatoDao getFormatoDao(int tipo) {
        FormatoDao dao;
        switch (tipo) {
            case FORMATO:
                dao = new FormatoDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public FormatoMedioDao getFormatoMedioDao(int tipo) {
        FormatoMedioDao dao;
        switch (tipo) {
            case FORMATO_MEDIO:
                dao = new FormatoMedioDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public InstitucionDao getInstitucionDao(int tipo) {
        InstitucionDao dao;
        switch (tipo) {
            case INSTITUCION:
                dao = new InstitucionDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public LenguajeDao getLenguajeDao(int tipo) {
        LenguajeDao dao;
        switch (tipo) {
            case LENGUAJE:
                dao = new LenguajeDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public LogTablaDao getLogTablaDao(int tipo) {
        LogTablaDao dao;
        switch (tipo) {
            case LOG_TABLA:
                dao = new LogTablaDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public LoginDao getLoginDao(int tipo) {
        LoginDao dao;
        switch (tipo) {
            case LOGIN:
                dao = new LoginDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public MetadataTablaDao getMetadataTablaDao(int tipo) {
        MetadataTablaDao dao;
        switch (tipo) {
            case METADATA_TABLA:
                dao = new MetadataTablaDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public NovedadDao getNovedadDao(int tipo) {
        NovedadDao dao;
        switch (tipo) {
            case NOVEDAD:
                dao = new NovedadDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public PaisDao getPaisDao(int tipo) {
        PaisDao dao;
        switch (tipo) {
            case PAIS:
                dao = new PaisDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public PerfilDocumentalDao getPerfilDocumentalDao(int tipo) {
        PerfilDocumentalDao dao;
        switch (tipo) {
            case PERFIL_DOCUMENTAL:
                dao = new PerfilDocumentalDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public PerfilDocumentalDetalleDao getPerfilDocumentalDetalleDao(int tipo) {
        PerfilDocumentalDetalleDao dao;
        switch (tipo) {
            case PERFIL_DOCUMENTAL_DETALLE:
                dao = new PerfilDocumentalDetalleDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public PersonalDao getPersonalDao(int tipo) {
        PersonalDao dao;
        switch (tipo) {
            case PERSONAL:
                dao = new PersonalDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public PublicacionDao getPublicacionDao(int tipo) {
        PublicacionDao dao;
        switch (tipo) {
            case PUBLICACION:
                dao = new PublicacionDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public ReporteDao getReporteDao(int tipo) {
        ReporteDao dao;
        switch (tipo) {
            case REPORTE:
                dao = new ReporteDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public SerieDao getSerieDao(int tipo) {
        SerieDao dao;
        switch (tipo) {
            case SERIE:
                dao = new SerieDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public TablaContenidoDao getTablaContenidoDao(int tipo) {
        TablaContenidoDao dao;
        switch (tipo) {
            case TABLA_CONTENIDO:
                dao = new TablaContenidoDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }
    
    public TemaDao getTemaDao(int tipo) {
        TemaDao dao;
        switch (tipo) {
            case TEMA:
                dao = new TemaDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }
    
    public TemporalDao getTemporalDao(int tipo) {
        TemporalDao dao;
        switch (tipo) {
            case TEMPORAL:
                dao = new TemporalDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }
    
    public TipoDao getTipoDao(int tipo) {
        TipoDao dao;
        switch (tipo) {
            case TIPO:
                dao = new TipoDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }
    
    public TipoOtroDao getTipoOtroDao(int tipo) {
        TipoOtroDao dao;
        switch (tipo) {
            case TIPO_OTRO:
                dao = new TipoOtroDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }
    
    public TransaccionDao getTransaccionDao(int tipo) {
        TransaccionDao dao;
        switch (tipo) {
            case TRANSACCION:
                dao = new TransaccionDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }
    
    public UsuarioDao getUsuarioDao(int tipo) {
        UsuarioDao dao;
        switch (tipo) {
            case USUARIO:
                dao = new UsuarioDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }
    
    public AlbumDao getAlbumDao(int tipo) {
        AlbumDao dao;
        switch (tipo) {
            case ALBUM:
                dao = new AlbumDaoImpl();
                break;
            default:
                dao = null;
        }
        return dao;
    }

    public static DaoFactory getInstance() {
        return DaoFactoryHolder.INSTANCE;
    }

    private static class DaoFactoryHolder {

        private static final DaoFactory INSTANCE = new DaoFactory();
    }
    
    
    
    
    
}
