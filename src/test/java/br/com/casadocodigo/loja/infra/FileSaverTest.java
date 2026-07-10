package br.com.casadocodigo.loja.infra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
class FileSaverTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private ServletContext servletContext;

    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private FileSaver fileSaver;

    @Test
    void shouldWriteFileAndReturnRelativePath() throws Exception {
        File tempDir = Files.createTempDirectory("file-saver").toFile();
        tempDir.deleteOnExit();

        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRealPath("/arquivos")).thenReturn(tempDir.getAbsolutePath());
        when(multipartFile.getOriginalFilename()).thenReturn("livro.pdf");
        doAnswer(invocation -> {
            File destination = invocation.getArgument(0);
            destination.getParentFile().mkdirs();
            assertTrue(destination.createNewFile());
            return null;
        }).when(multipartFile).transferTo(any(File.class));

        String result = fileSaver.write("arquivos", multipartFile);

        assertEquals("arquivos/livro.pdf", result);
        assertTrue(new File(tempDir, "livro.pdf").exists());
    }

    @Test
    void shouldThrowArquivoJaProcessadoExceptionWhenTransferFailsWithIllegalState() throws Exception {
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRealPath("/arquivos")).thenReturn("/tmp");
        when(multipartFile.getOriginalFilename()).thenReturn("arquivo.pdf");
        doThrow(new IllegalStateException("already processed")).when(multipartFile).transferTo(any(File.class));

        ArquivoJaProcessadoException exception = assertThrows(
                ArquivoJaProcessadoException.class,
                () -> fileSaver.write("arquivos", multipartFile));

        assertEquals("Arquivo já foi processado: arquivo.pdf", exception.getMessage());
        assertNotNull(exception.getCause());
        assertInstanceOf(IllegalStateException.class, exception.getCause());
    }

    @Test
    void shouldThrowErroAoSalvarArquivoExceptionWhenTransferFailsWithIOException() throws Exception {
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRealPath("/arquivos")).thenReturn("/tmp");
        when(multipartFile.getOriginalFilename()).thenReturn("arquivo.pdf");
        doThrow(new IOException("fail to save")).when(multipartFile).transferTo(any(File.class));

        ErroAoSalvarArquivoException exception = assertThrows(
                ErroAoSalvarArquivoException.class,
                () -> fileSaver.write("arquivos", multipartFile));

        assertEquals("Falha ao salvar o arquivo 'arquivo.pdf'", exception.getMessage());
        assertNotNull(exception.getCause());
        assertInstanceOf(IOException.class, exception.getCause());
    }
}
