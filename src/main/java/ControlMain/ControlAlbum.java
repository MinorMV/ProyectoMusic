package ControlMain;

import Logic.Album;
import View.ViewAlbum;

public class ControlAlbum {

    public void capturardatosAlbum(Album mal, ViewAlbum val) {
      
        if (mal.getAlbumNombre() == null || mal.getAlbumNombre().isBlank()) {
            val.capturaAlbumNombre();
            mal.setAlbumNombre(val.getValbumNombre());
        }

      
        val.capturaGenero();
        val.capturaFechaCreacion();
        val.capturaDescargas();
        val.capturaArtista();
        val.capturaCanciones();

        mal.setGenero(val.getVgenero());
        mal.setFechaCreacion(val.getVfechaCreacion());
        mal.setDescargas(val.getVdescargas());
        mal.setArtista(val.getVartista());
        mal.setCanciones(val.getVcanciones());

        val.mostrarinformacionAlbum();
    }
}
