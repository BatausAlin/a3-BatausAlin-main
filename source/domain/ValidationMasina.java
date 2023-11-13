package domain;

import service.carService;

import java.util.ArrayList;
import java.util.List;

public class ValidationMasina {

    static carService service;

    public ValidationMasina(carService service){
        this.service = service;
    }

    // Metoda de validare care arunca o exceptie daca masina nu este valida
    public static List<String> validate(String idStr, String marca, String model, String culoare, String numarInmatriculare) {
        List<String> errors = new ArrayList<>();
        for(int i = 0; i < service.getcar().size(); i++){
            if(Integer.parseInt(idStr) == service.getcar().get(i).getId()){
                errors.add("Id-ul deja exista");
            }
        }
        // Validare ID
        try {
            int id = Integer.parseInt(idStr);
            if (id <= 0) {
                errors.add("ID-ul masinii trebuie sa fie un numar pozitiv.");
            }
        } catch (NumberFormatException e) {
            errors.add("ID-ul trebuie să fie un număr întreg.");
        }

        // Restul validărilor...
        if (marca == null || marca.trim().isEmpty()) {
            errors.add("Marca masinii nu poate fi nula sau goala.");
        }

        if (model == null || model.trim().isEmpty()) {
            errors.add("Modelul masinii nu poate fi nul sau gol.");
        }
        if (culoare == null || culoare.trim().isEmpty()) {
            errors.add("Culoare masinii nu poate fi nul sau gol.");
        }
        if (numarInmatriculare == null || numarInmatriculare.trim().isEmpty()) {
            errors.add("Numarul de inmatriculare al masinii nu poate fi nul sau gol.");
        }

        return errors;
    }

    // Clasa interna pentru exceptii de validare
    public static class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }
    }
}
