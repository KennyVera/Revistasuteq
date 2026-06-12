# Revistasuteq

Un proyecto de aplicación Android moderna desarrollado en **Kotlin** (93.7%) y **Java** (6.3%), siguiendo las mejores prácticas de desarrollo Android.

## 📱 Descripción

Revistasuteq es una aplicación Android que utiliza tecnologías modernas y herramientas de desarrollo actualizadas. El proyecto está estructurado con un enfoque modular y utiliza Gradle como sistema de compilación.

## 🛠️ Tecnologías Utilizadas

### Lenguajes
- **Kotlin**: 93.7% - Lenguaje principal del proyecto
- **Java**: 6.3% - Soporte complementario

### Herramientas y Frameworks
- **Android SDK**: API 34 (minSdk) - API 36 (targetSdk)
- **Gradle**: Sistema de compilación basado en Kotlin DSL
- **AndroidX**: Librerías de compatibilidad
- **Material Design**: Componentes de interfaz moderna

### Dependencias Principales
- `androidx.appcompat` - Compatibilidad con versiones anteriores
- `androidx.constraintlayout` - Diseño flexible de interfaces
- `com.google.android.material` - Componentes Material Design
- `android.volley:1.2.1` - Peticiones HTTP
- `glide:4.16.0` - Carga y caché de imágenes
- `androidx.activity:ktx` - Extensiones de Activity
- `junit` - Testing unitario
- `espresso.core` - Testing de UI

## 📋 Requisitos Previos

- **JDK 11** o superior
- **Android Studio** (versión reciente recomendada)
- **Android SDK** con API 36 instalado
- **Gradle 8.0** o superior

## 🚀 Cómo Empezar

### 1. Clonar el Repositorio

```bash
git clone https://github.com/KennyVera/Revistasuteq.git
cd Revistasuteq
```

### 2. Abrir en Android Studio

- Abre Android Studio
- Selecciona "Open an Existing Project"
- Navega a la carpeta del proyecto clonado
- Android Studio descargará automáticamente las dependencias

### 3. Compilar el Proyecto

Usando Gradle:

```bash
# En Linux/Mac
./gradlew build

# En Windows
gradlew.bat build
```

### 4. Ejecutar la Aplicación

```bash
# En Linux/Mac
./gradlew installDebug

# En Windows
gradlew.bat installDebug
```

O simplemente presiona **Run** en Android Studio.

## 📁 Estructura del Proyecto

```
Revistasuteq/
├── app/                       # Módulo principal de la aplicación
│   ├── build.gradle.kts      # Configuración de compilación del app
│   ├── src/                  # Código fuente
│   │   ├── main/             # Código principal
│   │   ├── test/             # Tests unitarios
│   │   └── androidTest/      # Tests de instrumentación
│   └── proguard-rules.pro    # Configuración ProGuard/R8
├── gradle/                   # Configuración de Gradle
├── build.gradle.kts          # Configuración raíz del proyecto
├── settings.gradle.kts       # Configuración de submódulos
├── gradle.properties         # Propiedades de compilación
├── gradlew                   # Wrapper de Gradle (Linux/Mac)
├── gradlew.bat              # Wrapper de Gradle (Windows)
└── .gitignore               # Archivos ignorados por Git
```

## ⚙️ Configuración de Compilación

### API Levels
- **Minimum SDK**: 34 (Android 14)
- **Target SDK**: 36 (Android 15)
- **Compile SDK**: 36

### Versión de la Aplicación
- **Version Code**: 1
- **Version Name**: 1.0
- **Package**: com.example.revistasuteq

### Java Compatibility
- **Source Compatibility**: Java 11
- **Target Compatibility**: Java 11

## 🧪 Testing

### Tests Unitarios

```bash
./gradlew test
```

### Tests de Instrumentación (Android)

```bash
./gradlew connectedAndroidTest
```

## 🔧 Comandos Útiles

```bash
# Limpiar proyecto
./gradlew clean

# Compilar en modo Debug
./gradlew assembleDebug

# Compilar en modo Release
./gradlew assembleRelease

# Ver dependencias
./gradlew dependencies

# Analizar tipos estáticos (lint)
./gradlew lint

# Ejecutar todas las pruebas
./gradlew test connectedAndroidTest
```

## 📦 Dependencias Externas

Las versiones y catálogos de dependencias se gestionan a través del archivo `gradle/libs.versions.toml`. Para más información, consulta la [documentación oficial de Gradle](https://docs.gradle.org/current/userguide/platforms.html).

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Para contribuir:

1. Haz un fork del proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto no tiene una licencia especificada. Para más información, contacta con el propietario del repositorio.

## 👤 Autor

- **KennyVera** - [GitHub Profile](https://github.com/KennyVera)

## 📞 Soporte

Si tienes preguntas o encuentras problemas, por favor:
- Abre un [Issue](https://github.com/KennyVera/Revistasuteq/issues)
- Contacta directamente con el autor

---

**Nota**: Este proyecto utiliza Gradle Wrapper, lo que significa que no necesitas tener Gradle instalado globalmente en tu máquina. El proyecto incluye `gradlew` y `gradlew.bat` para este propósito.

**Última actualización**: Junio 2026
