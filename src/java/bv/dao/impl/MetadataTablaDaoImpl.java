package bv.dao.impl;

import bv.dao.MetadataTablaDao;
import java.util.ArrayList;
import java.util.List;
import vb.entidad.MetadataTabla;
import bv.util.sql;

/**
 *
 * @author virtual
 */
public class MetadataTablaDaoImpl implements MetadataTablaDao{

    sql conector = new sql();

    @Override
    public List<MetadataTabla> listarMetadataTabla() {
        List<MetadataTabla> lstMetadata = new ArrayList<>();
        String[] array = new String[1];
        array[0] = "DOCUMENTAL";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_LISTAR_METADATA_TABLAS", array);
        for (Object[] d : data) {
            MetadataTabla metadata = new MetadataTabla();
            metadata.setID_METADATA(Integer.parseInt(d[0].toString()));
            metadata.setCAMPO(d[1].toString());
            metadata.setETIQUETA_DC(d[2].toString());
            metadata.setDESCRIPCION(d[3].toString());
            metadata.setLONGITUD(Integer.parseInt(d[4].toString()));
            lstMetadata.add(metadata);
        }
        return lstMetadata;
    }

}
