package com.example.Management.Controller;

import com.example.Management.Client.FullResponse;
import com.example.Management.Model.Employee;
import com.example.Management.Model.Event;
import com.example.Management.Model.PaymentStatus;
import com.example.Management.Service.EmployeeService;
import com.example.Management.Service.EventService;
import com.razorpay.Payment;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EventService eventService;

    @GetMapping()
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok().body(employeeService.getAllEmployees()) ;
    }

    @PostMapping()
    public ResponseEntity<Employee> addEmployee(Employee employee) {
        return ResponseEntity.ok().body(employeeService.addEmployee(employee));
    }

    @PutMapping()
    public ResponseEntity<Employee> updateEmployee(Employee employee) {
        return ResponseEntity.ok().body(employeeService.updateEmployee(employee));
    }

    @PutMapping("/approveEvent")
    public ResponseEntity<Event> approveEvent(Long eventId) {
        return ResponseEntity.ok().body(eventService.approveEvent(eventId));
    }


    @GetMapping("/invoice/{eventId}")
    public ResponseEntity<FullResponse> sendOrder(@PathVariable("eventId") Long eventId) {
        return ResponseEntity.ok().body(eventService.sendInvoice(eventId));
    }

//    @GetMapping("/order/{eventId}")
//    public ResponseEntity<FullResponse> mail(@PathVariable("eventId") Long eventId) {
//        return ResponseEntity.ok().body(eventService.sendOrder(eventId));
//    }

//    @GetMapping("paymentStatus")
//    public ResponseEntity<List<Event>> getPaymentStatus(@RequestParam PaymentStatus status) {
//        return ResponseEntity.ok().body(eventService.getEventsByPaymentStatus(status));
//    }
}
