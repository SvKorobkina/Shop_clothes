package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.enumm.Status;
import com.example.springsecurityapplication.models.*;
import com.example.springsecurityapplication.repositories.CategoryRepository;
import com.example.springsecurityapplication.repositories.OrderRepository;
import com.example.springsecurityapplication.security.PersonDetails;
import com.example.springsecurityapplication.services.OrderService;
import com.example.springsecurityapplication.services.PersonService;
import com.example.springsecurityapplication.services.ProductService;
import com.example.springsecurityapplication.util.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class AdminController {

    @Value("${upload.path}")
    private String uploadPath;

    private final ProductValidator productValidator;
    private final ProductService productService;
    private final PersonService personService;
    private final  OrderService orderService;
    private final CategoryRepository categoryRepository;

    private final OrderRepository orderRepository;

    @Autowired
    public AdminController(ProductValidator productValidator, ProductService productService, PersonService personService, OrderService orderService, CategoryRepository categoryRepository, OrderRepository orderRepository) {
        this.productValidator = productValidator;
        this.productService = productService;
        this.personService = personService;
        this.orderService = orderService;
        this.categoryRepository = categoryRepository;
        this.orderRepository = orderRepository;
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('')")
//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('')")

    // Метод по отображению главной страницы администратора с выводом товаров
    @GetMapping()
    public String admin(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        String role = personDetails.getPerson().getRole();

        if(role.equals("ROLE_USER")){
            return "redirect:/index";
        }
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("persons", personService.getAllPersons());
               model.addAttribute("orders", orderService.getAllOrders());
        return "admin/admin";
    }

    // Метод по отображению формы добавление
    @GetMapping("/product/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryRepository.findAll());
//        System.out.println(categoryRepository.findAll().size());
        return "product/addProduct";
    }

    // Метод по добавлению объекта с формы в таблицу product
    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @RequestParam("file_one") MultipartFile file_one, @RequestParam("file_two") MultipartFile file_two, @RequestParam("file_three") MultipartFile file_three, @RequestParam("file_four") MultipartFile file_four, @RequestParam("file_five") MultipartFile file_five) throws IOException {

        productValidator.validate(product, bindingResult);
        if(bindingResult.hasErrors()){
            return "product/addProduct";
        }
        // Проверка на пустоту файла
        if(file_one != null){
            // Дирректория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной дирректории по пути не сущетсвует
            if(!uploadDir.exists()){
                // Создаем данную дирректорию
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неищменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
            // Загружаем файл по указаннопу пути
            file_one.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // Проверка на пустоту файла
        if(file_two != null){
            // Дирректория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной дирректории по пути не сущетсвует
            if(!uploadDir.exists()){
                // Создаем данную дирректорию
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неищменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_two.getOriginalFilename();
            // Загружаем файл по указаннопу пути
            file_two.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // Проверка на пустоту файла
        if(file_three != null){
            // Дирректория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной дирректории по пути не сущетсвует
            if(!uploadDir.exists()){
                // Создаем данную дирректорию
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неищменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_three.getOriginalFilename();
            // Загружаем файл по указаннопу пути
            file_three.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // Проверка на пустоту файла
        if(file_four != null){
            // Дирректория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной дирректории по пути не сущетсвует
            if(!uploadDir.exists()){
                // Создаем данную дирректорию
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неищменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_four.getOriginalFilename();
            // Загружаем файл по указаннопу пути
            file_four.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // Проверка на пустоту файла
        if(file_five != null){
            // Дирректория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной дирректории по пути не сущетсвует
            if(!uploadDir.exists()){
                // Создаем данную дирректорию
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неищменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_five.getOriginalFilename();
            // Загружаем файл по указаннопу пути
            file_five.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        productService.saveProduct(product);
        return "redirect:/admin";
    }

    // Метод по удалению товара по id
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return "redirect:/admin";
    }

    // Метод по получению товара по id и отображение шаблона редактирования
    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("editProduct", productService.getProductId(id));
        model.addAttribute("category", categoryRepository.findAll());
        return "product/editProduct";
    }

    @PostMapping("/product/edit/{id}")
    public String editProduct(@ModelAttribute("editProduct") Product product, @PathVariable("id") int id){
        productService.updateProduct(id, product);
        return "redirect:/admin";
    }

    // Метод возвращает страницу с выводом пользователей и кладет объект пользователя в модель
    @GetMapping("/person")
    public String person(Model model){;
        model.addAttribute("person", personService.getAllPerson());
        return "person/person";
    }

    // Метод возвращает страницу с подробной информацией о пользователе
    @GetMapping("/person/info/{id}")
    public String infoPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personService.getPersonById(id));
        return "person/personInfo";
    }

    // Метод возвращает страницу с формой редактирования пользователя и помещает в модель объект редактируемого пользователя по id
    @GetMapping("/person/edit/{id}")
    public String editPerson(@PathVariable("id")int id, Model model){
        model.addAttribute("editPerson", personService.getPersonById(id));
        return "person/editPerson";
    }

    // Метод принимает объект с формы и обновляет пользователя
    @PostMapping("/person/edit/{id}")
    public String editPerson(@ModelAttribute("editPerson") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "person/editPerson";
        }

        personService.updatePerson(id, person);
        return "redirect:/admin/person";
    }

// Метод возвращает страницу с выводом заказов кладет объект заказов в модель
@GetMapping("/orders")
public String order(Model model){;
    model.addAttribute("orders", orderService.getAllOrders());
    return "admin/orders";
}

    //   Метод возвращает страницу с формой редактирования заказ и помещает в модель объект редактируемого заказа по id
    @GetMapping("/orders/{id}")
    public String editOrder(@PathVariable("id")int id, Model model){
        model.addAttribute("info_order", orderService.getOrderById(id));
        return "/admin/infoOrder";
    }
    //
    // Метод принимает объект с формы и обновляет заказы
    @PostMapping("/orders/{id}")
    public String changeStatus(@PathVariable("id") int id, @RequestParam("status") Status status){
        Order order_status=orderService.getOrderById(id);
        order_status.setStatus(status);
        orderService.updateOrderStatus(order_status);
        return "redirect:/admin/orders/";
    }

    // Поиск по последним сомволам номера заказа
    @PostMapping("/orders/search")
    public String searchOrderByLastSymbols(@RequestParam("value") String value, Model model) {
        model.addAttribute("search_order",orderRepository.findByNumberEndingWith(value));
        return "/admin/orders";
    }

    }
