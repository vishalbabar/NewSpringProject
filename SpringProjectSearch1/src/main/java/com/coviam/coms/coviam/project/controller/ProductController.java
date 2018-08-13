package com.coviam.coms.coviam.project.controller;

import com.coviam.coms.coviam.project.productDTO.ProductDTO;
import com.coviam.coms.coviam.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/search")
public class ProductController {


    @Autowired
    @Qualifier("com.coviam.coms.coviam.project.service.Impl.ProductServiceImpl")
    ProductService productService;

    @RequestMapping(method = RequestMethod.GET,value = "/delete")
    public String deleteAllDocuments() {
        try { //delete all documents from solr core
            productService.deleteAll();
            return "documents deleted succesfully!";
        }catch (Exception e){
            return "Failed to delete documents";
        }
    }


    @RequestMapping(method = RequestMethod.GET,value = "/getAll")
    public ResponseEntity<List<ProductDTO>> getAllDocs() {

        List<ProductDTO> productDTOList=productService.getAll();
        return new ResponseEntity<List<ProductDTO>>(productDTOList,HttpStatus.OK);


    }

    @RequestMapping(method = RequestMethod.GET,value = "/searchbyid/{id}")
    public ResponseEntity<List<ProductDTO>> searchById(@PathVariable String id){
        List<ProductDTO> productDTO= (List<ProductDTO>) productService.searchById(id);

        return new ResponseEntity<List<ProductDTO>>(productDTO,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/searchbyname/{productname}/{pageable}")
    public ResponseEntity<List<ProductDTO>> searchByName(@PathVariable String productname, Pageable pageable)
    {
        List<ProductDTO> productDTOSList=productService.searchByName(productname,pageable);

        return new ResponseEntity<List<ProductDTO>>(productDTOSList,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/searchbystartingname/{productName}")
    public ResponseEntity<List<ProductDTO>> searchByStartinfName(@PathVariable String productname)
    {
        List<ProductDTO> productDTOSList=productService.searchByStartingName(productname);

        return new ResponseEntity<List<ProductDTO>>(productDTOSList,HttpStatus.OK);
    }
}
