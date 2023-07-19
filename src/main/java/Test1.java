public class Test1 {



        public void print(){
            System.out.println("message");
        }

          public void print(){
            System.out.println("message");
        }


    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteById(@RequestParam("id")Long id ){
        service.deleteById(id);

        return ResponseEntity.ok("Student deleted Successfully");
    }
    }

