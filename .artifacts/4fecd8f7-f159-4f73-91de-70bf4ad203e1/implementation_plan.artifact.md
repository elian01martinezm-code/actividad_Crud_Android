# Implementation Plan - Project Correction and Refactoring

This plan aims to correct architectural inconsistencies, fix dependency injection issues, and ensure follow-up on best practices across the project.

## User Review Required

> [!IMPORTANT]
> I will be renaming several methods to follow Kotlin's `camelCase` naming convention (e.g., `GetProductById` -> `getProductById`). This will affect almost all layers of the app (API, Repository, UseCase, ViewModel).

## Proposed Changes

### [Core] - Permissions & Setup
#### [MODIFY] [AndroidManifest.xml](file:///C:/Users/oscar/OneDrive/Desktop/CRUD_ANDROID/app/src/main/AndroidManifest.xml)
- Add `<uses-permission android:name="android.permission.INTERNET" />` to allow network requests.

### [Data Layer] - API & DTOs
#### [MODIFY] [ProductApiService.kt](file:///C:/Users/oscar/OneDrive/Desktop/CRUD_ANDROID/app/src/main/java/com/sena/crud/data/remote/api/ProductApiService.kt)
- Rename `GetProductByid` to `getProductById`.

#### [MODIFY] [ProductRepositoryImpl.kt](file:///C:/Users/oscar/OneDrive/Desktop/CRUD_ANDROID/app/src/main/java/com/sena/crud/data/repository/ProductRepositoryImpl.kt)
- Change `jakarta.inject.Inject` to `javax.inject.Inject`.
- Rename `GetProductById` to `getProductById`.

#### [MODIFY] [ProductMapper.kt](file:///C:/Users/oscar/OneDrive/Desktop/CRUD_ANDROID/app/src/main/java/com/sena/crud/data/mapper/ProductMapper.kt)
- Ensure naming consistency with DTOs and Domain models.

### [Domain Layer] - UseCases & Repositories
#### [MODIFY] [ProductRepository.kt](file:///C:/Users/oscar/OneDrive/Desktop/CRUD_ANDROID/app/src/main/java/com/sena/crud/domain/repository/ProductRepository.kt)
- Rename `GetProductById` to `getProductById`.

#### [MODIFY] [GetProductUseCase.kt](file:///C:/Users/oscar/OneDrive/Desktop/CRUD_ANDROID/app/src/main/java/com/sena/crud/domain/useCase/GetProductUseCase.kt)
- Update call to `repository.getProductById`.

### [Dependency Injection] - Hilt Modules
#### [MODIFY] [NetworkModule.kt](file:///C:/Users/oscar/OneDrive/Desktop/CRUD_ANDROID/app/src/main/java/com/sena/crud/di/NetworkModule.kt)
- Change `jakarta.inject.Singleton` to `javax.inject.Singleton`.
- Rename `provideCharacterApiService` to `provideProductApiService`.

#### [MODIFY] [RepositoryModule.kt](file:///C:/Users/oscar/OneDrive/Desktop/CRUD_ANDROID/app/src/main/java/com/sena/crud/di/RepositoryModule.kt)
- Change `jakarta.inject.Singleton` to `javax.inject.Singleton`.
- Rename `bindCharacterRepository` to `bindProductRepository`.

### [UI Layer] - ViewModels & Screens
#### [MODIFY] [ProductViewModel.kt](file:///C:/Users/oscar/OneDrive/Desktop/CRUD_ANDROID/app/src/main/java/com/sena/crud/ui/viewModel/ProductViewModel.kt)
- Ensure it calls the updated UseCase method.

## Verification Plan

### Automated Tests
- Run `./gradlew assembleDebug` to ensure the project compiles without errors.
- (Optional) Run unit tests if they exist: `./gradlew test`.

### Manual Verification
- Deploy the app to a device/emulator.
- Verify that product details are fetched and displayed correctly from the API.
- Verify that the "Refresh" button works.
