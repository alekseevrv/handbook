package com.example.handbook;

import com.example.handbook.entity.Insurance;
import com.example.handbook.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class InsuranceController {

    @Autowired
    private InsuranceRepository insuranceRepository;

    // Главная страница
    @GetMapping("/")
    public String main(Model model) {

        Iterable<Insurance> insurances = insuranceRepository.findAllOrderById();
        model.addAttribute("insurances", insurances);

        return "main";
    }

    // Вывод формы для добавления организации
    @GetMapping("/add-insurance")
    public String addInsuranceForm(Insurance insurance) {
        return "addInsurance";
    }

    // Проверка формы и сохранение организации в БД
    @PostMapping("/add-insurance")
    public String addInsuranceSave(
            @Valid Insurance insurance,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            return "addInsurance";
        }

        if(checkUnique(insurance)) {
            model.addAttribute(
                    "errorUnique",
                    "Поля ИНН, ОГРН и название организации должны быть уникальными");
            return "addInsurance";
        }

        insuranceRepository.save(insurance);

        return "redirect:/add-insurance";
    }

    // Удаление организации
    @GetMapping("/delete-insurance/{id}")
    public String deleteInsurance(
            @PathVariable("id") long id
    ) {
        Insurance insurance = insuranceRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Вы пытались удалить несуществующую организацию с id:" + id));

        insuranceRepository.delete(insurance);

        return "redirect:/";
    }

    // Проверка на уникальность полей записи
    private boolean checkUnique(Insurance insurance) {

        List<Insurance> insurances = insuranceRepository.findByInnOrOgrnOrName(
                insurance.getInn(),
                insurance.getOgrn(),
                insurance.getName()
        );

        return insurances.size() > 0;
    }

    // Вывод формы поиска организаций
    @GetMapping("/search-insurance")
    public String searchInsuranceForm() {

        return "searchInsurance";
    }

    // Поиск организаций и вывод результата поиска
    @PostMapping("/search-insurance")
    public String searchInsurance(
            @RequestParam Map<String, String> form,
            Model model
    ) {
        if (
                !form.get("inn").equals("") || !form.get("ogrn").equals("") ||
                !form.get("name").equals("") || !form.get("adres").equals("")
        ) {
            Iterable<Insurance> insurances = insuranceRepository.findByInnLikeAndOgrnLikeAndNameLikeAndAdresLike(
                    "%" + form.get("inn") + "%",
                    "%" + form.get("ogrn") + "%",
                    "%" + form.get("name") + "%",
                    "%" + form.get("adres") + "%"
            );

            model.addAttribute("insurances", insurances);
        }

        return "searchInsurance";
    }
}
