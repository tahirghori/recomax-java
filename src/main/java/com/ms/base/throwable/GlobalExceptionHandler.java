package com.ms.base.throwable;


import com.ms.base.throwable.exception.ResourceNotFoundException;
import com.ms.base.throwable.exception.UnProcessableEntitiyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> exception(ResourceNotFoundException exception) {
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }

    //
//    // Let Spring BasicErrorController handling the exception, we just override the status code
    @ExceptionHandler(UnProcessableEntitiyException.class)
    public ResponseEntity<Object> springHandleNotFound(UnProcessableEntitiyException message) {
        message.baseMessage.setDetails(message.getLocalizedMessage());
        return new ResponseEntity<>(message.baseMessage, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(ResourceUnSupportedFieldPatchException.class)
//    public void springUnSupportedFieldPatch(HttpServletResponse message) throws IOException {
//        message.sendError(HttpStatus.METHOD_NOT_ALLOWED.value());
//    }
//
//    // @Validate For Validating Path Variables and Request Parameters
//    @ExceptionHandler(ConstraintViolationException.class)
//    public void constraintViolationException(HttpServletResponse message) throws IOException {
//        message.sendError(HttpStatus.BAD_REQUEST.value());
//    }

    // message handling for @Valid
//    @Override
////    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                                  HttpHeaders headers,
//                                                                  HttpStatus status, WebRequest request) {
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", new Date());
//        body.put("status", status.value());
//
//        //Get all errors
//        List<Map<String, String>> errors = new ArrayList<>();
//        for (FieldError item : ex.getBindingResult()
//                .getFieldErrors()) {
//            Map<String, String> message = new HashMap<>();
//            message.put(item.getField(), item.getDefaultMessage());
//            errors.add(message);
//        }
//        body.put("errors", errors);
//        return new ResponseEntity<>(body, headers, status);
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    ResponseEntity handleException(MethodArgumentNotValidException e) {
////        SimpleValidateResults results = new SimpleValidateResults();
////        e.getBindingResult().getAllErrors()
////                .stream()
////                .filter(FieldError.class::isInstance)
////                .map(FieldError.class::cast)
////                .forEach(fieldError -> results.addResult(fieldError.getField(), fieldError.getDefaultMessage()));
//
//
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", new Date());
//        body.put("status", "taa");
//
//        //Get all errors
//        List<Map<String, String>> errors = new ArrayList<>();
//        for (FieldError item : e.getBindingResult()
//                .getFieldErrors()) {
//            Map<String, String> message = new HashMap<>();
//            message.put(item.getField(), item.getDefaultMessage());
//            errors.add(message);
//        }
//        body.put("errors", errors);
//
//        return ResponseEntity.unprocessableEntity().body(body) ;
//
////    .message(400, results.getResults().isEmpty() ? e.getMessage() : results.getResults().get(0).getMessage()).result(results.getResults())
//    }


//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        return ex.getBindingResult()
//                .getAllErrors().stream()
//                .map(ObjectError::getDefaultMessage)
//                .collect(Collectors.toList());
//    }

//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
//        List<Map<String, String>> details = new ArrayList<>();
//        Map<String, String> err = new HashMap<>();
//        err.put("message", ex.getLocalizedMessage());
//        details.add(err);
//        InternalServerErrorException message = new InternalServerErrorException(new Date().toString(), "Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value(),"", details);
//        return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(IOException.class)
//    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
//    public Object exceptionHandler(IOException e) {
//
//        return new HttpEntity<>(e.getMessage());
//
//    }
//
//    @ExceptionHandler(MissingHeaderInfoException.class)
//    public final ResponseEntity<InternalServerErrorException> handleInvalidTraceIdException
//            (MissingHeaderInfoException ex, WebRequest request) {
//        List<Map<String, String>> details = new ArrayList<>();
//        Map<String, String> err = new HashMap<>();
//        err.put("message", ex.getLocalizedMessage());
//        details.add(err);
//        InternalServerErrorException message = new InternalServerErrorException(
//                new Date().toString(),
//                "Server Error",
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                "Server Error",
//                details);
//
//        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
//    }
//
//
//    @Override
//    protected ResponseEntity<Object> handleMissingServletRequestParameter(
//            MissingServletRequestParameterException ex, HttpHeaders headers,
//            HttpStatus status, WebRequest request) {
//        String message = ex.getParameterName() + " parameter is missing";
//
//
//        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
//    }

}
