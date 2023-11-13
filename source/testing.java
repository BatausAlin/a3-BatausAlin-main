import domain.Masina;
import repository.BinaryFileRepository;

public class testing {
    public static void main(String[] args){
        BinaryFileRepository repo = new BinaryFileRepository<>("gigel.bin");
        Masina masina = new Masina(1, "AUDI", "A8", "ALB", "CJ 17 BAA");
        repo.add(masina);
//        repo.add(masina);
//        repo.add(masina);

        for(int i = 0; i <  repo.size(); i++){
            System.out.println(repo.get(i));
        }
    }
}
