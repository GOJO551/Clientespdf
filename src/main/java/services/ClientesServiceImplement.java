package services;

import modelo.Clientes;

import java.util.Arrays;
import java.util.List;

public class ClientesServiceImplement implements ClientesService{

    @Override
    public List<Clientes> listar() {
        return Arrays.asList(new Clientes(1L,"Ivan","Lisintuña","Masculino","112151"),
                new Clientes(65L,"Carlos","Cabrera","Masculino","320230"),
                new Clientes(45L,"Angel","Cabrera","Masculino","165165"),
                new Clientes(30L,"Magdalena","Cabrera","Femenino","03656"),
                new Clientes(22L,"Juan","Cabrera","Masculino","1313"),
                new Clientes(21L,"David","Cabrera","Masculino","651561"),
                new Clientes(30L,"Sebastian","Cabrera","Masculino","61561"),
                new Clientes(15L,"Alex","Cadena","Masculino","2616"),
                new Clientes(88L,"Jhonatan","Pardo","Masculino","615616"),
                new Clientes(99L,"Ximena","Cabrera","Femenino","165615")
        );

    }
}