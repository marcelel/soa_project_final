package com.agh.edu.rest.resource;

import com.agh.edu.api.dto.CategoryDto;
import com.agh.edu.api.dto.ElementDto;
import com.agh.edu.api.dto.Role;
import com.agh.edu.api.service.CategoryService;
import com.agh.edu.rest.translator.Translator;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Locale;

@Path(value = "/category")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CategoryResource {

    @EJB(mappedName = "java:global/server-1.0-SNAPSHOT/CategoryService!com.agh.edu.api.service.CategoryService")
    private CategoryService categoryService;

    @Inject
    private Translator translator;

    @GET
    public Response getAllCategories(@Context SecurityContext securityContext, @Context HttpServletRequest request) {
        if (!securityContext.isUserInRole(Role.Administrator.name())) {
            return Response.status(403).build();
        }

        Locale locale = request.getLocale();
        List<CategoryDto> categories = categoryService.getAllCategories();
        if (locale.equals(Locale.GERMAN)) {
            categories.forEach(x -> translator.translate(x));
        }
        GenericEntity<List<CategoryDto>> genericEntity = new GenericEntity<List<CategoryDto>>(categories) {};
        return Response.status(200).entity(genericEntity).build();
    }

    @GET
    @Path("/{id}")
    public Response getCategory(@PathParam("id") Integer id, @Context SecurityContext securityContext) {
        if (!securityContext.isUserInRole(Role.Administrator.name())) {
            return Response.status(403).build();
        }

        CategoryDto category = categoryService.getCategoryById(id);
        if (category == null) {
            return Response.status(404).build();
        }
        GenericEntity<CategoryDto> genericEntity = new GenericEntity<CategoryDto>(category) {};
        return Response.status(200).entity(genericEntity).build();
    }

    @POST
    public Response addElement(ElementDto elementDto, @Context SecurityContext securityContext) {
        if (!securityContext.isUserInRole(Role.Administrator.name())) {
            return Response.status(403).build();
        }

        CategoryDto categoryById = categoryService.getCategoryById(elementDto.getCategoryId());
        if (categoryById == null) {
            return Response.status(400).build();
        }
        com.agh.edu.api.dto.ElementDto element = categoryService.initElementDtoByCategory(categoryById);
        if (element == null) {
            return Response.status(400).build();
        }
        element.setName(elementDto.getName());
        element.setCategory(categoryById);
        element.setAttributeAmount(elementDto.getAttributeAmount());
        element.setAttributeName(elementDto.getAttributeName());
        element.setPower(elementDto.getPower());
        categoryService.addNewElement(element);
        return Response.status(200).build();
    }
}
