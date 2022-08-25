import com.webapp.PaginationBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaginationBuilderTest {
    @Test
    void paginationBuilderTest(){
        PaginationBuilder builder = new PaginationBuilder("10");

        assertEquals(2,builder.getCountOnPage());
        builder.setCountOnPage(10);
        assertEquals(10,builder.getCountOnPage());

        assertEquals(10,builder.getPage());
        builder.setPage(12);
        assertEquals(12,builder.getPage());

        assertEquals(0,builder.getCountOfElement());
        builder.setCountOfElement(10);
        assertEquals(10,builder.getCountOfElement());

        assertEquals(numOfPag(builder),builder.getNumberOfPages());
        builder = new PaginationBuilder("15",50);
        assertEquals(numOfPag(builder),builder.getNumberOfPages());

        //getStartElement
        assertEquals(startEl(builder),builder.getStartElement());
        builder = new PaginationBuilder(12,60);
        assertEquals(startEl(builder),builder.getStartElement());

        builder.setCountOfElement(3);
        builder.setCountOnPage(4);
        assertEquals(numOfPag(builder),builder.getNumberOfPages());
    }

    private static int numOfPag(PaginationBuilder builder){
        int nOfPages = builder.getCountOfElement()  / builder.getCountOnPage();
        if (builder.getCountOfElement() % builder.getCountOnPage() > 0)
            nOfPages++;
        return nOfPages;
    }
    private static int startEl(PaginationBuilder builder){
        return (builder.getPage()-1)*builder.getCountOnPage()+1;
    }
}
