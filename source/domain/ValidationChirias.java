package domain;

import service.chiriasService;

import java.util.ArrayList;
import java.util.List;

public class ValidationChirias {
    static chiriasService service;

    public ValidationChirias(chiriasService service){
        ValidationChirias.service = service;
    }

    public static List<String> validate(String idStr, String nume, String prenume, String CNP){
        List<String> errors = new ArrayList<>();

        for(int i = 0; i < service.getchirias().size(); i++){
            if(Integer.parseInt(idStr) == service.getchirias().get(i).getId()){
                errors.add("Id-ul deja exista");
            }
        }

        try{
            int id = Integer.parseInt(idStr);
            if (id <= 0) {
                errors.add("ID-ul chiriasului trebuie sa fie un numar pozitiv.");
            }
        }catch(NumberFormatException e){
            errors.add("ID-ul trebuie sa fie un numar pozitiv intreg");
        }

        if (nume == null || nume.trim().isEmpty()) {
            errors.add("Marca masinii nu poate fi nula sau goala.");
        }

        if (prenume == null || prenume.trim().isEmpty()) {
            errors.add("Modelul masinii nu poate fi nul sau gol.");
        }
        if (CNP == null || CNP.trim().isEmpty()) {
            errors.add("Culoare masinii nu poate fi nul sau gol.");
        }

        return errors;
    }
}
