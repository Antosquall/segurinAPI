//package com.api.antonio.controlador;
//
//import java.util.List;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.api.antonio.modelo.Usuario;
//import com.api.antonio.repositorio.UsuarioRepositorio;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@RestController
//@RequestMapping("/api/usuarios")
//public class UsuarioControlador {
//
//    @Autowired
//    private UsuarioRepositorio usuarioRepositorio;
//
//    // Clave secreta para firmar el token JWT
//    private final String SECRET_KEY = "miClaveSecreta";
//
//    @GetMapping
//    public List<Usuario> obtenerTodosLosUsuarios() {
//        return usuarioRepositorio.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
//        return usuarioRepositorio.findById(id)
//            .map(ResponseEntity::ok)
//            .orElse(ResponseEntity.notFound().build());
//    }
//
////    @PostMapping
////    public Usuario crearUsuario(@RequestBody Usuario usuario) {
////        // Codificar la contraseña antes de guardarla
////        usuario.setpassword(encryptPassword(usuario.getpassword()));
////        return usuarioRepositorio.save(usuario);
////    }
////
////    @PutMapping("/{id}")
////    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetalles) {
////        return usuarioRepositorio.findById(id)
////            .map(usuario -> {
////                usuario.setNombre(usuarioDetalles.getNombre());
////                usuario.setpassword(encryptPassword(usuarioDetalles.getpassword()));
////                Usuario usuarioActualizado = usuarioRepositorio.save(usuario);
////                return ResponseEntity.ok(usuarioActualizado);
////            })
////            .orElse(ResponseEntity.notFound().build());
////    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> eliminarUsuario(@PathVariable Long id) {
//        return usuarioRepositorio.findById(id)
//            .map(usuario -> {
//                usuarioRepositorio.delete(usuario);
//                return ResponseEntity.noContent().build();
//            })
//            .orElse(ResponseEntity.notFound().build());
//    }
//
////    @PostMapping("/login")
////    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
////        String username = credenciales.get("username");
////        String password = credenciales.get("password");
////
////        Optional<Usuario> usuarioOpt = usuarioRepositorio.findByNombre(username);
////        if (usuarioOpt.isEmpty() || !encryptPassword(password).equals(usuarioOpt.get().getpassword())) {
////            return ResponseEntity.status(401).body("Credenciales incorrectas");
////        }
////
////        Usuario usuario = usuarioOpt.get();
////
////        // Generar el token JWT
////        String token = Jwts.builder()
////            .setSubject(usuario.getNombre())
////            .setIssuedAt(new Date())
////            .setExpiration(new Date(System.currentTimeMillis() + 864_000_000)) // 10 días
////            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
////            .compact();
////
////        Map<String, String> response = new HashMap<>();
////        response.put("token", token);
////
////        return ResponseEntity.ok(response);
////    }
//
//    private String encryptPassword(String password) {
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hash = digest.digest(password.getBytes());
//            StringBuilder hexString = new StringBuilder();
//            for (byte b : hash) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) {
//                    hexString.append('0');
//                }
//                hexString.append(hex);
//            }
//            return hexString.toString();
//        } catch (NoSuchAlgorithmException e) {
//            System.err.println(e.getMessage());
//            return null;
//        }
//    }
//}
